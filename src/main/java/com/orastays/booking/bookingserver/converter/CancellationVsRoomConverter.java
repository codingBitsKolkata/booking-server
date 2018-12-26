package com.orastays.booking.bookingserver.converter;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.orastays.booking.bookingserver.entity.CancellationVsRoomEntity;
import com.orastays.booking.bookingserver.model.CancellationVsRoomModel;

@Component
public class CancellationVsRoomConverter extends CommonConverter
		implements BaseConverter<CancellationVsRoomEntity, CancellationVsRoomModel> {

	private static final long serialVersionUID = -3097439333727958284L;
	private static final Logger logger = LogManager.getLogger(CancellationVsRoomConverter.class);

	@Override
	public CancellationVsRoomEntity modelToEntity(CancellationVsRoomModel m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CancellationVsRoomModel entityToModel(CancellationVsRoomEntity e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CancellationVsRoomModel> entityListToModelList(List<CancellationVsRoomEntity> es) {
		// TODO Auto-generated method stub
		return null;
	}

}