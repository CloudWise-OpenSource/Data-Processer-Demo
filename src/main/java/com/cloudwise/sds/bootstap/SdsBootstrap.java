package com.cloudwise.sds.bootstap;

import java.util.List;
import java.util.concurrent.ExecutorService;

import com.cloudwise.sdg.dic.DicInitializer;
import com.cloudwise.sds.config.SdsConfig;
import com.cloudwise.sds.config.Worker;
import com.cloudwise.sds.managed.WorkerManaged;
import com.cloudwise.sds.resources.DataCenter;
import com.sun.jersey.api.client.Client;

import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

/**
 * 服务启动入口
 * @author www.toushibao.com
 *
 */
public class SdsBootstrap extends Application<SdsConfig>{
	public static void main(String[] args) throws Exception {
		new SdsBootstrap().run(new String[]{"server", "conf/sds.yml"});
	}

	@Override
	public void initialize(Bootstrap<SdsConfig> bootstrap) {
		try {
			DicInitializer.init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run(SdsConfig configuration, Environment environment) throws Exception {
		ExecutorService executor = environment.lifecycle().executorService("Thread-%d").minThreads(100).maxThreads(100).build();
		final Client jerseyClient = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration()).build(getName());
		if(configuration.isTestServerEnabled()){
			environment.jersey().register(new DataCenter());
		} else {
			environment.jersey().disable();
		}
		
		List<Worker> workers = configuration.getWorkers();
		environment.lifecycle().manage(new WorkerManaged(executor, workers, jerseyClient));
	}
	
	@Override
	public String getName() {
        return "simulatedata-sender";
    }
}
