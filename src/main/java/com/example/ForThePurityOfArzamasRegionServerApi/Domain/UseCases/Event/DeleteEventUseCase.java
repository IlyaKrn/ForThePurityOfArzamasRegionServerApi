package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Event;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.EventRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.EventResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DeleteEventUseCase {

    private EventRepository projectRepository;
    private Integer id;

    public DeleteEventUseCase(EventRepository projectRepository, Integer id) {
        this.projectRepository = projectRepository;
        this.id = id;
    }

    public ResponseModel<EventResponse> execute() {
        ResponseModel<EventResponse> response = new ResponseModel<>();
        try{
            projectRepository.deleteById(id);
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("event with id %d not found", id), e);
        }
        response.setResponse(null);
        return response;
    }
}
