package com.semptian.neo4j.entity.relationships;

import com.semptian.neo4j.entity.Book;
import com.semptian.neo4j.entity.Person;
import com.semptian.neo4j.entity.base.AbstractEntity;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.typeconversion.DateString;

import java.util.Date;

/**
 * @Author Princeliu
 * @Date 2018/8/30 11:08
 * @Description
 */
@RelationshipEntity(type="READER_OF")
public class ReaderOf extends AbstractEntity {

    @StartNode
    private Person reader;

    @EndNode
    private Book book;

    @DateString("yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    @DateString("yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    public Person getReader() {
        return reader;
    }

    public void setReader(Person reader) {
        this.reader = reader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
