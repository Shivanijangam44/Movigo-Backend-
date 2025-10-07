package com.movigo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movigo.dao.SeatDao;
import com.movigo.dao.SeatTypeRuleDao;
import com.movigo.entity.Screen;
import com.movigo.entity.Seat;
import com.movigo.entity.SeatTypeRule;

@Service
@Transactional
public class SeatTypeRuleSeriveImpl implements SeatTypeRuleService{

	@Autowired 
	private SeatTypeRuleDao seatTypeRuleDao;
	
	@Autowired
	private SeatDao seatDao;

	@Override
	public String getSeatTypeForRow(char row) {
		List<SeatTypeRule> rules = seatTypeRuleDao.findAll();
		
		for(SeatTypeRule list : rules) {
			if(row >= list.getStartRow().charAt(0) && row <= list.getEndRow().charAt(0)) {
				return list.getSeatType();
			}
		}

		return "Silver";
	}

	@Override
	public List<Seat> generateSeatsForScreen(Screen screen) {
		List<Seat> seats = new ArrayList<>();
		
		int totalSeats = screen.getTotalSeats();
		int seatsPerRow = 10;
		
		int totalRows = (int) Math.ceil((double) totalSeats /seatsPerRow); 
		
		for(int rowIndex = 0 ; rowIndex < totalRows ; rowIndex++) {
			String rowlabel = getRowLabel(rowIndex);
			
			for(int seatNumber = 1 ; seatNumber < seatsPerRow ; seatNumber++) {
				int currentSeatIndex = rowIndex * seatsPerRow + seatNumber;
				
				if(currentSeatIndex > totalSeats) break;
				
				String seatNum = rowlabel + seatNumber;
				String seatType = getSeatTypeForRow(rowlabel.charAt(0));
				
				Seat seat = new Seat();
				seat.setSeatNumber(seatNum);
				seat.setSeatType(seatType);
				seat.setAvailable(true);
				seat.setScreen(screen);
				
				seats.add(seat);
			}
		}
		
		return seatDao.saveAll(seats);
	}
	
	private String getRowLabel(int index) {
		
		StringBuilder label = new StringBuilder();
		
		while(index >= 0) {
			label.insert(0, (char)('A' + (index % 26)));
			index = (index / 26 )-1;
		}
		return label.toString();
		
	
	}
}
