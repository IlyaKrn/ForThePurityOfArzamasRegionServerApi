package com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectMainRequest {

    @NonNull
    private String title;
    @NonNull
    private String message;
    @NonNull
    private Long upload_time;
    @Nullable
    private Long last_modified_time;
    @Nullable
    private Integer[] image_ids;
}
