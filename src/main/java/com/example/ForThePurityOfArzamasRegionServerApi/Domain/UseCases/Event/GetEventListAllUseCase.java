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

import java.util.ArrayList;

public class GetEventListAllUseCase {

    private EventRepository projectRepository;
    private ImageRepository imageRepository;

    public GetEventListAllUseCase(EventRepository projectRepository, ImageRepository imageRepository) {
        this.projectRepository = projectRepository;
        this.imageRepository = imageRepository;
    }

    public ResponseModel<ArrayList<EventResponse>> execute() {
        ResponseModel<ArrayList<EventResponse>> response = new ResponseModel<>();
        ArrayList<EventResponse> projects = new ArrayList<>();
        ArrayList<Event> ps = (ArrayList<Event>) projectRepository.findAll();
        if(ps.size() > 0) {
            for (Event p : ps) {
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

                EventResponse res = new EventResponse(p.getId(), p.getTitle(), p.getMessage(), p.getUpload_time(), p.getLast_modified_time(), p.getType(), images, p.getMember_ids(), p.getChat_id());
                projects.add(res);
            }
        }
        else {
            response.setResponse(projects);
            return response;
        }
        response.setResponse(projects);
        return response;
    }
}
