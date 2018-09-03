package com.semptian.neo4j.entity.relationships;

import com.semptian.neo4j.entity.Director;
import com.semptian.neo4j.entity.Movie;
import com.semptian.neo4j.entity.base.AbstractEntity;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * @Author Princeliu
 * @Date 2018/8/31 14:25
 * @Description
 */
@RelationshipEntity(type="DIRECTED")
public class Directed extends AbstractEntity {

    @StartNode
    private Director director;

    @EndNode
    private Movie movie;


    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
