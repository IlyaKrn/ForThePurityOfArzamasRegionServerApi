package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMainRequest {

    @NonNull
    private String title;
    @NonNull
    private String message;
    @NonNull
    private Long upload_time;
    private Long last_modified_time;
    private Integer[] image_ids;
}
