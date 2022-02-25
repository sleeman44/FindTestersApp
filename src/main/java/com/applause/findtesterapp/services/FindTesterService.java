package com.applause.findtesterapp.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.applause.findtesterapp.dataobject.TesterResultsDO;
import com.applause.findtesterapp.gateway.FindTesterAppGateway;
import com.applause.findtesterapp.utils.AppException;

/**
 * Service for orchestrating finding testers.
 * 
 * @author bwilson
 */
@Service
public class FindTesterService {
	
	private static final Logger log = LoggerFactory.getLogger(FindTesterService.class);
	
	@Autowired
	private FindTesterAppGateway ftaGateway;
	
	// Default constructor
	public FindTesterService() {
		
	}
	
	/**
	 * Orchestrates the find testers process.
	 * 
	 * @param countries 	a list of String values for countries
	 * @param devices	a list of String values for devices
	 */
	@Transactional(readOnly=true)
	public String orchestrateFindTesters(List<String> countries, List<String> devices) 
			throws AppException {
		log.info("  orchestrateFindTesters called.\n");
		
		String countriesStr = null;
		String devicesStr = null;
		String retval = null;
		
		// Build countries string from the list values.
		log.info("*** Criteria:  ******************************");
		if(countries != null) {
			if(!countries.contains("ALL") && countries.size() > 0) {
				countriesStr = buildString(countries);
				log.info("  Countries: " + countriesStr);
			} 
		}
		else {
			log.info("  NO Country Criteria entered.");
		}
		
		// Build devices string from the list values.
		if(devices != null) {
			if(!devices.contains("ALL") && devices.size() > 0) {
				devicesStr = buildString(devices);
				log.info("  Devices:   " + devicesStr);
			} 
		}
		else {
			log.info("  NO Device Criteria entered.");
		}
		log.info("*** End of criteria:  ***********************\n");

		
		// Query the DB for any results.
		List<TesterResultsDO> list = null;
		try {
			list = ftaGateway.findMatchingTesters(countriesStr, devicesStr);
		} 
		catch(Exception e) {
			String msg = "An error occurred in the FindTesterAllGateway.";
			retval = "FAILED";
			log.error(msg, e.getMessage());
			throw new AppException(msg, e);
			//e.printStackTrace();
		}
				
		// Print the sorted list
		if(!list.isEmpty()) {
			printList(list);
			retval = "SUCCESS";
		}
		else {
			log.info("  There aren't ANY users for that criteria combination.\n");
			retval = "SUCCESS";
		}
		
		return retval;
	}
	
	/**
	 * Builds the parameter list into a String for SQL criteria use.
	 * 
	 * @param list	a list of Strings
	 * 
	 * @return a String value of objects.
	 */
	private String buildString(List<String> list) {
		
		String retval = "";
		boolean lastPass = false;
		int ctr=0;
		
		// Use boolean to get commas correct for each value.
		for(String str : list) {
			ctr++;
			if(ctr > list.size()-1)
				lastPass = true;
			
			retval += "'" + str + "'";
			
			if(!lastPass)
				retval += ",";
		}
		
		return retval;
	}
	
	/**
	 * Print the results list.
	 * 
	 * @param results	a list of TesterResultsDO objects.
	 */
	private void printList(List<TesterResultsDO> results) {
		// Output format:  User1 filed 4 Bugs for iPhone 4.
		String tempDevice = results.get(0).getDevice();
		
		log.info("*** Device (change)  ************************\n");
		// Loop through any results and separate the data by device then print.
		for(TesterResultsDO value : results) {
			
			if(tempDevice.equals(value.getDevice())) {
				StringBuilder sb = new StringBuilder();
				sb =  sb.append("  " + value.getUser() + " filed " + value.getTotalBugs().intValue() + 
						" bugs for " + value.getDevice());
				log.info(sb.toString());
			}
			else {
				log.info("*** Device (change)  ************************\n");
				StringBuilder sb = new StringBuilder();
				sb =  sb.append("  " + value.getUser() + " filed " + value.getTotalBugs().intValue() + 
						" bugs for " + value.getDevice());
				log.info(sb.toString());
				tempDevice = value.getDevice();
			}
		}
	}
	
}
