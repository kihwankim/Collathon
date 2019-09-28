package com.collathon.backendproject.model.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@ToString
@Document(collection = "bicycle")
public class Bicycle {
    @Id
    private long id;
    private Date stratDate;
    private Date arriveDate;
    private double latitude; // 위도
    private double longitude; // 경도
}
