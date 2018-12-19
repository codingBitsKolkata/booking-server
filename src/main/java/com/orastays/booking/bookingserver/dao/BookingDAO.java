package com.orastays.booking.bookingserver.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.orastays.booking.bookingserver.entity.BookingEntity;
import com.orastays.booking.bookingserver.helper.Status;

@Repository
public class BookingDAO extends GenericDAO<BookingEntity, Long> {

	private static final long serialVersionUID = -5070151407565218463L;

	public BookingDAO() {
		super(BookingEntity.class);

	}
	
	@SuppressWarnings("unchecked")
	public List<BookingEntity> getBookingsByCheckInDate(String date, String propertyId) {
		String hql = "FROM BookingEntity be where DATE(be.checkinDate) <= DATE('"+ date + "')"
				+ "and DATE(be.checkoutDate) >= DATE('"+ date + "')"
				+ "and be.status=" + Status.ACTIVE.ordinal() + " and be.propertyId = " + Long.parseLong(propertyId);
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("rawtypes")
		List results = query.list();
		return results;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<BookingEntity> getBookedPivateRoom(String propertyId, String roomId, String checkinDate, String checkoutDate) {
		
		String hql = "FROM BookingEntity be inner join BookingVsRoomEntity bre"
						+ "on be.bookingId = bre.bookingEntity.bookingId"
						+ "where (DATE('"+ checkinDate +"') <= DATE(be.checkinDate) and DATE('"+ checkoutDate +"') >= DATE(be.checkoutDate))"
						+ "or  (DATE('"+ checkinDate +"') <= DATE(be.checkinDate) and DATE('"+ checkoutDate +"') <= DATE(be.checkoutDate))"
						+ "or  (DATE('"+ checkinDate +"') >= DATE(be.checkinDate) and DATE('"+ checkoutDate +"') >= DATE(be.checkoutDate))"
						+ "or  (DATE('"+ checkinDate +"') >= DATE(be.checkinDate) and DATE('"+ checkoutDate +"') <= DATE(be.checkoutDate))"
						+ "and be.propertyId = " + propertyId + " and be.status=" + Status.ACTIVE.ordinal();
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List results = query.list();
		return results;
	}
}
