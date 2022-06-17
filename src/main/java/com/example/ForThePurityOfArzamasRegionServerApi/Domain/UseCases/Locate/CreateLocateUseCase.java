package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Locate;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ChatRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.LocateRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Chat;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Locate;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Project;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.LocateRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.RequestModels.ProjectMainRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.LocateResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

public class CreateLocateUseCase {


    private LocateRepository locateRepository;
    private ImageRepository imageRepository;
    private ChatRepository chatRepository;
    private LocateRequest locate;

    public CreateLocateUseCase(LocateRepository locateRepository, ImageRepository imageRepository, ChatRepository chatRepository, LocateRequest locate) {
        this.locateRepository = locateRepository;
        this.imageRepository = imageRepository;
        this.chatRepository = chatRepository;
        this.locate = locate;
    }

    public ResponseModel<LocateResponse> execute() {
        ResponseModel<LocateResponse> response = new ResponseModel<>();

        if(locate.getTitle() == null || locate.getMessage() == null || locate.getAddress() == null || locate.getLongitude() == null || locate.getLatitude() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fields 'title', 'message' must be not null", null);
        }

        Chat c = chatRepository.save(new Chat(null, null, null));

        Locate p = new Locate(null, locate.getTitle(), locate.getMessage(), System.currentTimeMillis(), null, locate.getImage_ids(), c.getId(), locate.getAddress(), locate.getLongitude(), locate.getLatitude());
        Locate temp = locateRepository.save(p);


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
        response.setResponse(new LocateResponse(temp.getId(), temp.getTitle(), temp.getMessage(), temp.getUpload_time(), temp.getLast_modified_time(), images,  temp.getChat_id(), locate.getAddress(), locate.getLongitude(), locate.getLatitude()));
        return response;

    }
}
