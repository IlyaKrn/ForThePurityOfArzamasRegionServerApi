package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class MessageResponse {

    private Integer id;
    private Integer user_id;
    private List<ImageResponse> images;
    private String message;
}
