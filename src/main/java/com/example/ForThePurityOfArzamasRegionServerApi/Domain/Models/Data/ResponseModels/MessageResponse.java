package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    @NonNull
    private Integer id;
    private Integer user_id;
    private List<ImageResponse> images;
    private String message;
}
