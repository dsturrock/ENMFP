package org.societies.musicidentify.service;

import java.io.*;
import java.lang.System;
import java.awt.Desktop;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.societies.api.context.broker.ICtxBroker;

public class IdentifyServer{
	
public ICtxBroker ctxBrokerService;
private Logger LOG = LoggerFactory.getLogger(MusicIdentifyServer.class);	

private ServiceTracker musicServiceServerTracker;
private MusicIdentifyServerService musicServer;
 
public IdentifyServer(){
	
	LOG.debug("IDENTIFY SERVER");

}
public void setService(MusicIdentifyServerService musicServer) {
this.musicServer = musicServer;
}
 
public void removeService() {
this.musicServer = null;
}


//getter/setter methods for the context broker service
public ICtxBroker getBroker(){
	LOG.debug("Context Broker: " +this.ctxBrokerService);
	return this.ctxBrokerService;
}

public void setBroker(ICtxBroker cxtBroker){
	this.ctxBrokerService=cxtBroker;
}
 
public void start(BundleContext context) throws Exception {
LOG.debug("IDENTIFY CLASS STARTED");
	musicServiceServerTracker = new ServiceTracker(context, MusicIdentifyServerService.class.getName(), null);
musicServiceServerTracker.open();

MusicIdentifyServerService mi = (MusicIdentifyServerService) musicServiceServerTracker.getService();
mi.start();

}
public void stop(BundleContext context) {
	MusicIdentifyServerService mi = (MusicIdentifyServerService) musicServiceServerTracker.getService();
	if (mi == null) {
		LOG.debug("No MusicIdentify service available for termination.");
	} else {
		LOG.debug("Music Identify Service terminated successfully");
	}
	musicServiceServerTracker.close();

}
}

