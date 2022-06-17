package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.*;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.LocateRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.ProjectMainRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.LocateResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Locate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("method")
public class LocateController {

    @Autowired
    LocateRepository locateRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ChatRepository chatRepository;

    @GetMapping("locates.getListAll")
    public @ResponseBody
    ResponseModel<ArrayList<LocateResponse>> getProjectListAll() {
        GetLocateListAllUseCase getAllProjectsUseCase = new GetLocateListAllUseCase(locateRepository, imageRepository);
        return getAllProjectsUseCase.execute();
    }

    @GetMapping("locates.getListById")
    public @ResponseBody ResponseModel<ArrayList<LocateResponse>> getUserListById(@RequestParam(value = "locate_ids") String locate_ids) {
        ResponseModel<ArrayList<UserResponse>> response = new ResponseModel<>();
        ArrayList<Integer> ids = new ArrayList<>();
        for (String s : locate_ids.split(",")){
            try {
                ids.add(Integer.valueOf(s));
            }
            catch(Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "locate_ids must be integer", e);
            }
        }

        GetLocateListByIdUseCase getProjectListById = new GetLocateListByIdUseCase(locateRepository, imageRepository, ids);
        return getProjectListById.execute();
    }

    @PostMapping("locates.setById")
    public @ResponseBody ResponseModel<LocateResponse> setProjectById(@RequestParam("locate_id") Integer locate_id, @RequestBody HashMap<String, Object> values) {
        RewriteLocateByIdUseCase getAllProjectsUseCase = new RewriteLocateByIdUseCase(locateRepository, imageRepository, locate_id, values);
        return getAllProjectsUseCase.execute();
    }

    @PostMapping("locates.create")
    public @ResponseBody ResponseModel<LocateResponse> createProject(@RequestBody LocateRequest locate) {
        CreateLocateUseCase createProjectUseCase = new CreateLocateUseCase(locateRepository, imageRepository, chatRepository, locate);
        return createProjectUseCase.execute();
    }

    @PostMapping("locates.delete")
    public @ResponseBody ResponseModel<LocateResponse> deleteProjectById(@RequestParam("locate_id") Integer locate_id) {
        DeleteLocateUseCase createProjectUseCase = new DeleteLocateUseCase(locateRepository, locate_id);
        return createProjectUseCase.execute();
    }


}
