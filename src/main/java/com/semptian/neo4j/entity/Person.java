package com.semptian.neo4j.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.semptian.neo4j.entity.base.DescriptiveEntity;
import com.semptian.neo4j.entity.relationships.Invested;
import com.semptian.neo4j.entity.relationships.ReaderOf;
import com.semptian.neo4j.entity.relationships.WriterOf;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

/**
 * @Author Princeliu
 * @Date 2018/8/4 21:02
 * @Description
 */
@NodeEntity
public class Person extends DescriptiveEntity {

    private String phone;
    private String sex;
    @Relationship(type="WRITER_OF")
    @JsonProperty("写作")
    private Set<WriterOf> writings;

    @Relationship(type="READER_OF")
    @JsonProperty("读书")
    private Set<ReaderOf> readBooks;

    @Relationship(type="INVESTED", direction = Relationship.INCOMING)
    @JsonProperty("投资")
    private Set<Invested> investeds;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Set<WriterOf> getWritings() {
        return writings;
    }

    public void setWritings(Set<WriterOf> writings) {
        this.writings = writings;
    }

    public Set<ReaderOf> getReadBooks() {
        return readBooks;
    }

    public void setReadBooks(Set<ReaderOf> readBooks) {
        this.readBooks = readBooks;
    }

    public Set<Invested> getInvesteds() {
        return investeds;
    }

    public void setInvesteds(Set<Invested> investeds) {
        this.investeds = investeds;
    }
}
