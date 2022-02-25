package com.applause.findtesterapp.services.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.applause.findtesterapp.services.FindTesterService;
import com.applause.findtesterapp.utils.AppException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Rest Service for call to find Testers.
 * 
 * @author bwilson
 */
@RestController
@RequestMapping("rest/find-testers")
public class FindTesterAppRestService {
	
	private static final Logger log = LoggerFactory.getLogger(FindTesterAppRestService.class);

	public static final String SUCCESS_MSG = "SUCCESS";
	public static final String FAILED_MSG  = "FAILED";

	@Autowired
	private FindTesterService ftService;
	
	@GetMapping("/find-matching-testers")
	public ResponseEntity<Object> findMatchingTesters(
			@RequestParam(value="countries") List<String> countries,
			@RequestParam(value="devices") List<String> devices) 
					throws JsonMappingException, JsonProcessingException {
		log.info("*** START New REST call  **************************");
		// Call service method
		String callResult;
		try {
			callResult = ftService.orchestrateFindTesters(countries, devices);
		} 
		catch (AppException e) {
			callResult = FAILED_MSG;
		}
		
		// Build a simple return message for whether the process was successful or not.
		JsonNode json = null;
		ObjectMapper mapper = new ObjectMapper();
		if(SUCCESS_MSG.equals(callResult))
			json = mapper.readTree("{\"FindTesters process call\": \"SUCCESS\"}"); 
		else 
			json = mapper.readTree("{\"FindTesters process call\": \"FAILED\"}"); 

		log.info("*** END of Tester Results  ************************");

        return ResponseEntity.ok(json);
	}

}
