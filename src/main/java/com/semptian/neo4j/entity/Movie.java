package com.semptian.neo4j.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.semptian.neo4j.entity.base.DescriptiveEntity;
import com.semptian.neo4j.entity.relationships.ActorIn;
import com.semptian.neo4j.entity.relationships.Directed;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;
import java.util.Set;

/**
 * @Author Princeliu
 * @Date 2018/8/31 14:15
 * @Description
 */
@NodeEntity
public class Movie extends DescriptiveEntity {

    @Id
    @GeneratedValue
    private Long id;

    private int rating;

    @DateString("yyyy-MM-dd HH:mm:ss")
    private Date releaseDate;

    @Relationship(type = "ACTED_IN", direction = Relationship.INCOMING)
    @JsonProperty("演员")
    private Set<ActorIn> actors;

    @Relationship(type = "DIRECTED", direction = Relationship.INCOMING)
    @JsonProperty("导演")
    private Set<Directed> directors;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Set<ActorIn> getActors() {
        return actors;
    }

    public void setActors(Set<ActorIn> actors) {
        this.actors = actors;
    }

    public Set<Directed> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<Directed> directors) {
        this.directors = directors;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
