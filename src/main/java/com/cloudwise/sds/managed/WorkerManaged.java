package com.cloudwise.sds.managed;

import java.io.File;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

import org.apache.commons.io.FileUtils;

import com.cloudwise.sdg.template.TemplateAnalyzer;
import com.cloudwise.sds.config.Worker;
import com.cloudwise.sds.sender.HttpSender;
import com.cloudwise.sds.sender.Sender;
import com.cloudwise.sds.worker.SimulateDataGenerator;
import com.cloudwise.sds.worker.SimulateDataSender;
import com.sun.jersey.api.client.Client;

import io.dropwizard.lifecycle.Managed;

public class WorkerManaged  implements Managed{
	private final ExecutorService executor;
	private final List<Worker> workers;
	private final Client jerseyClient;
	public WorkerManaged(ExecutorService executor, List<Worker> workers, Client jerseyClient){
		this.executor = executor;
		this.workers = workers;
		this.jerseyClient = jerseyClient;
	}

	public void start() throws Exception {
		for(Worker worker : workers){
			int queueSize = worker.getQueueSize();
			String tplPath = worker.getTemplateName();
			 
			BlockingQueue<String> dataQueue = new ArrayBlockingQueue<String>(queueSize);
			String mobileTpl = FileUtils.readFileToString(new File(tplPath));
			TemplateAnalyzer tplAnalyzer = new TemplateAnalyzer(tplPath,mobileTpl);
			executor.submit(new SimulateDataGenerator(dataQueue, tplAnalyzer));
			
			String url = worker.getDsUrl();
			Sender sender = new HttpSender(jerseyClient, url);
			executor.submit(new SimulateDataSender(dataQueue, sender));
		}
	}

	public void stop() throws Exception {
		System.out.println("======================================");
		if(!executor.isShutdown()){
			executor.shutdown();
		}
	}
}
