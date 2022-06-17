package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequest {

    @NonNull
    private String title;
    @NonNull
    private String message;
    @NonNull
    private Integer type;
    private Integer[] image_ids;

}
