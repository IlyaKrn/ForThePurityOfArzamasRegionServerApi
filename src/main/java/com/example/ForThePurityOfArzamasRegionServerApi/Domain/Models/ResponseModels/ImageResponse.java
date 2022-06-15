package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;

import lombok.Data;

import javax.persistence.*;

@Data
public class ImageResponse {

    private Integer id;
    private String url;
    private Integer height;
    private Integer width;
}
