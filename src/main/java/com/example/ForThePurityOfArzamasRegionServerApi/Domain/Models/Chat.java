package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="chats", schema = "public")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer[] message_ids;
    private Integer[] banned_user_ids;
}
