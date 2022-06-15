package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;


import lombok.Data;

import javax.persistence.*;

@Data
public class ChatResponse {

    private Integer id;
    private Integer[] message_ids;
    private Integer[] banned_user_ids;
}
