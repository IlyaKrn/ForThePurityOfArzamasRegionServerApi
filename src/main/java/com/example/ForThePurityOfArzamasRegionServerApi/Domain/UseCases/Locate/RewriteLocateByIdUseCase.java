package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Locate;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.LocateRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.LocateResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

import java.util.HashMap;

public class RewriteLocateByIdUseCase {
    public RewriteLocateByIdUseCase(LocateRepository locateRepository, ImageRepository imageRepository, Integer locate_id, HashMap<String, Object> values) {
    }

    public ResponseModel<LocateResponse> execute() {
        return null;
    }
}
