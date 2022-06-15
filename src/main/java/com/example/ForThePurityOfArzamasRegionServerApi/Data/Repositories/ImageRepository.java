package com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories;

import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository  extends CrudRepository<Image, Integer> {
}
