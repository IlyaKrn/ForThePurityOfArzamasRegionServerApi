package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.*;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.EventRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.ProjectMainRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.EventResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Event.*;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Project.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("method")
public class EventController {

    @Autowired
    EventRepository projectRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    ChatRepository chatRepository;

    @GetMapping("events.getListAll")
    public @ResponseBody
    ResponseModel<ArrayList<EventResponse>> getProjectListAll() {
        GetEventListAllUseCase getAllProjectsUseCase = new GetEventListAllUseCase(projectRepository, imageRepository);
        return getAllProjectsUseCase.execute();
    }

    @GetMapping("events.getListById")
    public @ResponseBody ResponseModel<ArrayList<EventResponse>> getUserListById(@RequestParam(value = "event_ids") String event_ids) {
        ArrayList<Integer> ids = new ArrayList<>();
        for (String s : event_ids.split(",")){
            try {
                ids.add(Integer.valueOf(s));
            }
            catch(Exception e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "event_ids ust be integer", e);
            }
        }

        GetEventListByIdUseCase getProjectListById = new GetEventListByIdUseCase(projectRepository, imageRepository, ids);
        return getProjectListById.execute();
    }

    @PostMapping("events.setById")
    public @ResponseBody ResponseModel<EventResponse> setProjectById(@RequestParam("event_id") Integer event_id, @RequestBody HashMap<String, Object> values) {
        RewriteEventByIdUseCase getAllProjectsUseCase = new RewriteEventByIdUseCase(projectRepository, imageRepository, event_id, values);
        return getAllProjectsUseCase.execute();
    }

    @PostMapping("events.create")
    public @ResponseBody ResponseModel<EventResponse> createProject(@RequestBody EventRequest event) {
        CreateEventUseCase createProjectUseCase = new CreateEventUseCase(projectRepository, imageRepository, chatRepository, event);
        return createProjectUseCase.execute();
    }

    @PostMapping("events.delete")
    public @ResponseBody ResponseModel<EventResponse> deleteProjectById(@RequestParam("event_id") Integer event_id) {
        DeleteEventUseCase createProjectUseCase = new DeleteEventUseCase(projectRepository, event_id);
        return createProjectUseCase.execute();
    }

    @GetMapping("events.getIds")
    public @ResponseBody ResponseModel<ArrayList<Integer>> getProjectIds(@RequestParam(value = "count", required = false) Integer count) {
        if(count == null){
            GetAllEventIdsUseCase getAllProjectIdsUseCase = new GetAllEventIdsUseCase(projectRepository);
            return getAllProjectIdsUseCase.execute();
        }
        else {
            GetCountEventIdsUseCase getCountProjectIdsUseCase = new GetCountEventIdsUseCase(projectRepository, count);
            return getCountProjectIdsUseCase.execute();
        }

    }

}
