package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private Integer score;
    @NonNull
    private String first_name;
    @NonNull
    private String last_name;
    @NonNull
    private Boolean is_admin;
    @NonNull
    private Boolean is_online;
    @NonNull
    private Boolean is_banned;
    @NonNull
    private Boolean is_verified;
    private Long last_session;
    private Integer image_id;

}
