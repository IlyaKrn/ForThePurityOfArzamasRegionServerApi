package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;

import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.RequestModels.ProjectRequestRequest;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
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
