package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.Exception.MovieNotFoundException;
import com.model.AppUser;
import com.model.Movie;
import com.service.IUserService;
import com.service.ImovieService;

@RestController
public class Controller {

	@Autowired
	private ImovieService movieService;
	
	@Autowired
	private IUserService userService;
	
	
	@GetMapping("find/{title}")
	public ResponseEntity<Movie> findMovieByTitle(@PathVariable String title){
		
		try {
			Movie movie = movieService.findMovie(title);
			return  ResponseEntity.ok(movie);
		}catch(MovieNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
	
	@PostMapping("register")
	public ResponseEntity<AppUser> register(@RequestBody AppUser newUser) {
		
		try {
			return  ResponseEntity.ok(userService.saveUser(newUser));
		}catch(MovieNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}	
	}
	
	@PostMapping("movie/add")
	public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
		
		try {
			return  ResponseEntity.ok(movieService.addMovie(movie));
		}catch(MovieNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage());
		}	
	}
	
	@GetMapping("movie")
	public ResponseEntity<List<Movie>> favoriteMovie(){
		
		try {
			return  ResponseEntity.ok(movieService.favotiteMovies());
		}catch(MovieNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		
	}
}
