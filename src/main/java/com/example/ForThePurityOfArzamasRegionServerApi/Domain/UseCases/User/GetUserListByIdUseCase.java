package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.User;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.UserRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseError;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

public class GetUserListByIdUseCase {

    private UserRepository userRepository;
    private ImageRepository imageRepository;
    private ArrayList<Integer> ids;

    public GetUserListByIdUseCase(UserRepository userRepository, ImageRepository imageRepository, ArrayList<Integer> ids) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.ids = ids;
    }

    public ResponseModel<ArrayList<UserResponse>> execute(){
        ResponseModel<ArrayList<UserResponse>> response = new ResponseModel<>();
        ArrayList<UserResponse> users = new ArrayList<>();
        for(Integer id : ids) {
            try {
                User u = userRepository.findById(id).get();
                ImageResponse img = null;
                if(u.getImage_id() != null){
                    try {
                        Image i = imageRepository.findById(u.getImage_id()).get();
                        img = new ImageResponse(i.getId(), i.getUrl(), i.getHeight(), i.getWidth());
                    } catch (Exception ignored) {

                    }
                }
                UserResponse res = new UserResponse(u.getId(), u.getEmail(), u.getPassword(), u.getScore(), u.getFirst_name(), u.getLast_name(), u.getIs_admin(), u.getIs_online(), u.getIs_banned(), u.getIs_verified(), u.getLast_session(), img);
                users.add(res);
            } catch (Exception ignored){
                users.add(null);
            }
        }
        response.setResponse(users);
        return response;

    }
}
