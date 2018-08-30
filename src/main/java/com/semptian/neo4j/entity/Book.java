package com.semptian.neo4j.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.semptian.neo4j.entity.base.DescriptiveEntity;
import com.semptian.neo4j.entity.relationships.ReaderOf;
import com.semptian.neo4j.entity.relationships.WriterOf;
import org.neo4j.ogm.annotation.*;

import java.util.Set;

/**
 * Entity which represents a book
 */
@NodeEntity
public class Book extends DescriptiveEntity {

   @Id
   @GeneratedValue
   private Long id;

   @Relationship(type="WRITER_OF", direction=Relationship.INCOMING)
   @JsonProperty("作者")
   private Set<WriterOf> writers;

   @Relationship(type="READER_OF", direction=Relationship.INCOMING)
   @JsonProperty("读者")
   private Set<ReaderOf> readers;

   @Override
   public Long getId() {
      return id;
   }

   @Override
   public void setId(Long id) {
      this.id = id;
   }

   public Set<WriterOf> getWriters() {
      return writers;
   }

   public void setWriters(Set<WriterOf> writers) {
      this.writers = writers;
   }

   public Set<ReaderOf> getReaders() {
      return readers;
   }

   public void setReaders(Set<ReaderOf> readers) {
      this.readers = readers;
   }
}