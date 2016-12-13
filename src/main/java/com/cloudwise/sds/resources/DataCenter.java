package com.cloudwise.sds.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据收集接口
 * @author www.toushibao.com
 *
 */
@Path("/dataCenter")
public class DataCenter {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@POST
	@Path("/collect")
	public String collect(String data){
		logger.info("Collected data is : {}", data);
		return "ok";
	}
}
