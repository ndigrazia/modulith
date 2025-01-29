package com.example.demo.eventpublication;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomEventPublicationRepository extends CrudRepository<CustomEventPublication, UUID> {
    List<CustomEventPublication> getEventPublicationsByPublicationDateNotNullAndCompletionDateIsNull();
    List<CustomEventPublication> getEventPublicationsByPublicationDateNotNullAndCompletionDateNotNull();
}
