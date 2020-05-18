package com.feedback.jpa.repositories;

import org.springframework.data.repository.CrudRepository;

import com.feedback.entities.Feedback;

public interface FeedbackRepository extends CrudRepository<Feedback, Long> {

}
