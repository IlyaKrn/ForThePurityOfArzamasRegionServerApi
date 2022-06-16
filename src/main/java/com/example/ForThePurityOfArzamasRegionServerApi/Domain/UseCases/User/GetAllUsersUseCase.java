package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.User;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseError;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

import java.util.ArrayList;

public class GetAllUsersUseCase {

    private UserRepository userRepository;
    private ImageRepository imageRepository;

    public GetAllUsersUseCase(UserRepository userRepository, ImageRepository imageRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
    }

    public ResponseModel<ArrayList<UserResponse>> execute(){
        ResponseModel<ArrayList<UserResponse>> response = new ResponseModel<>();
        try {
            ArrayList<UserResponse> users = new ArrayList<>();
            ArrayList<User> us = (ArrayList<User>) userRepository.findAll();
            if(us.size() > 0) {
                for (User u : us) {
                    ImageResponse img = null;
                    if (u.getImage_id() != null) {
                        try {
                            Image i = imageRepository.findById(u.getImage_id()).get();
                            img = new ImageResponse(i.getId(), i.getUrl(), i.getHeight(), i.getWidth());
                        } catch (Exception e) {
                            img = null;
                        }
                    }
                    UserResponse res = new UserResponse(u.getId(), u.getEmail(), u.getPassword(), u.getScore(), u.getFirst_name(), u.getLast_name(), u.getIs_admin(), u.getIs_online(), u.getIs_banned(), u.getIs_verified(), u.getLast_session(), img);
                    users.add(res);
                }
            }
            else {
                response.setError(new ResponseError("Users not found", String.format("users not found: %s"), 404));
                return response;
            }
            response.setResponse(users);
            return response;
        } catch (Exception e){
            response.setError(new ResponseError("Internal unexpected server error", String.format("something went wrong: %s", e.getMessage()), 500));
            return response;
        }
    }
}
