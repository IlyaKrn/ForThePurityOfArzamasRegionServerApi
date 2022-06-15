package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name="chats", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer[] message_ids;
    private Integer[] banned_user_ids;
}
