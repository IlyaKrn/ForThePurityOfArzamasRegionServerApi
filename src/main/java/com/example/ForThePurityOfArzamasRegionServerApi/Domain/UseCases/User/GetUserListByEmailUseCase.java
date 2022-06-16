package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
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

public class GetUserListByEmailUseCase {
    private UserRepository userRepository;
    private ImageRepository imageRepository;
    private String email;

    public GetUserListByEmailUseCase(UserRepository userRepository, ImageRepository imageRepository, String email) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.email = email;
    }

    public ResponseModel<ArrayList<UserResponse>> execute(){
        ResponseModel<ArrayList<UserResponse>> response = new ResponseModel<>();

        ArrayList<UserResponse> users = new ArrayList<>();
        ArrayList<User> us = (ArrayList<User>) userRepository.findAll();
        if(us.size() > 0) {
            for (User u : us) {
                if(u.getEmail().equals(email)) {
                    ImageResponse img = null;
                    if (u.getImage_id() != null) {
                        try {
                            Image i = imageRepository.findById(u.getImage_id()).get();
                            img = new ImageResponse(i.getId(), i.getUrl(), i.getHeight(), i.getWidth());
                        } catch (Exception ignored) {

                        }
                    }
                    UserResponse res = new UserResponse(u.getId(), u.getEmail(), u.getPassword(), u.getScore(), u.getFirst_name(), u.getLast_name(), u.getIs_admin(), u.getIs_online(), u.getIs_banned(), u.getIs_verified(), u.getLast_session(), img);
                    users.add(res);
                }
            }
        }
        else {
            users.add(null);
            response.setResponse(users);
            return response;
        }
        response.setResponse(users);
        return response;

    }
}
