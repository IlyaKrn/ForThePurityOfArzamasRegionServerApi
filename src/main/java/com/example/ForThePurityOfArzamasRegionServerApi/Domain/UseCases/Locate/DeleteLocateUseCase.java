package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Locate;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.LocateRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.LocateResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DeleteLocateUseCase {

    private LocateRepository locateRepository;
    private Integer id;

    public DeleteLocateUseCase(LocateRepository locateRepository, Integer id) {
        this.locateRepository = locateRepository;
        this.id = id;
    }

    public ResponseModel<LocateResponse> execute() {
        ResponseModel<LocateResponse> response = new ResponseModel<>();
        try{
            locateRepository.deleteById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user with id %d not found", id), e);
        }
        response.setResponse(null);
        return response;
    }
}
