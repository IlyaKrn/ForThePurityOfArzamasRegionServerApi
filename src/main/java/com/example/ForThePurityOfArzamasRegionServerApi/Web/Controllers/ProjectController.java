package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.ProjectRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("method")
public class ProjectController {

    @Autowired
    ProjectRepository userRepository;

    @GetMapping("projects.get")
    public @ResponseBody
    ArrayList<ProjectResponse> getProjects(@RequestParam(value = "project_ids", required = false) String project_ids) {

        return null;
    }


    @PostMapping("projects.setById")
    public @ResponseBody String setProjectById(@RequestParam("project_id") String project_id, @RequestBody HashMap<String, Object> values) {

        return null;
    }


    @PostMapping("projects.create")
    public @ResponseBody String createProject(@RequestBody ProjectRequest project) {

        return null;
    }

    @PostMapping("projects.delete")
    public @ResponseBody String deleteProjectById(@RequestParam("user_deleter_id") String user_deleter_id, @RequestParam("project_id") String project_id) {

        return null;
    }

    @GetMapping("projects.getIds")
    public @ResponseBody ArrayList<Integer> getProjectIds(@RequestParam(value = "count", required = false) Integer count) {

        return null;
    }

}
