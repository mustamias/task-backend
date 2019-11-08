package com.model;

import java.util.ArrayList;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @ToString @NoArgsConstructor
public class Movie {
	
	private String imdbID;
	private String title;
	private String year;
	private String rated;
	private String released;
	private String runtime;
	private String genre;
	private String director;
	private String writer;
	private String actors;
	private String plot;
	private String language;
	private String awards;
	private String country;
	private String poster;
	private ArrayList<Rating> ratings;
	private String metascore;
	private String imdbRating;
	private String imdbVotes;
	private String type;
	private String dvd;
	private String boxOffice;
	private String production;
	private String website;
	private String response;
	private boolean fav;


}
