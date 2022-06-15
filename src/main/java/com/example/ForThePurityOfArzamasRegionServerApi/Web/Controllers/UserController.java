package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.RequestModels.UserRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("method")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("users.get")
    public @ResponseBody ArrayList<UserResponse> getUsers(@RequestParam(value = "user_ids", required = false) String user_ids) {

        return null;
    }

    @PostMapping("users.setById")
    public @ResponseBody String setUserById(@RequestParam("user_id") String user_id, @RequestBody HashMap<String, Object> values) {

        return null;
    }


    @PostMapping("users.create")
    public @ResponseBody String createUser(@RequestBody UserRequest user) {

        return null;
    }

    @PostMapping("users.delete")
    public @ResponseBody String deleteUserById(@RequestParam("user_id") String user_id) {

        return null;
    }

    @GetMapping("users.getIds")
    public @ResponseBody ArrayList<Integer> getUserIds(@RequestParam(value = "count", required = false) Integer count) {

        return null;
    }


}
