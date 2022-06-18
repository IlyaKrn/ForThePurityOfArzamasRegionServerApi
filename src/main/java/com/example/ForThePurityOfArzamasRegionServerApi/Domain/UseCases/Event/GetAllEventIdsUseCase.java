package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Event;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.EventRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

import java.util.ArrayList;

public class GetAllEventIdsUseCase {
    public GetAllEventIdsUseCase(EventRepository projectRepository) {

    }

    public ResponseModel<ArrayList<Integer>> execute() {
        return null;
    }
}
