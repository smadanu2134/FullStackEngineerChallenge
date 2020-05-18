package com.feedback.dto;

import java.util.List;

public class ReviewDTO {

	private long revieweeId;
	private List<Long> reviewersIds;
	/**
	 * @return the revieweeId
	 */
	public long getRevieweeId() {
		return revieweeId;
	}
	/**
	 * @param revieweeId the revieweeId to set
	 */
	public void setRevieweeId(long revieweeId) {
		this.revieweeId = revieweeId;
	}
	/**
	 * @return the reviewersIds
	 */
	public List<Long> getReviewersIds() {
		return reviewersIds;
	}
	/**
	 * @param reviewersIds the reviewersIds to set
	 */
	public void setReviewersIds(List<Long> reviewersIds) {
		this.reviewersIds = reviewersIds;
	}
	
	

}
