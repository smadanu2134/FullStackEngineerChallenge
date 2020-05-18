package com.feedback.dto;

import java.util.Date;
import java.util.List;

public class FeedbackDTO {

	private long participantId;
	private long revieweeId;
	private long feedbackId;
	private String feedbackName;
	private String revieweeName;
	private String revieweeEmail;
	private String participantName;
	private String participantEmail;
	private String comments;
	private String rating;
	private String status;
	private Date assignedDate;
	private List<Long> participantIds;

	public FeedbackDTO(long participantId, String feedbackName, String revieweeName, String revieweeEmail,
			String comments, String rating, String status, java.sql.Date assignedDate) {
		super();
		this.participantId = participantId;
		this.feedbackName = feedbackName;
		this.revieweeName = revieweeName;
		this.revieweeEmail = revieweeEmail;
		this.comments = comments;
		this.rating = rating;
		this.status = status;
		this.assignedDate = assignedDate != null ? new Date(assignedDate.getTime()) : null;
	}

	public FeedbackDTO(long participantId, long revieweeId, long feedbackId, String feedbackName, String revieweeName,
			String revieweeEmail, String participantName, String participantEmail, String comments, String rating,
			String status, java.sql.Date assignedDate) {
		super();
		this.participantId = participantId;
		this.revieweeId = revieweeId;
		this.feedbackId = feedbackId;
		this.feedbackName = feedbackName;
		this.revieweeName = revieweeName;
		this.revieweeEmail = revieweeEmail;
		this.participantName = participantName;
		this.participantEmail = participantEmail;
		this.comments = comments;
		this.rating = rating;
		this.status = status;
		this.assignedDate = assignedDate != null ? new Date(assignedDate.getTime()) : null;
	}

	public FeedbackDTO(long participantId, long feedbackId, String feedbackName, String participantName,
			String participantEmail, String status, Date assignedDate) {
		super();
		this.participantId = participantId;
		this.feedbackId = feedbackId;
		this.feedbackName = feedbackName;
		this.participantName = participantName;
		this.participantEmail = participantEmail;
		this.status = status;
		this.assignedDate = assignedDate;
	}

	public long getRevieweeId() {
		return revieweeId;
	}

	public void setRevieweeId(long revieweeId) {
		this.revieweeId = revieweeId;
	}

	public long getFeedbackId() {
		return feedbackId;
	}

	public void setFedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}

	public String getParticipantName() {
		return participantName;
	}

	public void setParticipantName(String participantName) {
		this.participantName = participantName;
	}

	public String getParticipantEmail() {
		return participantEmail;
	}

	public void setParticipantEmail(String participantEmail) {
		this.participantEmail = participantEmail;
	}

	/**
	 * @return the participantId
	 */
	public long getParticipantId() {
		return participantId;
	}

	/**
	 * @param participantId the participantId to set
	 */
	public void setParticipantId(long participantId) {
		this.participantId = participantId;
	}

	/**
	 * @return the feedbackName
	 */
	public String getFeedbackName() {
		return feedbackName;
	}

	/**
	 * @param feedbackName the feedbackName to set
	 */
	public void setFeedbackName(String feedbackName) {
		this.feedbackName = feedbackName;
	}

	/**
	 * @return the revieweeName
	 */
	public String getRevieweeName() {
		return revieweeName;
	}

	/**
	 * @param revieweeName the revieweeName to set
	 */
	public void setRevieweeName(String revieweeName) {
		this.revieweeName = revieweeName;
	}

	/**
	 * @return the revieweeEmail
	 */
	public String getRevieweeEmail() {
		return revieweeEmail;
	}

	/**
	 * @param revieweeEmail the revieweeEmail to set
	 */
	public void setRevieweeEmail(String revieweeEmail) {
		this.revieweeEmail = revieweeEmail;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating the rating to set
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the assignedDate
	 */
	public Date getAssignedDate() {
		return assignedDate;
	}

	/**
	 * @param assignedDate the assignedDate to set
	 */
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	public List<Long> getParticipantIds() {
		return participantIds;
	}

	public void setParticipantIds(List<Long> participantIds) {
		this.participantIds = participantIds;
	}

	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}

}