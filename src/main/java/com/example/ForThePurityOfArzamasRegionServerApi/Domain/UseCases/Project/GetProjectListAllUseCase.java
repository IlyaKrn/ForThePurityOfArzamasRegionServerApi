package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Project;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRequestRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.UserRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Project;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.User;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.ProjectRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectRequestResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.UserResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;

import java.util.ArrayList;

public class GetProjectListAllUseCase {

    private ProjectRepository projectRepository;
    private ImageRepository imageRepository;
    private ProjectRequestRepository requestRepository;

    public GetProjectListAllUseCase(ProjectRepository projectRepository, ImageRepository imageRepository) {
        this.projectRepository = projectRepository;
        this.imageRepository = imageRepository;
    }

    public ResponseModel<ArrayList<ProjectResponse>> execute(){
        ResponseModel<ArrayList<ProjectResponse>> response = new ResponseModel<>();
        ArrayList<ProjectResponse> projects = new ArrayList<>();
        ArrayList<Project> ps = (ArrayList<Project>) projectRepository.findAll();
        if(ps.size() > 0) {
            for (Project p : ps) {
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
                    for(Integer id : p.getRequest_ids()) {
                        ProjectRequestResponse req = null;
                        try {
                            ProjectRequest i = requestRepository.findById(id).get();


                            ArrayList<ImageResponse> imgs = new ArrayList<>();
                            if (i.getImage_ids() != null && i.getImage_ids().length > 0) {
                                for(Integer idd : p.getImage_ids()) {
                                    ImageResponse imgg = null;
                                    try {
                                        Image ii = imageRepository.findById(id).get();
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




                ProjectResponse res = new ProjectResponse(p.getId(), p.getTitle(), p.getMessage(), p.getUpload_time(), p.getLast_modified_time(), images, requests, p.getChat_id());
                projects.add(res);
            }
        }
        else {
            projects.add(null);
            response.setResponse(projects);
            return response;
        }
        response.setResponse(projects);
        return response;
    }
}
