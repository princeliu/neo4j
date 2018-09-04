package com.semptian.neo4j.service.serviceImpl;

import com.semptian.neo4j.entity.Movie;
import com.semptian.neo4j.repository.MovieReporitory;
import com.semptian.neo4j.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Princeliu
 * @Date 2018/8/31 15:37
 * @Description
 */
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieReporitory movieReporitory;

    @Override
    public List<Movie> customQuery(String name) {
        return movieReporitory.customQuery(name);
    }

    @Override
    public Movie findByName(String name, int depth) {
        return movieReporitory.findByName(name, depth);
    }

    @Override
    public Optional<Movie> findOneById(long id, int depth) {
        return movieReporitory.findById(id, depth);
    }

    @Override
    public Movie save(Movie movie) {
        return movieReporitory.save(movie);
    }

    @Override
    public List<Movie> getMovieByQuery(String name, int rating) {
        return movieReporitory.getMovieByQuery(name, rating);
    }
}
