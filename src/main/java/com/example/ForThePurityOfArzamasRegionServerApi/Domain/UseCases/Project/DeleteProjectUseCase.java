package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Project;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DeleteProjectUseCase {

    private ProjectRepository projectRepository;
    private Integer id;

    public DeleteProjectUseCase(ProjectRepository projectRepository, Integer id) {
        this.projectRepository = projectRepository;
        this.id = id;
    }

    public ResponseModel<ProjectResponse> execute(){
        ResponseModel<ProjectResponse> response = new ResponseModel<>();
        try{
            projectRepository.deleteById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user with id %d not found", id), e);
        }
        response.setResponse(null);
        return response;
    }

}
