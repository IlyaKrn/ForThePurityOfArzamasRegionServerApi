package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="images", schema = "public")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url;
    private Integer height;
    private Integer width;
}
