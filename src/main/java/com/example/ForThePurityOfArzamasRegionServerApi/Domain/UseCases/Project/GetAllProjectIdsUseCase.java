package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Project;

import antlr.ASTNULLType;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Project;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.ProjectRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectRequestResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

import java.util.ArrayList;
import java.util.Comparator;

public class GetAllProjectIdsUseCase {

    private ProjectRepository projectRepository;

    public GetAllProjectIdsUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
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
        response.setResponse(ids);
        return response;
    }
}
