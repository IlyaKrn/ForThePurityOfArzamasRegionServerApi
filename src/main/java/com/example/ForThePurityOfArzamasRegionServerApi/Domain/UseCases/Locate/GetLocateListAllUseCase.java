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

import java.util.ArrayList;

public class GetLocateListAllUseCase {

    private LocateRepository locateRepository;
    private ImageRepository imageRepository;

    public GetLocateListAllUseCase(LocateRepository locateRepository, ImageRepository imageRepository) {
        this.locateRepository = locateRepository;
        this.imageRepository = imageRepository;
    }

    public ResponseModel<ArrayList<LocateResponse>> execute() {
        ResponseModel<ArrayList<LocateResponse>> response = new ResponseModel<>();
        ArrayList<LocateResponse> projects = new ArrayList<>();
        ArrayList<Locate> ps = (ArrayList<Locate>) locateRepository.findAll();
        if(ps.size() > 0) {
            for (Locate p : ps) {
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

                LocateResponse res = new LocateResponse(p.getId(), p.getTitle(), p.getMessage(), p.getUpload_time(), p.getLast_modified_time(), images, p.getChat_id(), p.getAddress(), p.getLongitude(), p.getLatitude());
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
