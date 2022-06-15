package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="messages", schema = "public")
public class MessageResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private Integer[] image_ids;
    private String message;
}
