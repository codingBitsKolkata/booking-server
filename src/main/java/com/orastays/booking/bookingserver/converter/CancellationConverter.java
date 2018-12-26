package com.orastays.booking.bookingserver.converter;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.orastays.booking.bookingserver.entity.CancellationEntity;
import com.orastays.booking.bookingserver.model.CancellationModel;

@Component
public class CancellationConverter extends CommonConverter implements BaseConverter<CancellationEntity, CancellationModel> {

	private static final long serialVersionUID = -7101212308719678276L;

	private static final Logger logger = LogManager.getLogger(CancellationConverter.class);

	@Override
	public CancellationEntity modelToEntity(CancellationModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CancellationModel entityToModel(CancellationEntity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CancellationModel> entityListToModelList(List<CancellationEntity> es) {
		// TODO Auto-generated method stub
		return null;
	}

	
}