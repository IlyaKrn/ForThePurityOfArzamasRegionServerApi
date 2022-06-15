package com.example.ForThePurityOfArzamasRegionServerApi.Data.Repositories;

import com.example.ForThePurityOfArzamasRegionServerApi.Domain.Models.Data.DatabaseModels.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository  extends CrudRepository<Event, Integer> {
}
