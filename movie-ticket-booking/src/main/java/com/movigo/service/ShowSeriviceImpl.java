package com.movigo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movigo.dao.MovieDao;
import com.movigo.dao.ScreenDao;
import com.movigo.dao.ShowDao;
import com.movigo.dto.showDtos.ShowCreateDto;
import com.movigo.dto.showDtos.ShowDto;
import com.movigo.dto.showDtos.ShowUpdateDto;
import com.movigo.entity.Movie;
import com.movigo.entity.Screen;
import com.movigo.entity.Show;
import com.movigo.exceptions.BusinessException;

@Service
@Transactional
public class ShowSeriviceImpl implements ShowService {
    
	@Autowired
	public ShowDao showDao;
	
	@Autowired
	public ModelMapper modelmapper;
	
	@Autowired 
	public MovieDao movieDao;
	
	@Autowired
	public ScreenDao screenDao;
	
	@Override
	public ShowDto addShow(ShowCreateDto showCreateDto) {
		Movie movie = movieDao.findById(showCreateDto.getMovieId())
				.orElseThrow(() -> new BusinessException("Movie not found for Id "+showCreateDto.getMovieId()));
		Screen screen = screenDao.findById(showCreateDto.getScreenId())
				.orElseThrow(() -> new BusinessException("Screen not found for Id "+showCreateDto.getScreenId()));
		 
		Show show = new Show();
		show.setShowTime(showCreateDto.getShowTime());
		show.setMovie(movie);
		show.setScreen(screen);
		
		Show dto = showDao.save(show);
		
		ShowDto showDto = new ShowDto();
		
		showDto.setShowId(dto.getShowId());
		showDto.setShowTime(dto.getShowTime());
		showDto.setMovieId(movie.getMovieId());
		showDto.setMovieName(movie.getMovieName());
		showDto.setScreenId(screen.getScreenId());
		
		return showDto ;
	}

	@Override
	public void deleteShow(Long showId) {
		if(!showDao.existsById(showId))
			throw new BusinessException("Show not found for Id "+showId);
		
		showDao.deleteById(showId);
	}

	@Override
	public List<ShowDto> getShowByMovie(String movieName) {
		List<ShowDto> showDtoList = new ArrayList<>();
		List<Show> showList = showDao.findByMovieMovieName(movieName);
		
		showList.forEach(show -> {
			ShowDto showDto = modelmapper.map(show, ShowDto.class);
			showDto.setMovieId(show.getMovie().getMovieId());
			showDto.setMovieName(show.getMovie().getMovieName());
			showDto.setScreenId(show.getScreen().getScreenId());
			
			showDtoList.add(showDto);
		});
		
		return showDtoList;
	}

	@Override
	public ShowDto getShowById(Long showId) {
		Show show = showDao.findById(showId)
				.orElseThrow(() -> new BusinessException("Show not found for Id "+showId));
		
		ShowDto dto = modelmapper.map(show, ShowDto.class);
		dto.setMovieId(show.getMovie().getMovieId());
		dto.setMovieName(show.getMovie().getMovieName());
		dto.setScreenId(show.getScreen().getScreenId());
		
		return dto;
	}

	@Override
	public List<ShowDto> getShowByScreen(Long screenId) {
		List<ShowDto> showDtoList = new ArrayList<>();
		
		List<Show> showList = showDao.findByScreenScreenId(screenId);
		
		showList.forEach(show -> {
			ShowDto showDto = modelmapper.map(show,ShowDto.class);
			showDto.setMovieId(show.getMovie().getMovieId());
			showDto.setMovieName(show.getMovie().getMovieName());
			showDto.setScreenId(show.getScreen().getScreenId());
			showDtoList.add(showDto);
	       
		});
				
		return showDtoList;
	}

	@Override
	public ShowDto updateShow(List<ShowUpdateDto> showUpdateDto, Long showId) {
		Show show = showDao.findById(showId)
				.orElseThrow(() -> new BusinessException("Show not found for Id "+showId));
		
		showUpdateDto.forEach(item -> {
			if("showTime".equals(item.getFieldName())){
			show.setShowTime(LocalDateTime.parse(item.getValue()));
			} else if("movieId".equals(item.getFieldName())) {
				Long movieId = Long.parseLong(item.getValue());
			    Movie movie = movieDao.findById(movieId)
					.orElseThrow(() -> new BusinessException("Movie not found for Id "+movieId));
			    show.setMovie(movie);
			} else if("screenId".equals(item.getFieldName())) {
				Long screenId = Long.parseLong(item.getValue());
				Screen screen =screenDao.findById(screenId)
					.orElseThrow(() -> new BusinessException("Screen not found for Id "+screenId));
				show.setScreen(screen);
			} else {
				throw new BusinessException("Invalid field name "+item.getFieldName());
			}
		});
		Show savedShow = showDao.save(show);
		ShowDto dto = modelmapper.map(savedShow, ShowDto.class);
		dto.setMovieId(savedShow.getMovie().getMovieId());
		dto.setMovieName(savedShow.getMovie().getMovieName());
		dto.setScreenId(savedShow.getScreen().getScreenId());
		return dto;
	}

}
