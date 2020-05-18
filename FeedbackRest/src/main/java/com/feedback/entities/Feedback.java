package com.feedback.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.util.CollectionUtils;

/**
 * The persistent class for the feedback database table.
 * 
 */
@Entity
@Table(name = "feedback")
public class Feedback implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private long id;

	@Column(name = "feedback_name", length = 45)
	private String feedbackName;

	// bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "reviewee_id")
	private Users users;

	// bi-directional many-to-one association to FeedbackParticipant
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "feedback_participants", joinColumns = @JoinColumn(name = "feedback_id"), inverseJoinColumns = @JoinColumn(name = "participant_id"))
	private Set<Participant> participants;

	public Feedback() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFeedbackName() {
		return this.feedbackName;
	}

	public void setFeedbackName(String feedbackName) {
		this.feedbackName = feedbackName;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Set<Participant> getParticipants() {
		if(CollectionUtils.isEmpty(participants)) {
			participants = new HashSet<Participant>();
		}
		return participants;
	}

	public void setParticiants(Set<Participant> participants) {
		this.participants = participants;
	}
	
	public void addPaticipant(Participant participant) {
        getParticipants().add(participant);
        participant.getFeedback().add(this);
    }
 
}