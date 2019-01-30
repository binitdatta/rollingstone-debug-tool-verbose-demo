package com.rollingstone.debugclientdemo.model.verbose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RequestInsightCollector {


	static boolean verbose;
	
    public static  ThreadLocal<Map> requestContext = new ThreadLocal<Map>();
    
    private static List<RequestInsight> requestInsights = new ArrayList<RequestInsight>();
    
    public static void startRequest(String requedtID)
    {
    	if (verbose) {
    		//requestContext = new ThreadLocal<Map>();
	    	releaseRequest();
	    	RequestInsight requestInsght = new RequestInsight();
	    	requestInsght.setStartTime(System.currentTimeMillis());
	    	requestInsght.setVerbose(verbose);
	        Map<String, Object> requestData = new HashMap<String, Object>();
	        requestData.put(requedtID, requestInsght);
	        requestContext.set(requestData);
    	}
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
}