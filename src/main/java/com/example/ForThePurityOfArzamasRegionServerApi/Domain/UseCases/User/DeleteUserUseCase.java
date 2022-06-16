package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.User;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseError;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

import java.util.ArrayList;

public class DeleteUserUseCase {

    private UserRepository userRepository;
    private Integer id;
    private Integer deleterId;

    public DeleteUserUseCase(UserRepository userRepository, Integer id, Integer deleterId) {
        this.userRepository = userRepository;
        this.id = id;
        this.deleterId = deleterId;
    }

    public ResponseModel<UserResponse> execute(){
        ResponseModel<UserResponse> response = new ResponseModel<>();
        try {
            if(id.equals(deleterId)) {
                userRepository.deleteById(id);
                response.setResponse(null);
                return response;
            }
            else {
                response.setError(new ResponseError("No have permission for delete user", String.format("user can be deleted by them only"), 400));
                return response;
            }
        } catch (Exception e){
            response.setError(new ResponseError("Internal unexpected server error", String.format("something went wrong: %s", e.getMessage()), 500));
            return response;
        }
    }

}
