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

public class CreateUserUseCase {

    private UserRepository userRepository;
    private ImageRepository imageRepository;
    private UserRequest user;

    public CreateUserUseCase(UserRepository userRepository, ImageRepository imageRepository, UserRequest user) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.user = user;
    }

    public ResponseModel<UserResponse> execute(){
        ResponseModel<UserResponse> response = new ResponseModel<>();
        try {
            if(user.getEmail() == null || user.getPassword() == null || user.getFirst_name() == null || user.getLast_name() == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields 'email', 'password', 'first_name', 'last_name' must be not null", null);
            }
            User u = new User(null, user.getEmail(), user.getPassword(), 0, user.getFirst_name(), user.getLast_name(), false, false, false, false,System.currentTimeMillis(), null);
            User temp = userRepository.save(u);
            ImageResponse image = null;
            if (temp.getImage_id() != null){
                try {
                    Image i = imageRepository.findById(temp.getImage_id()).get();
                    image = new ImageResponse(i.getId(), i.getUrl(), i.getHeight(), i.getWidth());
                } catch (Exception e){
                    image = null;
                }
            }
            response.setResponse(new UserResponse(temp.getImage_id(), temp.getEmail(), temp.getPassword(), temp.getScore(), temp.getFirst_name(), temp.getLast_name(), temp.getIs_admin(), temp.getIs_online(), temp.getIs_banned(), temp.getIs_verified(), temp.getLast_session(), image));
            return response;
        } catch (Exception e) {
            if(e instanceof ResponseStatusException)
                throw e;
            else
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "something went wrong", e);
        }
    }
}
