package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Project;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRequestRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Project;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.ProjectRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.User;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectRequestResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;

public class RewriteProjectByIdUseCase {

    private ProjectRepository projectRepository;
    private ImageRepository imageRepository;
    private ProjectRequestRepository requestRepository;
    private Integer id;
    private HashMap<String, Object> values;

    public RewriteProjectByIdUseCase(ProjectRepository projectRepository, ImageRepository imageRepository, ProjectRequestRepository requestRepository, Integer id, HashMap<String, Object> values) {
        this.projectRepository = projectRepository;
        this.imageRepository = imageRepository;
        this.requestRepository = requestRepository;
        this.id = id;
        this.values = values;
    }

    public ResponseModel<ProjectResponse> execute(){
        ResponseModel<ProjectResponse> response = new ResponseModel<>();
        try {
            Project p = projectRepository.findById(id).get();
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
            ArrayList<ProjectRequestResponse> requests = new ArrayList<>();
            if (p.getRequest_ids() != null && p.getRequest_ids().length > 0) {
                for (Integer id : p.getRequest_ids()) {
                    ProjectRequestResponse req = null;
                    try {
                        ProjectRequest i = requestRepository.findById(id).get();


                        ArrayList<ImageResponse> imgs = new ArrayList<>();
                        if (i.getImage_ids() != null && i.getImage_ids().length > 0) {
                            for (Integer idd : p.getImage_ids()) {
                                ImageResponse imgg = null;
                                try {
                                    Image ii = imageRepository.findById(idd).get();
                                    imgg = new ImageResponse(i.getId(), ii.getUrl(), ii.getHeight(), ii.getWidth());
                                    images.add(imgg);
                                } catch (Exception e) {
                                    images.add(null);
                                }
                            }
                        }

                        req = new ProjectRequestResponse(i.getId(), i.getUser_id(), i.getMessage(), imgs);
                        requests.add(req);
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
            p.setLast_modified_time(System.currentTimeMillis());
            projectRepository.save(p);
            p = projectRepository.findById(id).get();
            ProjectResponse res = new ProjectResponse(p.getId(), p.getTitle(), p.getMessage(), p.getUpload_time(), p.getLast_modified_time(),images, requests, p.getChat_id());
            response.setResponse(res);
            return response;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user with id %d not found", id), e);
        }
    }
}
