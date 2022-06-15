package com.example.ForThePurityOfArzamasRegionServerApi.Web.Controllers;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.LocateRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.RequestModels.LocateRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.RequestModels.ProjectRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels.LocateResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.ResponseModels.ProjectResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("method")
public class LocateController {

    @Autowired
    LocateRepository userRepository;

    @GetMapping("locates.get")
    public @ResponseBody
    ArrayList<LocateResponse> getLocates(@RequestParam(value = "locate_ids", required = false) String locate_ids) {

        return null;
    }


    @PostMapping("locates.setById")
    public @ResponseBody String setLocateById(@RequestParam("locate_id") String locate_id, @RequestBody HashMap<String, Object> values) {

        return null;
    }


    @PostMapping("locates.create")
    public @ResponseBody String createLocate(@RequestBody LocateRequest locate) {

        return null;
    }

    @PostMapping("locates.delete")
    public @ResponseBody String deleteLocateById(@RequestParam("user_deleter_id") String user_deleter_id, @RequestParam("locate_id") String locate_id) {

        return null;
    }

    @GetMapping("locates.getIds")
    public @ResponseBody ArrayList<Integer> getLocateIds(@RequestParam(value = "count", required = false) Integer count) {

        return null;
    }


}
