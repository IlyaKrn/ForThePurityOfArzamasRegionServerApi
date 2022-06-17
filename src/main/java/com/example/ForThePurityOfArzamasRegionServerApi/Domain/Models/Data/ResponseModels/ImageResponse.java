package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {

    @NonNull
    private Integer id;
    @NonNull
    private String url;
    @NonNull
    private Integer height;
    @NonNull
    private Integer width;
}
