package com.semptian.neo4j.entity.relationships;

import com.semptian.neo4j.entity.Director;
import com.semptian.neo4j.entity.Person;
import com.semptian.neo4j.entity.base.AbstractEntity;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;

/**
 * @Author Princeliu
 * @Date 2018/8/31 16:11
 * @Description
 */
@RelationshipEntity(type="INVESTED")
public class Invested extends AbstractEntity {

    @StartNode
    private Director director;

    @EndNode
    private Person person;

    @DateString("yyyy-MM-dd HH:mm:ss")
    private Date investTime;

    private double investCount;

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Date getInvestTime() {
        return investTime;
    }

    public void setInvestTime(Date investTime) {
        this.investTime = investTime;
    }

    public double getInvestCount() {
        return investCount;
    }

    public void setInvestCount(double investCount) {
        this.investCount = investCount;
    }
}
