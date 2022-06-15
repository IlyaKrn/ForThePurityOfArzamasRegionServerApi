package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocateRequest {

    private String title;
    private String message;
    private String address;
    private Double longitude;
    private Double latitude;
    private Integer[] image_ids;
}
