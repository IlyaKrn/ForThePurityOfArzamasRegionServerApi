package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.RequestModels;

import lombok.Data;

import javax.persistence.*;

@Data
public class ProjectRequest {

    private String title;
    private String message;
    private Integer[] image_ids;
}
