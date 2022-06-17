package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatResponse {

    @NonNull
    private Integer id;
    private List<MessageResponse> messages;
    private Integer[] banned_user_ids;
}
