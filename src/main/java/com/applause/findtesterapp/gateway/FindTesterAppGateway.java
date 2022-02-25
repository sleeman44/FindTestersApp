package com.applause.findtesterapp.gateway;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.applause.findtesterapp.dataobject.TesterResultsDO;
import com.applause.findtesterapp.utils.AppException;

/**
 * The Repository Gateway for all DB functionality.
 * 
 * @author bwilson
 *
 */
@Repository
public class FindTesterAppGateway {
	
	private static final Logger log = LoggerFactory.getLogger(FindTesterAppGateway.class);
	
	@Autowired
	SessionFactory sf;
	
	/**
	 * Retrieves the testers based on the param criteria.
	 * 
	 * @param countries	a String value of countries in SQL criteria format.
	 * @param devices	a String value of devices in SQL criteria format.
	 * 
	 * @return a list of TesterResultsDO objects.
	 * @throws AppException 
	 */
	@SuppressWarnings({ "unchecked" })
	public List<TesterResultsDO> findMatchingTesters(String countries, String devices) throws AppException {
		log.info("Query db for tester info based on User criteria.");
		
		Session session = sf.getCurrentSession(); 
		
		List<TesterResultsDO> trList = new ArrayList<TesterResultsDO>();
		String countryCriteria = "";
		String deviceCriteria = "";
		
		// Determine dynamic criteria.
		if(countries != null && countries.length() > 0) 
			countryCriteria += "  and UPPER(t.country) IN (" + countries.toUpperCase() + ")";
		
		if(devices != null && devices.length() > 0)
			deviceCriteria += "  and UPPER(d.description) IN (" + devices.toUpperCase() + ")";
		
		// Count and sort the data here in the resultSet.
		String sql = "select count(b.bug_id) as bugs, CONCAT_WS(' ', t.first_name, t.last_name) as user, d.description as device" +
					"   from bugs b, testers t, devices d " +
					"  where b.tester_id = t.tester_id " +
					"    and b.device_id = d.device_id " +
					countryCriteria +
					deviceCriteria +
					"  group by user, d.description " +
					"  order by d.description, bugs DESC, user ";
		
		try {
			
			Query query = session.createNativeQuery(sql);
			List<Object[]> list = (List<Object[]>)query.getResultList();
			
			TesterResultsDO tr;
		    for (Object[] obj : list) {
		    	tr = new TesterResultsDO();
		    	BigInteger bugs = (BigInteger)obj[0];
		    	String user = (String)obj[1];
		    	String device = (String)obj[2];
		    	
		    	tr.setTotalBugs(Integer.valueOf(bugs.intValue()));
		    	tr.setUser(user);
		    	tr.setDevice(device);
		    	
		    	trList.add(tr);
			 }
		}
		catch(Exception e) {
			String msg = "An error occurred retrieving the SQL results.";
			log.error(msg, e.getMessage());
			throw new AppException(msg, e);
			//e.printStackTrace();
		}
		
		return trList;
	}

}
