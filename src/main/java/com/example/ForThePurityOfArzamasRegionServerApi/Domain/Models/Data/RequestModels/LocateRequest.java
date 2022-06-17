package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocateRequest {

    @NonNull
    private String title;
    @NonNull
    private String message;
    @NonNull
    private String address;
    @NonNull
    private Double longitude;
    @NonNull
    private Double latitude;
    private Integer[] image_ids;
}
