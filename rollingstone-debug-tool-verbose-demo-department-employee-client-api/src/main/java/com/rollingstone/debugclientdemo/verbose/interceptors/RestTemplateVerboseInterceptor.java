package com.rollingstone.debugclientdemo.verbose.interceptors;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.rollingstone.debugclientdemo.model.verbose.RequestInsight;
import com.rollingstone.debugclientdemo.model.verbose.RequestInsightCollector;

public class RestTemplateVerboseInterceptor implements ClientHttpRequestInterceptor {
 
    @Override
    public ClientHttpResponse intercept(
      HttpRequest request, 
      byte[] body, 
      ClientHttpRequestExecution execution) throws IOException {
  
        ClientHttpResponse response = execution.execute(request, body);
        
        String requestUri = request.getURI().getPath();

        if (requestUri.contains("department") && !requestUri.contains("employees")) {
	        if (RequestInsightCollector.isVerbose()) {
	        	String requestId = RequestInsightCollector.getRequestIdFromMap("DEPARTMENT");
				RequestInsight requestInsight = RequestInsightCollector.getRequestInsight(requestId);
				if (requestInsight != null && requestInsight.isVerbose()) {
					requestInsight.setRequestID(requestId);
					requestInsight.setRequestResponse(response.getBody().toString());
					String result = IOUtils.toString(response.getBody(), StandardCharsets.UTF_8);
					requestInsight.setRequestResponse(result);
					requestInsight.setRequestStatus(String.valueOf(response.getRawStatusCode()));
					requestInsight.setRequestURI(request.getURI().toString());
					requestInsight.setElaspsedTime(RequestInsightCollector.getElapsedTime(requestId));
					RequestInsightCollector.addRequestInsight(requestInsight);
					response = execution.execute(request, body);
				}
			}
        }
        else if (requestUri.contains("employee")) {
	        if (RequestInsightCollector.isVerbose()) {
	        	String requestId = RequestInsightCollector.getRequestIdFromMap("EMPLOYEE");
				RequestInsight requestInsight = RequestInsightCollector.getRequestInsight(requestId);
				if (requestInsight != null && requestInsight.isVerbose()) {
					requestInsight.setRequestID(requestId);
					String result = IOUtils.toString(response.getBody(), StandardCharsets.UTF_8);
					requestInsight.setRequestResponse(result);
					requestInsight.setRequestStatus(String.valueOf(response.getRawStatusCode()));
					requestInsight.setRequestURI(request.getURI().toString());
					requestInsight.setElaspsedTime(RequestInsightCollector.getElapsedTime(requestId));
					RequestInsightCollector.addRequestInsight(requestInsight);
					response = execution.execute(request, body);
				}
			}
        }
        return response;
    }

}
