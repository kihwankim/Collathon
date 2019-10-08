package com.collathon.backendproject.model.domain;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Calendar;
import java.util.List;

@Data
@Builder
@Document(collection = "bicycle")
public class Bicycle {
    @Id
    private long bicycleNumber;
    private Calendar startDate;
    private Calendar endDate;
    private double latitude; // 위도
    private double longitude; // 경도
    private long nowUsingPersonId;
    private List<String> lastUserId;

}
