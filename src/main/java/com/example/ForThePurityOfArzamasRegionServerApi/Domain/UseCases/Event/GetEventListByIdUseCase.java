package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Event;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.EventRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.EventResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

import java.util.ArrayList;

public class GetEventListByIdUseCase {
    public GetEventListByIdUseCase(EventRepository projectRepository, ImageRepository imageRepository, ArrayList<Integer> ids) {

    }

    public ResponseModel<ArrayList<EventResponse>> execute() {
        return null;
    }
}
