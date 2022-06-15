package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.RequestModels;

import lombok.Data;

import javax.persistence.*;

@Data
public class ProjectRequestRequest {

    private Integer user_id;
    private String message;
    private Integer[] image_ids;
}
