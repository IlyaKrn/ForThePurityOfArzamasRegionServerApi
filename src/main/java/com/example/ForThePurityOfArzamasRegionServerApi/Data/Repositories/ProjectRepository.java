package com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories;

import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.DatabaseModels.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository  extends CrudRepository<Project, Integer> {
}
