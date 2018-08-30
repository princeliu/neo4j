package com.semptian.neo4j.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.semptian.neo4j.entity.relationships.ReaderOf;
import com.semptian.neo4j.entity.relationships.WriterOf;
import org.neo4j.ogm.annotation.*;
import org.springframework.data.annotation.Id;

import java.util.Set;

/**
 * @Author Princeliu
 * @Date 2018/8/4 21:02
 * @Description
 */
@NodeEntity
public class Person {
    @Id
    private Long id;
    private String name ;
    private String phone;
    private String sex;
    @Relationship(type="WRITER_OF")
    @JsonProperty("写作")
    private Set<WriterOf> writings;

    @Relationship(type="READER_OF")
    @JsonProperty("读书")
    private Set<ReaderOf> readBooks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
