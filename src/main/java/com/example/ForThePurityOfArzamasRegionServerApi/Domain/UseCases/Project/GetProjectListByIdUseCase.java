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

import java.util.ArrayList;

public class GetProjectListByIdUseCase {

    private ProjectRepository projectRepository;
    private ProjectRequestRepository requestRepository;
    private ImageRepository imageRepository;
    private ArrayList<Integer> ids;

    public GetProjectListByIdUseCase(ProjectRepository projectRepository, ProjectRequestRepository requestRepository, ImageRepository imageRepository, ArrayList<Integer> ids) {
        this.projectRepository = projectRepository;
        this.requestRepository = requestRepository;
        this.imageRepository = imageRepository;
        this.ids = ids;
    }

    public ResponseModel<ArrayList<ProjectResponse>> execute(){
        ResponseModel<ArrayList<ProjectResponse>> response = new ResponseModel<>();
        ArrayList<ProjectResponse> projects = new ArrayList<>();
        for(Integer id : ids) {
            try {
                Project p = projectRepository.findById(id).get();
                ArrayList<ImageResponse> images = new ArrayList<>();
                if (p.getImage_ids() != null && p.getImage_ids().length > 0) {
                    for(Integer id1 : p.getImage_ids()) {
                        ImageResponse img = null;
                        try {
                            Image i = imageRepository.findById(id1).get();
                            img = new ImageResponse(i.getId(), i.getUrl(), i.getHeight(), i.getWidth());
                            images.add(img);
                        } catch (Exception e) {
                            images.add(null);
                        }
                    }
                }
                ArrayList<ProjectRequestResponse> requests = new ArrayList<>();
                if (p.getRequest_ids() != null && p.getRequest_ids().length > 0) {
                    for(Integer id1 : p.getRequest_ids()) {
                        ProjectRequestResponse req = null;
                        try {
                            ProjectRequest i = requestRepository.findById(id1).get();
                            ArrayList<ImageResponse> imgs = new ArrayList<>();
                            if (i.getImage_ids() != null && i.getImage_ids().length > 0) {
                                for(Integer idd : i.getImage_ids()) {
                                    ImageResponse imgg = null;
                                    try {
                                        Image ii = imageRepository.findById(idd).get();
                                        imgg = new ImageResponse(i.getId(), ii.getUrl(), ii.getHeight(), ii.getWidth());
                                        imgs.add(imgg);
                                    } catch (Exception e) {
                                        imgs.add(null);
                                    }
                                }
                            }

                            req = new ProjectRequestResponse(i.getId(), i.getUser_id(), i.getMessage(), imgs);
                            requests.add(req);
                        } catch (Exception e) {
                            requests.add(null);
                        }
                    }
                }




                ProjectResponse res = new ProjectResponse(p.getId(), p.getTitle(), p.getMessage(), p.getUpload_time(), p.getLast_modified_time(), images, requests, p.getChat_id());
                projects.add(res);
            } catch (Exception ignored){
                projects.add(null);
            }
        }
        response.setResponse(projects);
        return response;

    }
}
