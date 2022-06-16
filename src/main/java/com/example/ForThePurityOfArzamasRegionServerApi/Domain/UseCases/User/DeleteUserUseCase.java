package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.User;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseError;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

public class DeleteUserUseCase {

    private UserRepository userRepository;
    private Integer id;

    public DeleteUserUseCase(UserRepository userRepository, Integer id) {
        this.userRepository = userRepository;
        this.id = id;
    }

    public ResponseModel<UserResponse> execute(){
        ResponseModel<UserResponse> response = new ResponseModel<>();
        try {
            userRepository.deleteById(id);
            response.setResponse(null);
            return response;
        } catch (Exception e){
            if(e instanceof ResponseStatusException)
                throw e;
            else
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "something went wrong", e);
        }
    }

}
