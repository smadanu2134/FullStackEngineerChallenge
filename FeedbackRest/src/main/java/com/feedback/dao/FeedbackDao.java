package com.feedback.dao;

import java.util.List;

import com.feedback.dto.DropdownDTO;
import com.feedback.dto.EmployeeDTO;
import com.feedback.dto.FeedbackDTO;

public interface FeedbackDao {

	List<FeedbackDTO> getMyReviews(String userName);

	void saveReviews(FeedbackDTO myReviews);

	List<EmployeeDTO> getAllEmployees();

	String addEmployee(EmployeeDTO employee);

	void editEmployee(EmployeeDTO employee);

	void deleteEmployee(EmployeeDTO employee);

	List<DropdownDTO> getEmployeeFeedbackList(long revieweeId);

	List<FeedbackDTO> getFeedbackReviewers(long revieweeId, long feedbackId);

	List<FeedbackDTO> addparticipants(FeedbackDTO feedbackDTO);

}
