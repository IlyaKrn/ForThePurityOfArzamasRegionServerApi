package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models;

import javax.persistence.*;

@Entity
@Table(name="project_requests", schema = "public")
public class ProjectRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;
    private Integer[] image_ids;
}
