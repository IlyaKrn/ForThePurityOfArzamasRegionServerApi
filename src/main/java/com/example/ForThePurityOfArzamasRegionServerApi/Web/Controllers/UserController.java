package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.UserRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseError;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("method")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ImageRepository imageRepository;

    @GetMapping("users.getListAll")
    public @ResponseBody ResponseModel<ArrayList<UserResponse>> getUserListAll() {
        GetUserListAllUseCase getAllUsersUseCase = new GetUserListAllUseCase(userRepository, imageRepository);
        return getAllUsersUseCase.execute();
    }
    @GetMapping("users.getListById")
    public @ResponseBody ResponseModel<ArrayList<UserResponse>> getUserListById(@RequestParam(value = "user_ids") String user_ids) {
        ResponseModel<ArrayList<UserResponse>> response = new ResponseModel<>();
        ArrayList<Integer> ids = new ArrayList<>();
        for (String s : user_ids.split(",")){
            try {
                ids.add(Integer.valueOf(s));
            }
            catch(Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user_ids ust be integer", e);
            }
        }

        GetUserListByIdUseCase getUserListById = new GetUserListByIdUseCase(userRepository, imageRepository, ids);
        return getUserListById.execute();
    }
    @GetMapping("users.getListByEmail")
    public @ResponseBody ResponseModel<ArrayList<UserResponse>> getUsersListByEmail(@RequestParam("email") String email) {
        GetUserListByEmailUseCase getByEmail = new GetUserListByEmailUseCase(userRepository, imageRepository, email);
        return getByEmail.execute();
    }

    @PostMapping("users.setById")
    public @ResponseBody ResponseModel<UserResponse> setUserById(@RequestParam("user_id") Integer user_id, @RequestBody HashMap<String, Object> values) {
        RewriteUserByIdUseCase getAllUsersUseCase = new RewriteUserByIdUseCase(userRepository, imageRepository, user_id, values);
        return getAllUsersUseCase.execute();
    }


    @PostMapping("users.create")
    public @ResponseBody ResponseModel<UserResponse> createUser(@RequestBody UserRequest user) {
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userRepository, imageRepository, user);
        return createUserUseCase.execute();
    }

    @PostMapping("users.delete")
    public @ResponseBody ResponseModel<UserResponse> deleteUserById(@RequestParam("user_id") Integer user_id) {
        DeleteUserUseCase deleteUserUseCase = new DeleteUserUseCase(userRepository, user_id);
        return deleteUserUseCase.execute();
    }
}
