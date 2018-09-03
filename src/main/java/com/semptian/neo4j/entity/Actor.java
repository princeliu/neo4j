package com.semptian.neo4j.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.semptian.neo4j.entity.relationships.ActorIn;
import com.semptian.neo4j.entity.relationships.Directed;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * @Author Princeliu
 * @Date 2018/8/31 14:27
 * @Description
 */
@NodeEntity
public class Actor extends Person{

    private int fans;
    private double moviePayment;

    @Relationship(type="ACTED_IN")
    @JsonProperty("参演")
    private Set<ActorIn> actorIns;

    @Relationship(type="DIRECTED")
    @JsonProperty("导演")
    private Set<Directed> directeds;

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public double getMoviePayment() {
        return moviePayment;
    }

    public void setMoviePayment(double moviePayment) {
        this.moviePayment = moviePayment;
    }

    public Set<ActorIn> getActorIns() {
        return actorIns;
    }

    public void setActorIns(Set<ActorIn> actorIns) {
        this.actorIns = actorIns;
    }

    public Set<Directed> getDirecteds() {
        return directeds;
    }

    public void setDirecteds(Set<Directed> directeds) {
        this.directeds = directeds;
    }
}
