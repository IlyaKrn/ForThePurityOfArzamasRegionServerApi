package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name="chats", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Integer id;
    private Integer[] message_ids;
    private Integer[] banned_user_ids;
}
