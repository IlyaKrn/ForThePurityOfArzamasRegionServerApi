package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequestRequest {

    @NonNull
    private Integer user_id;
    @NonNull
    private String message;
    @Nullable
    private Integer[] image_ids;
}
