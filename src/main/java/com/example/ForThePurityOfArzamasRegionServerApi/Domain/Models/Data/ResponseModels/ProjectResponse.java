package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {

    private Integer id;
    private String title;
    private String message;
    private Long upload_time;
    private Long last_modified_time;
    private List<ImageResponse> images;
    private List<ProjectRequestResponse> requests;
    private Integer chat_id;
}
