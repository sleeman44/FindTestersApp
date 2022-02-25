package com.applause.findtesterapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class FindtesterappApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void orchestrateFindTesters() {
		
		List<String> countries = new ArrayList<String>(Arrays.asList("US", "GB", "JP")); 		
		
		// Build countries list
		if(countries != null || countries.size() > 0) {
			
			countries.stream()
				.collect(Collectors.joining("\'",",","\'"));
		}
		
		buildString(countries);
		
		System.out.println();
	}
	
	String buildString(List<String> countries) {
		String retval = "";
		boolean lastPass = false;
		int ctr=0;
		
		
		for(String str : countries) {
			ctr++;
			if(ctr > countries.size()-1)
				lastPass = true;
			
			retval += "'" + str + "'";
			
			if(!lastPass)
				retval += ",";
			
			System.out.println(retval);
		}
		
		System.out.println(retval);
		
		return retval;
	}


}
