package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ChatRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRequestRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.ProjectRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.UserRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectRequestResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Project.*;
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
    @Autowired
    ProjectRequestRepository requestRepository;

    @GetMapping("projects.getListAll")
    public @ResponseBody
    ResponseModel<ArrayList<ProjectResponse>> getProjectListAll() {
        GetProjectListAllUseCase getAllProjectsUseCase = new GetProjectListAllUseCase(projectRepository, imageRepository, requestRepository);
        return getAllProjectsUseCase.execute();
    }

    @GetMapping("projects.getListById")
    public @ResponseBody ResponseModel<ArrayList<UserResponse>> getUserListById(@RequestParam(value = "project_ids") String project_ids) {
        ResponseModel<ArrayList<UserResponse>> response = new ResponseModel<>();
        ArrayList<Integer> ids = new ArrayList<>();
        for (String s : project_ids.split(",")){
            try {
                ids.add(Integer.valueOf(s));
            }
            catch(Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "project_ids ust be integer", e);
            }
        }

        GetProjectListByIdUseCase getProjectListById = new GetProjectListByIdUseCase(projectRepository, imageRepository, ids);
        return getProjectListById.execute();
    }

    @PostMapping("projects.setById")
    public @ResponseBody ResponseModel<UserResponse> setProjectById(@RequestParam("project_id") Integer project_id, @RequestBody HashMap<String, Object> values) {
        RewriteProjectByIdUseCase getAllProjectsUseCase = new RewriteProjectByIdUseCase(projectRepository, imageRepository, project_id, values);
        return getAllProjectsUseCase.execute();
    }

    @PostMapping("projects.create")
    public @ResponseBody ResponseModel<ProjectResponse> createProject(@RequestBody ProjectRequest project) {
        CreateProjectUseCase createProjectUseCase = new CreateProjectUseCase(projectRepository, imageRepository, chatRepository, project);
        return createProjectUseCase.execute();
    }

    @PostMapping("projects.delete")
    public @ResponseBody ResponseModel<UserResponse> deleteProjectById(@RequestParam("project_id") Integer project_id) {
        DeleteProjectUseCase createProjectUseCase = new DeleteProjectUseCase(projectRepository, project_id);
        return createProjectUseCase.execute();
    }

    @GetMapping("projects.getIds")
    public @ResponseBody ResponseModel<ArrayList<Integer>> getProjectIds(@RequestParam(value = "count", required = false) Integer count) {
        if(count == null){
            GetAllProjectIdsUseCase getAllProjectIdsUseCase = new GetAllProjectIdsUseCase(projectRepository);
            return getAllProjectIdsUseCase.execute();
        }
        else {
            GetCountProjectIdsUseCase getCountProjectIdsUseCase = new GetCountProjectIdsUseCase(projectRepository, count);
            return getCountProjectIdsUseCase.execute();
        }

    }

}
