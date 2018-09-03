package com.semptian.neo4j.entity.relationships;

import com.semptian.neo4j.entity.Actor;
import com.semptian.neo4j.entity.Movie;
import com.semptian.neo4j.entity.base.AbstractEntity;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * @Author Princeliu
 * @Date 2018/8/31 14:24
 * @Description
 */
@RelationshipEntity(type = "ACTED_ID")
public class ActorIn extends AbstractEntity {

    @StartNode
    private Actor actor;

    @EndNode
    private Movie movie;

    private String role;

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
