package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="locates", schema = "public")
public class Locate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String message;
    private Long upload_time;
    private Long last_modified_time;
    private Integer[] image_ids;
    private Integer chat_id;
    private String address;
    private Double longitude;
    private Double latitude;
}
