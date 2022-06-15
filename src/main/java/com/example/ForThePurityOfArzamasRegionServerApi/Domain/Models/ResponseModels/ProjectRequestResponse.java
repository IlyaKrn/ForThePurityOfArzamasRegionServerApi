package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="project_requests", schema = "public")
public class ProjectRequestResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    private String message;
    private Integer[] image_ids;
}
