package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;

import lombok.Data;

import javax.persistence.*;

@Data
public class ProjectRequestResponse {

    private Integer id;
    private Integer user_id;
    private String message;
    private Integer[] image_ids;
}
