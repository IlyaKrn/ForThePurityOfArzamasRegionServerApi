package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models;

import javax.persistence.*;

@Entity
@Table(name="messages", schema = "public")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private Integer[] image_ids;
    private String message;
}
