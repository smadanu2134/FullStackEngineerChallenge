package com.feedback.rest.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.dto.DropdownDTO;
import com.feedback.dto.EmployeeDTO;
import com.feedback.dto.FeedbackDTO;
import com.feedback.dto.Response;
import com.feedback.security.JwtTokenUtil;
import com.feedback.service.FeedbackService;
import com.google.gson.JsonObject;

@RestController
@CrossOrigin
@RequestMapping(path = "/api")
public class FeedbackRestController {

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping(path = "/myReviews", produces = "application/json")
	public ResponseEntity<Response> getMyReviews(HttpServletRequest request) {
		Response resp = new Response();
		ResponseEntity<Response> respEntity = null;
		try {
			String token = jwtTokenUtil.getTokenFromRequest(request);
			String userName = jwtTokenUtil.getUsernameFromToken(token);
			List<FeedbackDTO> reviewList = feedbackService.getMyReviews(userName);
			resp.setCode(HttpStatus.OK.value());
			resp.setResponse(reviewList);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.OK);

		} catch (Exception ex) {
			resp.setCode(HttpStatus.BAD_REQUEST.value());
			List<String> err = new ArrayList<String>();
			err.add(ex.getMessage());
			resp.setErrors(err);
			ex.printStackTrace();
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		return respEntity;
	}

	@PostMapping(path = "/saveReviews", produces = "application/json")
	public ResponseEntity<Response> saveMyReviews(HttpServletRequest request, @RequestBody FeedbackDTO myReviews) {
		Response resp = new Response();
		ResponseEntity<Response> respEntity = null;
		try {
			String token = jwtTokenUtil.getTokenFromRequest(request);
			String userName = jwtTokenUtil.getUsernameFromToken(token);
			List<FeedbackDTO> reviewList = feedbackService.saveReviews(myReviews, userName);
			resp.setCode(HttpStatus.OK.value());
			resp.setResponse(reviewList);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.OK);
		} catch (Exception ex) {
			resp.setCode(HttpStatus.BAD_REQUEST.value());
			List<String> err = new ArrayList<String>();
			err.add(ex.getMessage());
			resp.setErrors(err);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		return respEntity;
	}

	@PostMapping(path = "/addEmployee", produces = "application/json")
	public ResponseEntity<Response> addEmployee(HttpServletRequest request, @RequestBody EmployeeDTO employee) {
		Response resp = new Response();
		ResponseEntity<Response> respEntity = null;
		try {
			List<EmployeeDTO> employeeList = feedbackService.addEmployee(employee);
			resp.setCode(HttpStatus.OK.value());
			resp.setResponse(employeeList);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.OK);
		} catch (Exception ex) {
			resp.setCode(HttpStatus.BAD_REQUEST.value());
			List<String> err = new ArrayList<String>();
			err.add(ex.getMessage());
			resp.setErrors(err);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		return respEntity;
	}

	@PostMapping(path = "/modifyEmployee", produces = "application/json")
	public ResponseEntity<Response> modifyEmployee(HttpServletRequest request, @RequestBody EmployeeDTO employee) {
		Response resp = new Response();
		ResponseEntity<Response> respEntity = null;
		try {
			List<EmployeeDTO> employeeList = feedbackService.editEmployee(employee);
			resp.setCode(HttpStatus.OK.value());
			resp.setResponse(employeeList);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.OK);
		} catch (Exception ex) {
			resp.setCode(HttpStatus.BAD_REQUEST.value());
			List<String> err = new ArrayList<String>();
			err.add(ex.getMessage());
			resp.setErrors(err);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		return respEntity;
	}

	@PostMapping(path = "/deleteEmployee", produces = "application/json")
	public ResponseEntity<Response> deleteEmployee(HttpServletRequest request, @RequestBody EmployeeDTO employee) {
		Response resp = new Response();
		ResponseEntity<Response> respEntity = null;
		try {
			List<EmployeeDTO> employeeList = feedbackService.deleteEmployee(employee);
			resp.setCode(HttpStatus.OK.value());
			resp.setResponse(employeeList);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.OK);
		} catch (Exception ex) {
			resp.setCode(HttpStatus.BAD_REQUEST.value());
			List<String> err = new ArrayList<String>();
			err.add(ex.getMessage());
			resp.setErrors(err);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		return respEntity;
	}

	@PostMapping(path = "/employees", produces = "application/json")
	public ResponseEntity<Response> getAllEmployee(HttpServletRequest request, @RequestBody EmployeeDTO employee) {
		Response resp = new Response();
		ResponseEntity<Response> respEntity = null;
		try {
			List<EmployeeDTO> employeeList = feedbackService.getAllEmployees();
			resp.setCode(HttpStatus.OK.value());
			resp.setResponse(employeeList);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.OK);
		} catch (Exception ex) {
			resp.setCode(HttpStatus.BAD_REQUEST.value());
			List<String> err = new ArrayList<String>();
			err.add(ex.getMessage());
			resp.setErrors(err);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		return respEntity;
	}
	
	@PostMapping(path = "/feedbacks", produces = "application/json")
	public ResponseEntity<Response> getFeedbacksList(HttpServletRequest request, @RequestBody JsonObject obj) {
		Response resp = new Response();
		ResponseEntity<Response> respEntity = null;
		try {
			long revieweeId = obj.get("revieweeId").getAsLong();
			List<DropdownDTO> feedbckList = feedbackService.getEmployeeFeedback(revieweeId);
			resp.setCode(HttpStatus.OK.value());
			resp.setResponse(feedbckList);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.OK);
		} catch (Exception ex) {
			resp.setCode(HttpStatus.BAD_REQUEST.value());
			List<String> err = new ArrayList<String>();
			err.add(ex.getMessage());
			resp.setErrors(err);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		return respEntity;
	}
	
	@PostMapping(path = "/feedbackParticipants", produces = "application/json")
	public ResponseEntity<Response> getFeedbackParticipants(HttpServletRequest request, @RequestBody JsonObject obj) {
		Response resp = new Response();
		ResponseEntity<Response> respEntity = null;
		try {
			long feedbackId = obj.get("feedbackId").getAsLong();
			List<FeedbackDTO> feedbckList = feedbackService.getFeedbackReviewers(0, feedbackId);
			resp.setCode(HttpStatus.OK.value());
			resp.setResponse(feedbckList);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.OK);
		} catch (Exception ex) {
			resp.setCode(HttpStatus.BAD_REQUEST.value());
			List<String> err = new ArrayList<String>();
			err.add(ex.getMessage());
			resp.setErrors(err);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		return respEntity;
	}

	@PostMapping(path = "/addParticipants", produces = "application/json")
	public ResponseEntity<Response> addparticipants(HttpServletRequest request, @RequestBody FeedbackDTO feedbackDto) {
		Response resp = new Response();
		ResponseEntity<Response> respEntity = null;
		try {
			List<FeedbackDTO> feedbckList = feedbackService.addparticipants(feedbackDto);
			resp.setCode(HttpStatus.OK.value());
			resp.setResponse(feedbckList);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.OK);
		} catch (Exception ex) {
			resp.setCode(HttpStatus.BAD_REQUEST.value());
			List<String> err = new ArrayList<String>();
			err.add(ex.getMessage());
			resp.setErrors(err);
			respEntity = new ResponseEntity<Response>(resp, HttpStatus.BAD_REQUEST);
		}
		return respEntity;
	}
	
}
