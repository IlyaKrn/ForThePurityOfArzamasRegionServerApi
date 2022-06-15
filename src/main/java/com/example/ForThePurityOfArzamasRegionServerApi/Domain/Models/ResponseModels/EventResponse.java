package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class EventResponse {

    private Integer id;
    private String title;
    private String message;
    private Long upload_time;
    private Long last_modified_time;
    private Integer type;
    private List<ImageResponse> images;
    private Integer[] member_ids;
    private Integer chat_id;

}
