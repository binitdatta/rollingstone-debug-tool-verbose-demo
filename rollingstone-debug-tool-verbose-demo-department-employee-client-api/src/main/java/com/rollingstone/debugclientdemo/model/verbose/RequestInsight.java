package com.rollingstone.debugclientdemo.model.verbose;

import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class RequestInsight {

	private String requestID;
	private String requestURI;
	private String requestBody;
	private String requestResponse;
	private String requestStatus;
	private long startTime;
	private double elaspsedTime;
	private boolean verbose;

	@JsonInclude(Include.NON_EMPTY)	
	private ArrayList<String> debugLogMessages = new ArrayList<String>();

	@JsonInclude(Include.NON_EMPTY)	
	private ArrayList<String> externalAPICallMessages = new ArrayList<String>();

	public String getRequestID() {
		return requestID;
	}

	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	public String getRequestURI() {
		return requestURI;
	}

	public void setRequestURI(String requestURI) {
		this.requestURI = requestURI;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public String getRequestResponse() {
		return requestResponse;
	}

	public void setRequestResponse(String requestResponse) {
		this.requestResponse = requestResponse;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}



	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public ArrayList<String> getDebugLogMessages() {
		return debugLogMessages;
	}

	public void setDebugLogMessages(ArrayList<String> debugLogMessages) {
		this.debugLogMessages = debugLogMessages;
	}

	public void addDebugMessage(String debugMessage) {
		debugLogMessages.add(debugMessage);
	}

	public ArrayList<String> getExternalAPICallMessages() {
		return externalAPICallMessages;
	}

	public void setExternalAPICallMessages(ArrayList<String> externalAPICallMessages) {
		this.externalAPICallMessages = externalAPICallMessages;
	}

	public void addExternalCallMessages(String debugMessage) {
		externalAPICallMessages.add(debugMessage);
	}

	

	public double getElaspsedTime() {
		return elaspsedTime;
	}

	public void setElaspsedTime(double elaspsedTime) {
		this.elaspsedTime = elaspsedTime;
	}

	public RequestInsight() {
		super();
	}

	public RequestInsight(String requestID, String requestURI, String requestBody, String requestResponse,
			String requestStatus, long startTime, double elaspsedTime, boolean verbose,
			ArrayList<String> debugLogMessages, ArrayList<String> externalAPICallMessages) {
		super();
		this.requestID = requestID;
		this.requestURI = requestURI;
		this.requestBody = requestBody;
		this.requestResponse = requestResponse;
		this.requestStatus = requestStatus;
		this.startTime = startTime;
		this.elaspsedTime = elaspsedTime;
		this.verbose = verbose;
		this.debugLogMessages = debugLogMessages;
		this.externalAPICallMessages = externalAPICallMessages;
	}

	@Override
	public String toString() {
		return "RequestInsight [requestID=" + requestID + ", requestURI=" + requestURI + ", requestBody=" + requestBody
				+ ", requestResponse=" + requestResponse + ", requestStatus=" + requestStatus + ", startTime="
				+ startTime + ", elaspsedTime=" + elaspsedTime + ", verbose=" + verbose + ", debugLogMessages="
				+ debugLogMessages + ", externalAPICallMessages=" + externalAPICallMessages + "]";
	}

	
}
