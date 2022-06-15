package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
