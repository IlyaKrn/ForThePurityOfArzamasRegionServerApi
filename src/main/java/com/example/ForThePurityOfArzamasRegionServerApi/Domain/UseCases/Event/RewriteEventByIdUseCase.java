package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Event;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.EventRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.EventResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

import java.util.HashMap;

public class RewriteEventByIdUseCase {
    public RewriteEventByIdUseCase(EventRepository projectRepository, ImageRepository imageRepository, Integer event_id, HashMap<String, Object> values) {

    }

    public ResponseModel<EventResponse> execute() {
        return null;
    }
}
