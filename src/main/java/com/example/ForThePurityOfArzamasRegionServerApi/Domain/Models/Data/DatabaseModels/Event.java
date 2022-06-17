package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Entity
@Table(name="events", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    @NonNull
    private String title;
    @NonNull
    private String message;
    @NonNull
    private Long upload_time;
    @Nullable
    private Long last_modified_time;
    @NonNull
    private Integer type;
    @Nullable
    private Integer[] image_ids;
    @Nullable
    private Integer[] member_ids;
    @NonNull
    private Integer chat_id;

}
