package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;

import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.DatabaseModels.Image;
import lombok.Data;

import javax.persistence.*;

@Data
public class UserResponse {

    private Integer id;
    private String email;
    private String password;
    private Integer score;
    private String first_name;
    private String last_name;
    private Boolean is_admin;
    private Boolean is_online;
    private Boolean is_banned;
    private Boolean is_verified;
    private Long last_session;
    private ImageResponse image;

}
