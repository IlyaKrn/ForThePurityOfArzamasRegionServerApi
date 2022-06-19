package com.example.ForThePurityOfArzamasRegionServerApi.Domain.UseCases.Locate;

import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ImageRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.LocateRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories.ProjectRequestRepository;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Locate;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Project;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.ProjectRequest;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ImageResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.LocateResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectRequestResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.ResponseModels.ProjectResponse;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Support.ResponseModels.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RewriteLocateByIdUseCase {

    private LocateRepository projectRepository;
    private ImageRepository imageRepository;
    private Integer id;
    private HashMap<String, Object> values;

    public RewriteLocateByIdUseCase(LocateRepository projectRepository, ImageRepository imageRepository, Integer id, HashMap<String, Object> values) {
        this.projectRepository = projectRepository;
        this.imageRepository = imageRepository;
        this.id = id;
        this.values = values;
    }

    public ResponseModel<LocateResponse> execute(){
        ResponseModel<LocateResponse> response = new ResponseModel<>();
        try {
            Locate p = projectRepository.findById(id).get();
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
                p.setImage_ids(((List<Integer>)values.get("image_ids")).toArray(new Integer[0]));
            }
            if (values.get("address") != null){
                p.setAddress((String) values.get("address"));
            }
            if (values.get("longitude") != null){
                p.setLongitude((Double) values.get("longitude"));
            }
            if (values.get("latitude") != null){
                p.setLatitude((Double) values.get("latitude"));
            }
            p.setLast_modified_time(System.currentTimeMillis());
            projectRepository.save(p);
            p = projectRepository.findById(id).get();
            LocateResponse res = new LocateResponse(p.getId(), p.getTitle(), p.getMessage(), p.getUpload_time(), p.getLast_modified_time(),images, p.getChat_id(), p.getAddress(), p.getLongitude(), p.getLatitude());
            response.setResponse(res);
            return response;
        } catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("user with id %d not found", id), e);
        }
    }
}
