package com.service;

import java.util.List;
import java.util.Optional;

import com.model.Movie;

public interface ImovieService {
	
	public Movie findMovie(String title);
	public Movie addMovie(Movie movie);
	public Optional<Movie> findMovieFromDB(String movieId);
	public List<Movie> favotiteMovies ();


}
