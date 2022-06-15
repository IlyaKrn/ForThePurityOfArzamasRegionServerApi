package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class ProjectRequestResponse {

    private Integer id;
    private Integer user_id;
    private String message;
    private List<ImageResponse> images;
}
