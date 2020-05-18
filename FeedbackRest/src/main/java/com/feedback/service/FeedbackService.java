package com.feedback.service;

import java.util.List;

import com.feedback.dto.DropdownDTO;
import com.feedback.dto.EmployeeDTO;
import com.feedback.dto.FeedbackDTO;

public interface FeedbackService {

	List<FeedbackDTO> getMyReviews(String userName);

	List<FeedbackDTO> saveReviews(FeedbackDTO myReviews, String userName);

	List<EmployeeDTO> getAllEmployees();

	List<EmployeeDTO> addEmployee(EmployeeDTO employee);

	List<EmployeeDTO> editEmployee(EmployeeDTO employee);

	List<EmployeeDTO> deleteEmployee(EmployeeDTO employee);
	
	List<DropdownDTO> getEmployeeFeedback(long revieweeId);
	
	List<FeedbackDTO> getFeedbackReviewers(long revieweeId, long feedbackId);
	
	List<FeedbackDTO> addparticipants(FeedbackDTO feedbackDTO);
	
	List<FeedbackDTO> addFeedback();
	
}
