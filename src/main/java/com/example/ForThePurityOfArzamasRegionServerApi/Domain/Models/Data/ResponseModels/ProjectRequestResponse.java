package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestResponse {

    @NonNull
    private Integer id;
    @NonNull
    private Integer user_id;
    @NonNull
    private String message;
    private List<ImageResponse> images;
}
