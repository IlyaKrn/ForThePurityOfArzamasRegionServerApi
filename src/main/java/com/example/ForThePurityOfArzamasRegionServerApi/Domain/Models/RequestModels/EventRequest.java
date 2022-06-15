package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.RequestModels;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="events", schema = "public")
public class EventRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String message;
    private Long upload_time;
    private Long last_modified_time;
    private Integer type;
    private Integer[] image_ids;
    private Integer[] member_ids;
    private Integer chat_id;

}
