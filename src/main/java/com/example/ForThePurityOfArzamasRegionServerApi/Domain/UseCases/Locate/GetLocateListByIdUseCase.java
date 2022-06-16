package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Locate;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.LocateRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.LocateResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

import java.util.ArrayList;

public class GetLocateListByIdUseCase {
    public GetLocateListByIdUseCase(LocateRepository locateRepository, ImageRepository imageRepository, ArrayList<Integer> ids) {
    }

    public ResponseModel<ArrayList<LocateResponse>> execute() {
        return null;
    }
}
