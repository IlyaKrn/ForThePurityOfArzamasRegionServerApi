package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Event;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.EventRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.EventResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

public class DeleteEventUseCase {
    public DeleteEventUseCase(EventRepository projectRepository, Integer event_id) {

    }

    public ResponseModel<EventResponse> execute() {
        return null;
    }
}
