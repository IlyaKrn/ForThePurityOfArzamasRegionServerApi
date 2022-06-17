package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Entity
@Table(name="users", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer id;
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
    @Nullable
    private Long last_session;
    @Nullable
    private Integer image_id;

}
