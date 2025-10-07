package com.movigo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movigo.dao.TheaterDao;
import com.movigo.dto.theaterDtos.TheaterDto;
import com.movigo.dto.theaterDtos.TheaterUpdateDto;
import com.movigo.entity.Theater;

@Service
@Transactional
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	public TheaterDao theaterDao;
	
	@Autowired
	public ModelMapper modelmapper;
	
	@Override
	public TheaterDto addTheater(TheaterDto theaterDto) {
	    Theater theater = modelmapper.map(theaterDto, Theater.class);
	    
	    theater = theaterDao.save(theater);
		return modelmapper.map(theaterDto, TheaterDto.class);
	}

	@Override
	public List<TheaterDto> getAllTheaters() {
		List<TheaterDto> theaterDtoList = new ArrayList<>();
		List<Theater> theaterList = theaterDao.findAll();
		
		theaterList.forEach(theater->{
			TheaterDto theaterDto = modelmapper.map(theater, TheaterDto.class);
			theaterDtoList.add(theaterDto);
		});
		return theaterDtoList;
	}

	@Override
	public void deleteTheater(Long theaterId) {
		theaterDao.deleteById(theaterId);
		
	}

	@Override
	public List<TheaterDto> getTheaterByName(String TheaterName) {
		List<TheaterDto> tList = new ArrayList<>();
		List<Theater> theaterList = theaterDao.findByTheaterName(TheaterName);
		
		theaterList.forEach(theater ->{
			TheaterDto thDto = modelmapper.map(theater,TheaterDto.class);
			tList.add(thDto);
			
		});
		return tList;
	}

	@Override
	public List<TheaterDto> getTheaterByLocation(String Location) {
		List<TheaterDto> tDtoList = new ArrayList<>();
		List<Theater> thList = theaterDao.findByLocation(Location);
		
		thList.forEach(item ->{
			TheaterDto tDto = modelmapper.map(item, TheaterDto.class);
			tDtoList.add(tDto);
		});
		return tDtoList;
	}

	@Override
	public TheaterDto updateTheaterDetails(List<TheaterUpdateDto> theaterUpdateDto, Long TheaterId) {
		Optional<Theater> optionalTheater = theaterDao.findById(TheaterId);
		Theater theater = optionalTheater.orElse(null);
		
		if(theater != null) {
			theaterUpdateDto.forEach(item ->{
				if("theaterName".equals(item.getFieldName())) {
					theater.setTheaterName(item.getValue());
				}
				else if("location".equals(item.getFieldName())) {
					theater.setLocation(item.getValue());
				}
				else if("totalScreens".equals(item.getFieldName())) {
					theater.setTotalScreens(Integer.parseInt(item.getValue()));
				}
				
			});
			TheaterDto thDto = modelmapper.map(theaterDao.save(theater),TheaterDto.class);
			
			return thDto;
		}
		return null;
	}

}
