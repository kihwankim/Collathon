package com.collathon.backendproject.model.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
@Document(collection = "bicycle")
public class Bicycle {
    @Id
    private long bicycleNumber;
    private Date startDate;
    private Date endDate;
    private double latitude; // 위도
    private double longitude; // 경도
    private long nowUsingPersonId;
    private List<String> lastUserId;

    public Bicycle() {
    }
}
