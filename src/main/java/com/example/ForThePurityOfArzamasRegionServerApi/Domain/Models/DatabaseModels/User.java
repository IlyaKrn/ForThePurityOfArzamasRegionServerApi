package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.DatabaseModels;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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