package com.feedback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.dao.FeedbackDao;
import com.feedback.dto.DropdownDTO;
import com.feedback.dto.EmployeeDTO;
import com.feedback.dto.FeedbackDTO;
import com.feedback.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;

	@Override
	public List<FeedbackDTO> getMyReviews(String userName) {
		return feedbackDao.getMyReviews(userName);
	}

	@Override
	public List<FeedbackDTO> saveReviews(FeedbackDTO myReviews, String userName) {
		feedbackDao.saveReviews(myReviews);
		List<FeedbackDTO> reviews = feedbackDao.getMyReviews(userName);
		return reviews;
	}

	@Override
	public List<EmployeeDTO> addEmployee(EmployeeDTO employee) {
		feedbackDao.addEmployee(employee);
		return feedbackDao.getAllEmployees();
	}

	@Override
	public List<EmployeeDTO> editEmployee(EmployeeDTO employee) {
		feedbackDao.editEmployee(employee);
		return feedbackDao.getAllEmployees();
	}

	@Override
	public List<EmployeeDTO> deleteEmployee(EmployeeDTO employee) {
		feedbackDao.deleteEmployee(employee);
		return feedbackDao.getAllEmployees();

	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		return feedbackDao.getAllEmployees();
	}

	@Override
	public List<DropdownDTO> getEmployeeFeedback(long revieweeId) {
		return feedbackDao.getEmployeeFeedbackList(revieweeId);
	}

	@Override
	public List<FeedbackDTO> getFeedbackReviewers(long revieweeId, long feedbackId) {
		return feedbackDao.getFeedbackReviewers(revieweeId, feedbackId);
	}

	@Override
	public List<FeedbackDTO> addparticipants(FeedbackDTO feedbackDTO){
		return feedbackDao.addparticipants(feedbackDTO);
	}

	@Override
	public List<FeedbackDTO> addFeedback(){
		return null;
		
	}
}