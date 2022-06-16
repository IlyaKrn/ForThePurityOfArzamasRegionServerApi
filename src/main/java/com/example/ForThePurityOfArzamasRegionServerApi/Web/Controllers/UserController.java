package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.UserRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseError;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User.CreateUserUseCase;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User.DeleteUserUseCase;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User.GetAllUsersUseCase;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User.GetUserListByIdUseCase;
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
    @GetMapping("users.getByEmail")
    public @ResponseBody ResponseModel<ArrayList<UserResponse>> getUsersByEmail(@RequestParam("email") String email) {
        ResponseModel<ArrayList<UserResponse>> response = new ResponseModel<>();
        if(email == null){
            GetAllUsersUseCase getAllUsersUseCase = new GetAllUsersUseCase(userRepository, imageRepository);
            return getAllUsersUseCase.execute();
        }
        else{
            response.setError(new ResponseError("Null email", "email can not be null", 400));
            return response;
        }
    }

    @PostMapping("users.setById")
    public @ResponseBody ResponseModel<ArrayList<UserResponse>> setUserById(@RequestParam("user_id") Integer user_id, @RequestBody HashMap<String, Object> values) {
        ResponseModel<ArrayList<UserResponse>> response = new ResponseModel<>();
        if(user_id == null){
            GetAllUsersUseCase getAllUsersUseCase = new GetAllUsersUseCase(userRepository, imageRepository);
            return getAllUsersUseCase.execute();
        }
        else{
            response.setError(new ResponseError("Null user_id", "user_id can not be null", 400));
            return response;
        }
    }


    @PostMapping("users.create")
    public @ResponseBody ResponseModel<UserResponse> createUser(@RequestBody UserRequest user) {
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository, imageRepository, user);
        return createUserUseCase.execute();
    }

    @PostMapping("users.delete")
    public @ResponseBody ResponseModel<UserResponse> deleteUserById(@RequestParam("user_deleter_id") Integer user_deleter_id, @RequestParam("user_id") Integer user_id) {
        ResponseModel<UserResponse> response = new ResponseModel<>();
        if(user_id != null && user_deleter_id != null){
            DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(userRepository, user_id, user_deleter_id);
            return deleteUserUseCase.execute();
        }
        else {
            response.setError(new ResponseError("Id is null", String.format("user id must be not null"), 400));
            return response;
        }
    }
}
