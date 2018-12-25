package com.orastays.booking.bookingserver.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.orastays.booking.bookingserver.entity.BookingEntity;
import com.orastays.booking.bookingserver.helper.BookingStatus;
import com.orastays.booking.bookingserver.helper.Status;

@Repository
public class BookingDAO extends GenericDAO<BookingEntity, Long> {

	private static final long serialVersionUID = -5070151407565218463L;

	public BookingDAO() {
		super(BookingEntity.class);

	}

	@SuppressWarnings("unchecked")
	public List<BookingEntity> getBookingsByCheckInDate(String date, String propertyId) {
		String hql = "FROM BookingEntity be where DATE(be.checkinDate) <= DATE('" + date + "')"
				+ "and DATE(be.checkoutDate) >= DATE('" + date + "')" + "and be.status=" + Status.ACTIVE.ordinal()
				+ " and be.propertyId = " + Long.parseLong(propertyId);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("rawtypes")
		List results = query.list();
		return results;
	}

	@SuppressWarnings({ "unchecked" })
	public boolean getBookedPivateRoom(String propertyId, String roomId, String checkinDate, String checkoutDate) {
		
		
		String hql = "SELECT count(*) as row_count FROM master_booking mb inner join booking_vs_room br on "
				+ "mb.booking_id = br.booking_id"
				+ " where mb.property_id = "+ Long.parseLong(propertyId) +" and br.room_id = '" + roomId + "' and br.status = " + BookingStatus.BOOKED.ordinal() + " and "
				+ "("
					+ "("
						+ "Date(mb.checkin_date) >= Date('" + checkinDate +"') and DATE(mb.checkout_date) <= Date('" + checkoutDate +"')"
					+ ")"
					+ "or"
					+ "("
						+ "DATE(mb.checkin_date) >= Date('" + checkinDate +"') and DATE(mb.checkout_date) >= Date('" + checkoutDate +"')"
						+ "and (DATE(mb.checkin_date) <= Date('" + checkoutDate +"'))"
					+ ")"
					+ "or"
					+ "("
						+ "DATE(mb.checkin_date) <= Date('" + checkinDate +"') and DATE(mb.checkout_date) >= Date('" + checkoutDate +"')"
					+ ")"
					+ "or"
					+ "("
						+ "DATE(mb.checkin_date) <= Date('" + checkinDate +"') and DATE(mb.checkout_date) <= Date('" + checkoutDate +"')"
						+ "and (DATE (mb.checkout_date) >= Date('" + checkinDate +"'))"
					+ ")"
				+ ")";
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
		try {
			Long count = ((BigInteger) query.uniqueResult()).longValue();
			return count != 0;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	@SuppressWarnings({ "unchecked" })
	public boolean getBookedSharedRoom(String propertyId, String roomId, String checkinDate, String checkoutDate,
			long numberOfSharedBed, long sharedBedCount, long numberOfSharedCot, long sharedCotCount) {
		
		
		String hql = "SELECT sum(ifnull(br.num_of_shared_bed, 0)) as bed_count, sum(ifnull(br.num_of_shared_cot, 0)) as cot_count FROM master_booking mb"
				+ " inner join booking_vs_room br on "
				+ "mb.booking_id = br.booking_id"
				+ " where mb.property_id = "+ Long.parseLong(propertyId) +" and br.room_id = '" + roomId + "' and br.status = " + BookingStatus.BOOKED.ordinal() + " and "
				+ "("
					+ "("
						+ "Date(mb.checkin_date) >= Date('" + checkinDate +"') and DATE(mb.checkout_date) <= Date('" + checkoutDate +"')"
					+ ")"
					+ "or"
					+ "("
						+ "DATE(mb.checkin_date) >= Date('" + checkinDate +"') and DATE(mb.checkout_date) >= Date('" + checkoutDate +"')"
						+ "and (DATE(mb.checkin_date) <= Date('" + checkoutDate +"'))"
					+ ")"
					+ "or"
					+ "("
						+ "DATE(mb.checkin_date) <= Date('" + checkinDate +"') and DATE(mb.checkout_date) >= Date('" + checkoutDate +"')"
					+ ")"
					+ "or"
					+ "("
						+ "DATE(mb.checkin_date) <= Date('" + checkinDate +"') and DATE(mb.checkout_date) <= Date('" + checkoutDate +"')"
						+ "and (DATE (mb.checkout_date) >= Date('" + checkinDate +"'))"
					+ ")"
				+ ")";
		
		Query query = sessionFactory.getCurrentSession().createSQLQuery(hql);
		try {
		List<Object[]> rows = query.list();
		if(!Objects.nonNull(rows)) { 
			return false;
		} else {
			for(Object[] row : rows) { //there can be max 1 row
				if(!((Long.parseLong(row[0].toString()) + numberOfSharedBed <= sharedBedCount) && (Long.parseLong(row[1].toString()) + numberOfSharedCot <= sharedCotCount))) {
					return true;
				}
			}
		}
		} catch(Exception e) {
			e.printStackTrace();
			return true;
		}
		return false;
	}
}
