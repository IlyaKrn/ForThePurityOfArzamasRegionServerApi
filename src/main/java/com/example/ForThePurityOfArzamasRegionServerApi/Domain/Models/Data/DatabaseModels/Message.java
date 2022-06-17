package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Data
@Entity
@Table(name="messages", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    @Nullable
    private Integer user_id;
    @Nullable
    private Integer[] image_ids;
    @Nullable
    private String message;
}
