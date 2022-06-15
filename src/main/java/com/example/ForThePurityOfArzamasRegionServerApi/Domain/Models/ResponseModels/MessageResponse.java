package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;

import lombok.Data;

import javax.persistence.*;

@Data
public class MessageResponse {

    private Integer id;
    private Integer user_id;
    private Integer[] image_ids;
    private String message;
}
