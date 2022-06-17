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
public class ProjectResponse {

    @NonNull
    private Integer id;
    @NonNull
    private String title;
    @NonNull
    private String message;
    @NonNull
    private Long upload_time;
    private Long last_modified_time;
    private List<ImageResponse> images;
    private List<ProjectRequestResponse> requests;
    @NonNull
    private Integer chat_id;
}
