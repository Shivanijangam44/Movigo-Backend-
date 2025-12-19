package com.movigo.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.SearchControls;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movigo.dao.ScreenDao;
import com.movigo.dao.SeatDao;
import com.movigo.dto.seatDtos.SeatAvailableDto;
import com.movigo.dto.seatDtos.SeatDto;
import com.movigo.dto.seatDtos.SeatUpdateDto;
import com.movigo.entity.Screen;
import com.movigo.entity.Seat;
import com.movigo.exceptions.BusinessException;

@Service
@Transactional
public class SeatServiceImpl implements SeatService{

	@Autowired 
	private SeatDao seatDao;
	
	@Autowired
	private ScreenDao screenDao;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Override
	public List<SeatAvailableDto> getAvailableSeatsByScreenId(Long screenId) {
	    
		return seatDao.findAvailableSeatsByScreenId(screenId);
	}

	@Override
	public List<SeatDto> getSeatsByScreen(Long screenId) {
		List<SeatDto> seatDtoList = new ArrayList<>();
		List<Seat> list = seatDao.findByScreenScreenId(screenId);
		
		list.forEach(element -> {
			SeatDto dto = modelmapper.map(element, SeatDto.class);
			seatDtoList.add(dto);
		});
		return seatDtoList ;
	}
	
	@Override
	public SeatDto updateSeat(List<SeatUpdateDto> seatUpdateDto, Long seatId) {
		Seat seat = seatDao.findById(seatId)
				.orElseThrow(()-> new BusinessException("Seat not found for Id " +seatId));
		
		seatUpdateDto.forEach(element ->{
			if("seatType".equals(element.getFieldName())) {
				seat.setSeatType(element.getValue());
			}else if("screenId".equals(element.getFieldName())) {
				Long screenId = Long.parseLong(element.getValue());
				Screen screen = screenDao.findById(screenId)
						.orElseThrow(() -> new BusinessException("Screen not found for Id " +screenId));
			    seat.setScreen(screen);
			}else {
	            throw new BusinessException("Invalid field name: " + element.getFieldName());
	        }
		});
			Seat savedSeat = seatDao.save(seat);
			
			SeatDto seatDto = modelmapper.map(savedSeat,SeatDto.class);
			seatDto.setScreenId(savedSeat.getScreen().getScreenId());
		
		return seatDto;
	}
	

	@Override
	public void deleteSeat(Long seatId) {
		if(!seatDao.existsById(seatId)) {
			throw new BusinessException("Seat not found for id "+seatId);
		}
		seatDao.deleteById(seatId);
		
	}

	@Override
	public SeatDto bookSeat(Long seatId) {
		Seat seat = seatDao.findById(seatId).orElse(null);
		if(seat == null) {
			throw new BusinessException("Seat not found for Id "+seatId);
			}
		if(!seat.isAvailable()) {
			throw new BusinessException("Seat is booked with id "+seatId);
		}
		
		seat.setAvailable(false);
		Seat bookedseat = seatDao.save(seat);
		
		SeatDto dto = modelmapper.map(bookedseat, SeatDto.class);
		dto.setScreenId(bookedseat.getScreen().getScreenId());
		return dto;
	}

	@Override
	public SeatDto cancelSeat(Long seatId) {
		 Seat seat = seatDao.findById(seatId).orElse(null);
		 if(seat == null) {
			 throw new BusinessException("Seat not found for Id "+seatId);
		 }
		 if(seat.isAvailable()) {
			 throw new BusinessException("Seat is not currently booked with Id "+seatId);
		 }
		 
		 seat.setAvailable(true);
		 Seat cancelledSeat = seatDao.save(seat);
		 
		 SeatDto seatDto = modelmapper.map(cancelledSeat, SeatDto.class);
	     seatDto.setScreenId(cancelledSeat.getScreen().getScreenId());
		return seatDto;
	}

	

}
