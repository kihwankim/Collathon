package com.collathon.backendproject.sequence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "sequence")
public class Sequence {
    @Id
    private String id;
    private long seq;
}
