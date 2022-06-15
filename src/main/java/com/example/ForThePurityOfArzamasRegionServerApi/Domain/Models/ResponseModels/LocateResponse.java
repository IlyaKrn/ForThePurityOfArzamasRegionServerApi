package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class LocateResponse {

    private Integer id;
    private String title;
    private String message;
    private Long upload_time;
    private Long last_modified_time;
    private List<ImageResponse> images;
    private Integer chat_id;
    private String address;
    private Double longitude;
    private Double latitude;
}
