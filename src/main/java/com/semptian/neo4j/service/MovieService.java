package com.semptian.neo4j.service;

import com.semptian.neo4j.entity.Movie;

import java.util.List;
import java.util.Optional;

/**
 * @Author Princeliu
 * @Date 2018/8/31 15:34
 * @Description
 */
public interface MovieService {

    List<Movie> customQuery(String name);

    Movie findByName(String name, int depth);

    Optional<Movie> findOneById(long id, int depth);

    Movie save(Movie movie);

    List<Movie> getMovieByQuery(String name, int rating);
}
