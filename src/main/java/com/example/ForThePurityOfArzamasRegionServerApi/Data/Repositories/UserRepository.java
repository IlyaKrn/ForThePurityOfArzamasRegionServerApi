package com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories;

import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.DatabaseModels.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
