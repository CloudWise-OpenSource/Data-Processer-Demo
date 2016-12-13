package com.cloudwise.sds.worker;

import java.util.concurrent.BlockingQueue;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloudwise.sds.sender.Sender;

public class SimulateDataSender implements Runnable {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final Sender sender;
	private final BlockingQueue<String> dataQueue;
	private volatile boolean stop = false;

	public SimulateDataSender(BlockingQueue<String> dataQueue, Sender sender) {
		this.sender = sender;
		this.dataQueue = dataQueue;
	}
	
	public void stop(){
		this.stop = true;
	}

	public void run() {
		logger.info("Data sender is starting.");
		while(!stop && !Thread.currentThread().isInterrupted()){
			try {
				String data = dataQueue.take();
				logger.info("Data sender will send data.");
				sender.send(data);
			} catch (Exception e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
	}
}
