package com.movigo.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movigo.dao.MovieDao;
import com.movigo.dto.movieDtos.MovieDto;
import com.movigo.dto.movieDtos.MovieUpdateDto;
import com.movigo.entity.Movie;



@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao movieDao;
	
	@Autowired
	private ModelMapper modelmapper;
	
	@Value("${image.upload.dir}")
    private String uploadDirPath;
	
	
	@Override
	public MovieDto addMovie(MovieDto movieDto) {
		Movie movie = modelmapper.map(movieDto,Movie.class);
		
//		 MultipartFile file = movieDto.getImageurl();
//		if(file != null && !file.isEmpty()) {
//			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//			Path filepath =Paths.get(uploadDirPath,fileName).toAbsolutePath().normalize();
//			try {
//				Files.createDirectories(filepath.getParent());
//				Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
//			}catch(IOException e) {
//				throw new BusinessException("Unable to upload image");
//			}
//			movie.setImageurl(fileName);
//		}
		movie = movieDao.save(movie);
				
		return modelmapper.map(movie, MovieDto.class);
	}

	@Override
	public List<MovieDto> getAllMovies() {
		List<MovieDto> moviesDtoList = new ArrayList<>();
		List<Movie> movieList = movieDao.findAll();
		
		movieList.forEach(movies->{
			MovieDto movieDto = modelmapper.map(movies, MovieDto.class);
			moviesDtoList.add(movieDto);
		});
		return moviesDtoList;
	}

	@Override
	public List<MovieDto> getMovieByMovieName(String MovieName) {
		List<MovieDto> movieDtoList = new ArrayList<>();
		List<Movie> movieList = movieDao.findByMovieName(MovieName);
		movieList.forEach(movies->{
			MovieDto movieDto = modelmapper.map(movies, MovieDto.class);
			movieDtoList.add(movieDto);
		}
		);
		
		return movieDtoList;
	}

	@Override
	public MovieDto updateMovieDetails(List<MovieUpdateDto> movieUpdateDto, Long movieId) {
	    Optional<Movie> OptionalMovie = movieDao.findById(movieId);
	    Movie movie = OptionalMovie.orElse(null);
	    
	    if(movie !=null) {
	    	movieUpdateDto.forEach(dto->{
	    		if("movieName".equals(dto.getFieldName())) {
	    			movie.setMovieName(dto.getValue());
	    		}
	    		else if("genre".equals(dto.getFieldName())) {
	    			movie.setGenre(dto.getValue());
	    		}
	    		else if("duration".equals(dto.getFieldName())) {
	    			movie.setDuration(Integer.parseInt(dto.getValue()));
	    		}
	    		else if("language".equals(dto.getFieldName())) {
	    			movie.setLanguage(dto.getValue());
	    		}
	    		else if("releaseDate".equals(dto.getFieldName())) {
	    			movie.setReleaseDate(LocalDate.parse(dto.getValue()));
	    		}
	    		else if("imageurl".equals(dto.getFieldName())) {
	    			movie.setImageUrl(dto.getValue());
	    		}
	    	});
	    	
	    	MovieDto movieDto = modelmapper.map(movieDao.save(movie),MovieDto.class);
	    	
	    	return movieDto;
	    }
		return null;
	}
	
	@Override
	public void deleteMovie(Long movieId) {
		movieDao.deleteById(movieId);
		
	}

	@Override
	public List<MovieDto> getMovieByGenre(String Genre) {
		List<MovieDto> movieDtoList = new ArrayList<>();
		List<Movie> movieList = movieDao.findByGenre(Genre);
		movieList.forEach(movies -> {
			 MovieDto movieDto = modelmapper.map(movies,MovieDto.class);
			 movieDtoList.add(movieDto);
			 
		});
		
		return movieDtoList;
	}

	@Override
	public List<MovieDto> getMovieByLanguage(String Language) {
	    List<MovieDto> movieDtoList = new ArrayList<>();
	    List<Movie> movieList = movieDao.findByLanguage(Language);
	    movieList.forEach(movie ->{
	    	MovieDto movieDto = modelmapper.map(movie, MovieDto.class);
	    	movieDtoList.add(movieDto);
	    	
	    });
		return movieDtoList;
	}

	
	

	



}
