package com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories;

import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Message;
import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository  extends CrudRepository<Message, Integer> {
}
