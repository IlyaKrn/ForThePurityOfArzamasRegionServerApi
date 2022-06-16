package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.ProjectRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.UserRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.User.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("method")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ImageRepository imageRepository;

    @GetMapping("projects.getListAll")
    public @ResponseBody
    ResponseModel<ArrayList<UserResponse>> getUserListAll() {

        return null;
    }

    @GetMapping("projects.getListById")
    public @ResponseBody ResponseModel<ArrayList<UserResponse>> getUserListById(@RequestParam(value = "user_ids") String user_ids) {

        return null;
    }

    @PostMapping("projects.setById")
    public @ResponseBody ResponseModel<UserResponse> setUserById(@RequestParam("user_id") Integer user_id, @RequestBody HashMap<String, Object> values) {

        return null;
    }

    @PostMapping("projects.create")
    public @ResponseBody ResponseModel<UserResponse> createUser(@RequestBody UserRequest user) {

        return null;
    }

    @PostMapping("projects.delete")
    public @ResponseBody ResponseModel<UserResponse> deleteUserById(@RequestParam("user_id") Integer user_id) {

        return null;
    }

    @GetMapping("projects.getIds")
    public @ResponseBody ArrayList<Integer> getProjectIds(@RequestParam(value = "count", required = false) Integer count) {

        return null;
    }

}
