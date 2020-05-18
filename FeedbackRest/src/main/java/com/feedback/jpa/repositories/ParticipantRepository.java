package com.feedback.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.feedback.entities.Participant;

public interface ParticipantRepository extends CrudRepository<Participant, Long> {

}
