package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.RequestModels;

import lombok.Data;

import javax.persistence.*;

@Data
public class EventRequest {

    private String title;
    private String message;
    private Integer type;
    private Integer[] image_ids;

}
