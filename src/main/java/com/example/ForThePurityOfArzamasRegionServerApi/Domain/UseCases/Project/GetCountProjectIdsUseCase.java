package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Project;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Project;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

import java.util.ArrayList;
import java.util.Comparator;

public class GetCountProjectIdsUseCase {

    private ProjectRepository projectRepository;
    private int count;

    public GetCountProjectIdsUseCase(ProjectRepository projectRepository, int count) {
        this.projectRepository = projectRepository;
        this.count = count;
    }

    public ResponseModel<ArrayList<Integer>> execute() {
        ResponseModel<ArrayList<Integer>> response = new ResponseModel<>();
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<Project> ps = (ArrayList<Project>) projectRepository.findAll();
        ps.sort(new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return o1.getUpload_time().compareTo(o2.getUpload_time());
            }
        });
        if(ps.size() > 0) {
            for (Project p : ps) {
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
