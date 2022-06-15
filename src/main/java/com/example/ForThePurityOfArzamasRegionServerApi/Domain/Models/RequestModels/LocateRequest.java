package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.RequestModels;

import lombok.Data;

import javax.persistence.*;

@Data
public class LocateRequest {

    private String title;
    private String message;
    private String address;
    private Double longitude;
    private Double latitude;
    private Integer[] image_ids;
}
