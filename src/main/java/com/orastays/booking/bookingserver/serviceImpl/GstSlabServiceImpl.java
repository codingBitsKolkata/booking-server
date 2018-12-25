package com.orastays.booking.bookingserver.serviceImpl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.orastays.booking.bookingserver.dao.GstSlabDAO;
import com.orastays.booking.bookingserver.entity.GstSlabEntity;
import com.orastays.booking.bookingserver.helper.Status;
import com.orastays.booking.bookingserver.helper.Util;
import com.orastays.booking.bookingserver.service.GstSlabService;

@Service
@Transactional
public class GstSlabServiceImpl implements GstSlabService {
	private static final Logger logger = LogManager.getLogger(BookingServiceImpl.class);

	@Value("${entitymanager.packagesToScan}")
	protected String entitymanagerPackagesToScan;

	@Autowired
	protected GstSlabDAO gstSlabDAO;

	/* (non-Javadoc)
	 * @see com.orastays.booking.bookingserver.serviceImpl.GstSlabService#getActiveGstEntity(java.lang.Double)
	 */
	@Override
	public GstSlabEntity getActiveGstEntity(Double amount) {
		if (logger.isInfoEnabled()) {
			logger.info("getActiveGstEntity -- START");
		}

		List<GstSlabEntity> gstSlabEntities = null;
		GstSlabEntity gstSlabEntityFetched = null;
		try {
			Map<String, String> innerMap1 = new LinkedHashMap<>();
			innerMap1.put("status", String.valueOf(Status.ACTIVE.ordinal()));
			Map<String, Map<String, String>> outerMap1 = new LinkedHashMap<>();
			outerMap1.put("eq", innerMap1);

			Map<String, Map<String, Map<String, String>>> alliasMap = new LinkedHashMap<>();
			alliasMap.put(entitymanagerPackagesToScan + ".GstSlabEntity", outerMap1);

			gstSlabEntities = gstSlabDAO.fetchListBySubCiteria(alliasMap);
		} catch (Exception e) {
			if (logger.isInfoEnabled()) {
				logger.info("Exception in getActiveGstEntity -- " + Util.errorToString(e));
			}
		}

		for (GstSlabEntity gstSlabEntity : gstSlabEntities) {
			if (Double.parseDouble(gstSlabEntity.getFromAmount()) <= amount
					&& Double.parseDouble(gstSlabEntity.getToAmount()) >= amount) {
				gstSlabEntityFetched = gstSlabEntity;
				break;
			}
		}

		if (logger.isInfoEnabled()) {
			logger.info("getActiveGstEntity -- END");
		}

		return gstSlabEntityFetched;

	}
}
