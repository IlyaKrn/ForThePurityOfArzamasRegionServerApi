package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Event;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.*;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Chat;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Event;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Project;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.EventRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.LocateRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.ProjectMainRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.EventResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

public class CreateEventUseCase {

    private EventRepository projectRepository;
    private ImageRepository imageRepository;
    private ChatRepository chatRepository;
    private EventRequest project;

    public CreateEventUseCase(EventRepository projectRepository, ImageRepository imageRepository, ChatRepository chatRepository, EventRequest project) {
        this.projectRepository = projectRepository;
        this.imageRepository = imageRepository;
        this.chatRepository = chatRepository;
        this.project = project;
    }

    public ResponseModel<EventResponse> execute() {
        ResponseModel<EventResponse> response = new ResponseModel<>();
        if(project.getTitle() == null || project.getMessage() == null ||  project.getType() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields 'title', 'message', 'type' must be not null", null);
        }

        Chat c = chatRepository.save(new Chat(null, null, null));
        Event p = new Event(null, project.getTitle(), project.getMessage(), System.currentTimeMillis(), null, project.getType(), project.getImage_ids(), null, c.getId());
        Event temp = projectRepository.save(p);



        ArrayList<ImageResponse> images = new ArrayList<>();
        if (temp.getImage_ids() != null && temp.getImage_ids().length > 0){
            for(Integer id : temp.getImage_ids()){
                ImageResponse image = null;
                try {
                    Image i = imageRepository.findById(id).get();
                    image = new ImageResponse(i.getId(), i.getUrl(), i.getHeight(), i.getWidth());
                } catch (Exception ignored){

                }
                images.add(image);
            }

        }
        response.setResponse(new EventResponse(temp.getId(), temp.getTitle(), temp.getMessage(), temp.getUpload_time(), temp.getLast_modified_time(), temp.getType(), images, temp.getMember_ids(), temp.getChat_id()));
        return response;

    }
}
