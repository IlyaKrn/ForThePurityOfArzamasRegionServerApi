package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.User;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.UserRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseError;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.CreateUserUseCase;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.GetAllUsersUseCase;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.GetUserListByIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("method")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;

    @GetMapping("users.getById")
    public @ResponseBody ResponseModel<ArrayList<UserResponse>> getUsers(@RequestParam(value = "user_ids", required = false) String user_ids) {
        ResponseModel<ArrayList<UserResponse>> response = new ResponseModel<>();
        if(user_ids == null){
            GetAllUsersUseCase getAllUsersUseCase = new GetAllUsersUseCase(userRepository, imageRepository);
            return getAllUsersUseCase.execute();
        }
        else{
            ArrayList<Integer> ids = new ArrayList<>();
            for (String s : user_ids.split(",")){
                try {
                    ids.add(Integer.valueOf(s));
                }
                catch(Exception e) {
                    response.setError(new ResponseError("Type casting error", String.format("user id must be integer value: %s", e.getMessage()), 400));
                    return response;
                }
            }
            GetUserListByIdUseCase getUserListById = new GetUserListByIdUseCase(userRepository, imageRepository, ids);
            return getUserListById.execute();
        }

    }

    @PostMapping("users.setById")
    public @ResponseBody String setUserById(@RequestParam("user_id") String user_id, @RequestBody HashMap<String, Object> values) {

        return null;
    }


    @PostMapping("users.create")
    public @ResponseBody ResponseModel<UserResponse> createUser(@RequestBody UserRequest user) {
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository, imageRepository, user);
        return createUserUseCase.execute();
    }

    @PostMapping("users.delete")
    public @ResponseBody String deleteUserById(@RequestParam("user_deleter_id") String user_deleter_id, @RequestParam("user_id") String user_id) {

        return null;
    }
}
