package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ChatRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.ProjectRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.UserRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Project.CreateProjectUseCase;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Project.GetProjectListAllUseCase;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Project.GetProjectListByIdUseCase;
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
    @Autowired
    ChatRepository chatRepository;

    @GetMapping("projects.getListAll")
    public @ResponseBody
    ResponseModel<ArrayList<ProjectResponse>> getUserListAll() {
        GetProjectListAllUseCase getAllProjectsUseCase = new GetProjectListAllUseCase(projectRepository, imageRepository);
        return getAllProjectsUseCase.execute();
    }

    @GetMapping("projects.getListById")
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

      //  GetProjectListByIdUseCase getProjectListById = new GetProjectListByIdUseCase(projectRepository, imageRepository, ids);
        return null;//getProjectListById.execute();
    }

    @PostMapping("projects.setById")
    public @ResponseBody ResponseModel<UserResponse> setUserById(@RequestParam("user_id") Integer user_id, @RequestBody HashMap<String, Object> values) {

        return null;
    }

    @PostMapping("projects.create")
    public @ResponseBody ResponseModel<ProjectResponse> createUser(@RequestBody ProjectRequest project) {
        CreateProjectUseCase createProjectUseCase = new CreateProjectUseCase(projectRepository, imageRepository, chatRepository, project);
        return createProjectUseCase.execute();
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
