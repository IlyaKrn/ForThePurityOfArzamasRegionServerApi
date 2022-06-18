package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Event;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.EventRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Event;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Project;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

import java.util.ArrayList;
import java.util.Comparator;

public class GetCountEventIdsUseCase {

    private EventRepository projectRepository;
    private int count;

    public GetCountEventIdsUseCase(EventRepository projectRepository, int count) {
        this.projectRepository = projectRepository;
        this.count = count;
    }

    public ResponseModel<ArrayList<Integer>> execute() {
        ResponseModel<ArrayList<Integer>> response = new ResponseModel<>();
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<Event> ps = (ArrayList<Event>) projectRepository.findAll();
        ps.sort(new Comparator<Event>() {
            @Override
            public int compare(Event o1, Event o2) {
                return o1.getUpload_time().compareTo(o2.getUpload_time());
            }
        });
        if(ps.size() > 0) {
            for (Event p : ps) {
                ids.add(p.getId());
            }
        }
        if(ids.size() <= count){
            response.setResponse(ids);
        }
        else {
            response.setResponse((ArrayList<Integer>) ids.subList(0, count-1));
        }
        return response;
    }
}
