package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.RequestModels;

import lombok.Data;

import javax.persistence.*;

@Data
public class UserRequest {

    private Integer email;
    private String password;
    private String score;
    private String first_name;
    private String last_name;
    private Boolean is_admin;
    private Boolean is_online;
    private Boolean is_banned;
    private Boolean is_verified;
    private Long last_session;
    private Integer image_id;

}
