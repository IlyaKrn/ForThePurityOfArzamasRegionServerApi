package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.RequestModels;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="messages", schema = "public")
public class MessageRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private Integer[] image_ids;
    private String message;
}
