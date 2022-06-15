package com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories;

import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository  extends CrudRepository<Message, Integer> {
}
