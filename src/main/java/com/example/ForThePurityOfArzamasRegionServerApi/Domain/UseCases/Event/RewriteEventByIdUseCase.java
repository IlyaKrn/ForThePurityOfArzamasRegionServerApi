package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Event;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.EventRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRequestRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Event;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Project;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.ProjectRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.EventResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectRequestResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;

public class RewriteEventByIdUseCase {

    private EventRepository projectRepository;
    private ImageRepository imageRepository;
    private Integer id;
    private HashMap<String, Object> values;

    public RewriteEventByIdUseCase(EventRepository projectRepository, ImageRepository imageRepository, Integer id, HashMap<String, Object> values) {
        this.projectRepository = projectRepository;
        this.imageRepository = imageRepository;
        this.id = id;
        this.values = values;
    }

    public ResponseModel<EventResponse> execute() {
        ResponseModel<EventResponse> response = new ResponseModel<>();
        try {
            Event p = projectRepository.findById(id).get();
            ArrayList<ImageResponse> images = new ArrayList<>();
            if (p.getImage_ids() != null && p.getImage_ids().length > 0) {
                for(Integer id : p.getImage_ids()) {
                    ImageResponse img = null;
                    try {
                        Image i = imageRepository.findById(id).get();
                        img = new ImageResponse(i.getId(), i.getUrl(), i.getHeight(), i.getWidth());
                        images.add(img);
                    } catch (Exception e) {
                        images.add(null);
                    }
                }
            }

            if (values.get("title") != null){
                p.setTitle((String) values.get("title"));
            }
            if (values.get("message") != null){
                p.setMessage((String) values.get("message"));
            }
            if (values.get("image_ids") != null){
                p.setImage_ids((Integer[]) values.get("image_ids"));
            }
            if (values.get("type") != null){
                p.setType((Integer) values.get("type"));
            }


            p.setLast_modified_time(System.currentTimeMillis());
            projectRepository.save(p);
            p = projectRepository.findById(id).get();
            EventResponse res = new EventResponse(p.getId(), p.getTitle(), p.getMessage(), p.getUpload_time(), p.getLast_modified_time(), p.getType(), images, p.getMember_ids(), p.getChat_id());
            response.setResponse(res);
            return response;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user with id %d not found", id), e);
        }
    }
}
