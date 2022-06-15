package com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories;

import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.DatabaseModels.ProjectRequest;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRequestRepository  extends CrudRepository<ProjectRequest, Integer> {
}
