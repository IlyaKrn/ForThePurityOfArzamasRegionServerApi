package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Locate;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.LocateRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.LocateResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

public class DeleteLocateUseCase {
    public DeleteLocateUseCase(LocateRepository locateRepository, Integer locate_id) {

    }

    public ResponseModel<LocateResponse> execute() {
        return null;
    }
}
