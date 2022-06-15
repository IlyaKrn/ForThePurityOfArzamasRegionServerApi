package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.User;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.UserRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseError;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
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
        ArrayList<UserResponse> users = new ArrayList<>();
        ArrayList<Integer> ids = new ArrayList<>();
        for (String s : user_ids.split(",")){
            try {
                ids.add(Integer.valueOf(s));
            }
            catch(Exception e) {
                response.setError(new ResponseError("Type casting error", "user id must be integer value", 500));
                return response;
            }
        }

        for(Integer id : ids) {
            try {
                User u = userRepository.findById(id).get();
                try {

                    ImageResponse img = null;
                    if(u.getImage_id() != null){
                        Image i = imageRepository.findById(u.getImage_id()).get();
                        img = new ImageResponse(i.getId(), i.getUrl(), i.getHeight(), i.getWidth());
                    }

                    UserResponse res = new UserResponse(u.getId(), u.getEmail(), u.getPassword(), u.getScore(), u.getFirst_name(), u.getLast_name(), u.getIs_admin(), u.getIs_online(), u.getIs_banned(), u.getIs_verified(), u.getLast_session(), img);
                    users.add(res);
                } catch (Exception e) {
                    response.setError(new ResponseError("Image not found", String.format("image with id %d not found", u.getImage_id()), 500));
                    return response;
                }
            } catch (Exception e){
                response.setError(new ResponseError("User not found", String.format("user with id %d not found", id), 404));
                return response;
            }
        }
        response.setResponse(users);
        return response;
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
    public @ResponseBody String deleteUserById(@RequestParam("user_deleter_id") String user_deleter_id, @RequestParam("user_id") String user_id) {

        return null;
    }

    @GetMapping("users.getIds")
    public @ResponseBody ArrayList<Integer> getUserIds(@RequestParam(value = "count", required = false) Integer count) {

        return null;
    }


}
