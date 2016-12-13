package com.cloudwise.sds.sender;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HttpSender implements Sender {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final Client jerseyClient;
	private final String url;
	public HttpSender(Client jerseyClient, String url){
		this.jerseyClient = jerseyClient;
		this.url = url;
	}
	public void send(String data) {
		ClientResponse resp = null;
		WebResource resource = jerseyClient.resource(url);
		try {
			logger.info("Send data to {}", url);
			resp = resource.entity(data).post(ClientResponse.class);
			String respStr = resp.getEntity(String.class);
			logger.info("Get response string is : {}", respStr);
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			if(resp != null) {
				resp.close();
			}
		}
	}
}
