package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageResponse {

    @NonNull
    private Integer id;
    @Nullable
    private Integer user_id;
    @Nullable
    private List<ImageResponse> images;
    @Nullable
    private String message;
}
