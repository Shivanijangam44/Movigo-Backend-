package com.movigo.service;

import java.util.List;

import com.movigo.entity.Screen;
import com.movigo.entity.Seat;

public interface SeatTypeRuleService {

	public String getSeatTypeForRow(char row);
	
	public List<Seat> generateSeatsForScreen(Screen screen);
}
