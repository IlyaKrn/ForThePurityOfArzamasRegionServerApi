package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class ChatResponse {

    private Integer id;
    private List<MessageResponse> messages;
    private Integer[] banned_user_ids;
}
