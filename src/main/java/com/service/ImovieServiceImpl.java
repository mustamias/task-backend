package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Exception.MovieNotFoundException;
import com.dao.MovieRepository;
import com.model.Movie;

@Service
public class ImovieServiceImpl implements ImovieService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private MovieRepository movieRepository;

	@Override
	public Movie findMovie(String title) {
		try {
			Movie movie = restTemplate.getForObject("http://www.omdbapi.com/?apikey=5d52337f&t="+title, Movie.class);
			return movie;
		}catch (Exception e) {
			System.out.println("error");
			throw new MovieNotFoundException("movie not found");
		}
	        
	
	}

	@Override
	public Movie addMovie(Movie movie) {
				
		return movieRepository.save(movie);
	}

	@Override
	public Optional<Movie>  findMovieFromDB (String movieId) {
		return movieRepository.findById(movieId);
	}

	@Override
	public List<Movie> favotiteMovies() {
		// TODO Auto-generated method stub
		return movieRepository.findAll();
	}

}
