package com.cloudwise.sds.config;

public class Worker {
	private String templateName;
	private int dgThNum;
	private int dgInterval;
	private int queueSize;
	private int dsThNum;
	private int dsInterval;
	private String dsUrl;

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public int getDgThNum() {
		return dgThNum;
	}

	public void setDgThNum(int dgThNum) {
		this.dgThNum = dgThNum;
	}

	public int getDgInterval() {
		return dgInterval;
	}

	public void setDgInterval(int dgInterval) {
		this.dgInterval = dgInterval;
	}

	public int getQueueSize() {
		return queueSize;
	}

	public void setQueueSize(int queueSize) {
		this.queueSize = queueSize;
	}

	public int getDsThNum() {
		return dsThNum;
	}

	public void setDsThNum(int dsThNum) {
		this.dsThNum = dsThNum;
	}

	public int getDsInterval() {
		return dsInterval;
	}

	public void setDsInterval(int dsInterval) {
		this.dsInterval = dsInterval;
	}

	public String getDsUrl() {
		return dsUrl;
	}

	public void setDsUrl(String dsUrl) {
		this.dsUrl = dsUrl;
	}

}
