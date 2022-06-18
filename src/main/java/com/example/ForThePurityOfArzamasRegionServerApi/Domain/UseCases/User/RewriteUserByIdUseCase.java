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
import java.util.HashMap;

public class RewriteUserByIdUseCase {

    private UserRepository userRepository;
    private ImageRepository imageRepository;
    private Integer id;
    private HashMap<String, Object> values;

    public RewriteUserByIdUseCase(UserRepository userRepository, ImageRepository imageRepository, Integer id, HashMap<String, Object> values) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.id = id;
        this.values = values;
    }

    public ResponseModel<UserResponse> execute(){
        ResponseModel<UserResponse> response = new ResponseModel<>();
        try {
            User u = userRepository.findById(id).get();
            ImageResponse img = null;
            try {
                if(u.getImage_id() != null){
                    Image i = imageRepository.findById(u.getImage_id()).get();
                    img = new ImageResponse(i.getId(), i.getUrl(), i.getHeight(), i.getWidth());
                }
            } catch (Exception ignored) {

            }
            if (values.get("email") != null){
                u.setEmail((String) values.get("email"));
            }
            if (values.get("password") != null){
                u.setPassword((String) values.get("password"));
            }
            if (values.get("first_name") != null){
                u.setFirst_name((String) values.get("first_name"));
            }
            if (values.get("last_name") != null){
                u.setLast_name((String) values.get("last_name"));
            }
            if (values.get("is_online") != null){
                u.setIs_online((Boolean) values.get("is_online"));
                if (values.get("is_online").equals("false"))
                    u.setLast_session(System.currentTimeMillis());
            }
            if (values.get("is_banned") != null){
                u.setIs_banned((Boolean) values.get("is_banned"));
            }
            if (values.get("is_admin") != null){
                u.setIs_admin((Boolean) values.get("is_admin"));
            }
            if (values.get("score") != null){
                u.setScore((Integer) values.get("last_session"));
            }
            if (values.get("is_verified") != null){
                u.setIs_verified((Boolean) values.get("is_verified"));
            }
            if (values.get("image_id") != null){
                u.setImage_id((Integer) values.get("image_id"));
            }
            u = userRepository.findById(id).get();
            UserResponse res = new UserResponse(u.getId(), u.getEmail(), u.getPassword(), u.getScore(), u.getFirst_name(), u.getLast_name(), u.getIs_admin(), u.getIs_online(), u.getIs_banned(), u.getIs_verified(), u.getLast_session(), img);
            response.setResponse(res);
            return response;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user with id %d not found", id), e);
        }
    }
}
