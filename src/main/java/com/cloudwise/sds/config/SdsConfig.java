package com.cloudwise.sds.config;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

public class SdsConfig extends Configuration {
	@Valid
	@NotNull
	@JsonProperty("jerseyClient")
	private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

	public JerseyClientConfiguration getJerseyClientConfiguration() {
		return jerseyClient;
	}
	
	@Valid
	@NotNull
	@JsonProperty("workers")
	private List<Worker> workers = new ArrayList<Worker>();

	public List<Worker> getWorkers() {
		return workers;
	}
	
	@Valid
	@NotNull
	private boolean testServerEnabled = true;
	
	public boolean isTestServerEnabled(){
		return testServerEnabled;
	}
}
