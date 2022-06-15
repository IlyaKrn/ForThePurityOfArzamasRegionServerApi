package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.EventRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.EventRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("method")
public class EventController {

    @Autowired
    EventRepository userRepository;

    @GetMapping("events.get")
    public @ResponseBody
    ArrayList<ProjectResponse> getEvents(@RequestParam(value = "event_ids", required = false) String event_ids) {

        return null;
    }


    @PostMapping("events.setById")
    public @ResponseBody String setEventById(@RequestParam("event_id") String event_id, @RequestBody HashMap<String, Object> values) {

        return null;
    }


    @PostMapping("events.create")
    public @ResponseBody String createEvent(@RequestBody EventRequest event) {

        return null;
    }

    @PostMapping("events.delete")
    public @ResponseBody String deleteEventById(@RequestParam("user_deleter_id") String user_deleter_id, @RequestParam("event_id") String event_id) {

        return null;
    }

    @GetMapping("events.getIds")
    public @ResponseBody ArrayList<Integer> getEventIds(@RequestParam(value = "count", required = false) Integer count) {

        return null;
    }

}
