package com.rollingstone.debugclientdemo.model.verbose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rollingstone.debugclientdemo.model.Department;
import com.rollingstone.debugclientdemo.model.EmployeeModified;


public class RequestInsightCollector {

	static Logger logger  = LoggerFactory.getLogger(RequestInsightCollector.class);

	public static Map<String,String> requestIdMap = new HashMap<String, String>();

	static boolean verbose;
	
    public static  ThreadLocal<Map> requestContext = new ThreadLocal<Map>();
    
    private static List<RequestInsight> requestInsights = new ArrayList<RequestInsight>();
    
    public static void startRequest(String requedtID)
    {
    	if (verbose) {
	    	releaseRequest();
	    	RequestInsight requestInsght = new RequestInsight();
	    	requestInsght.setStartTime(System.currentTimeMillis());
	    	requestInsght.setVerbose(verbose);
	        Map<String, Object> requestData = new HashMap<String, Object>();
	        requestData.put(requedtID, requestInsght);
	        requestContext.set(requestData);
    	}
    }
    
    public static void initializeRequest() {
    	requestContext = new ThreadLocal<Map>();
    	requestInsights = new ArrayList<RequestInsight>();
    	requestIdMap = new HashMap<String, String>();
    }


    public static void addRequestIdToMap(String key, String value) {
    	requestIdMap.put(key, value);
    }
    
    public static String getRequestIdFromMap(String key) {
    	return requestIdMap.get(key);
    }
	public static boolean isVerbose() {
		return verbose;
	}



	public static void setVerbose(boolean verbose) {
		RequestInsightCollector.verbose = verbose;
	}



	public static ThreadLocal<Map> getRequestcontext() {
		return requestContext;
	}
    
    
	 //To release memory
    public static void releaseRequest()
    {
    	requestContext.remove();
    	requestIdMap.clear();
    }

    public static RequestInsight getRequestInsight(String requestID)
    {
    	RequestInsight requestInsight = (requestContext.get() == null || requestContext.get().get(requestID) == null) ? new RequestInsight()
                : (RequestInsight) requestContext.get().get(requestID);
        return requestInsight;
    }
    
	
    
    public static double getElapsedTime(String requestID)
    {
        long time = System.currentTimeMillis() - getRequestInsight(requestID).getStartTime();
        return time/1000;
    }

	public static List<RequestInsight> getRequestInsights() {
		return requestInsights;
	}

	public static void setRequestInsights(List<RequestInsight> requestInsights) {
		RequestInsightCollector.requestInsights = requestInsights;
	}
    
    public static void addRequestInsight(RequestInsight requestInsight) {
    	RequestInsightCollector.requestInsights.add(requestInsight);
    }
    
   
    
    public static void addDebugLogMessage(String key, String source, String message) {
    	logger.debug(source +": "+message);
    	String requestId = requestIdMap.get(key);
    	getRequestInsight(requestId).addDebugMessage(source +": "+message);
    }
}