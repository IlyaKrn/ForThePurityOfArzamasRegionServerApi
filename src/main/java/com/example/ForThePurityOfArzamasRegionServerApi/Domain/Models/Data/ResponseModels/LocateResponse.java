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
public class LocateResponse {

    @NonNull
    private Integer id;
    @NonNull
    private String title;
    @NonNull
    private String message;
    @NonNull
    private Long upload_time;
    @NonNull
    private Long last_modified_time;
    @Nullable
    private List<ImageResponse> images;
    @NonNull
    private Integer chat_id;
    @NonNull
    private String address;
    @NonNull
    private Double longitude;
    @NonNull
    private Double latitude;
}
