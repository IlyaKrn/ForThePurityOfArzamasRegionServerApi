package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Project;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ChatRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Chat;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Project;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.ProjectMainRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

public class CreateProjectUseCase {

    private ProjectRepository projectRepository;
    private ImageRepository imageRepository;
    private ChatRepository chatRepository;
    private ProjectMainRequest project;

    public CreateProjectUseCase(ProjectRepository projectRepository, ImageRepository imageRepository, ChatRepository chatRepository, ProjectMainRequest project) {
        this.projectRepository = projectRepository;
        this.imageRepository = imageRepository;
        this.chatRepository = chatRepository;
        this.project = project;
    }

    public ResponseModel<ProjectResponse> execute(){
        ResponseModel<ProjectResponse> response = new ResponseModel<>();
        if(project.getTitle() == null || project.getMessage() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields 'title', 'message' must be not null", null);
        }

        Chat c = chatRepository.save(new Chat(null, null, null));
        Project p = new Project(null, project.getTitle(), project.getMessage(), System.currentTimeMillis(), null, project.getImage_ids(), null, c.getId());
        Project temp = projectRepository.save(p);



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
        response.setResponse(new ProjectResponse(temp.getId(), temp.getTitle(), temp.getMessage(), temp.getUpload_time(), temp.getLast_modified_time(), images, null, temp.getChat_id()));
        return response;

    }
}
