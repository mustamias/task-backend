package com.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.model.Movie;

public interface MovieRepository extends MongoRepository<Movie, String>{

}
