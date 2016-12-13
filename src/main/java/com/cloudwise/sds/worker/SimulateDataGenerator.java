package com.cloudwise.sds.worker;

import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudwise.sdg.template.TemplateAnalyzer;

public class SimulateDataGenerator implements Runnable  {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final BlockingQueue<String> dataQueue;
	private final TemplateAnalyzer tplAnalyzer;
	private volatile boolean stop = false;
	private final long interval = 1000;
	public SimulateDataGenerator(BlockingQueue<String> dataQueue, TemplateAnalyzer tplAnalyzer){
		this.dataQueue = dataQueue;	
		this.tplAnalyzer = tplAnalyzer;
	}
	
	public void stop(){
		this.stop = true;
	}
	
	public void run() {
		logger.info("Template analyzer[{}] is starting.", tplAnalyzer.getTplName());
		while(!stop && !Thread.currentThread().isInterrupted()){
			logger.info("Template analyzer[{}] analyse template.", tplAnalyzer.getTplName());
			String data = tplAnalyzer.analyse();
			try {
				dataQueue.put(data);
			} catch (InterruptedException e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
			
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
