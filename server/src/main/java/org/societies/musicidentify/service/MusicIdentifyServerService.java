package org.societies.musicidentify.service;

import org.osgi.framework.BundleContext;
import org.societies.api.comm.xmpp.interfaces.ICommManager;
import org.societies.api.context.broker.ICtxBroker;
import org.societies.api.identity.IIdentity;
import org.societies.api.services.IServices;

public interface MusicIdentifyServerService {

	public void start(BundleContext context)throws Exception;
	public void stop(BundleContext context) throws Exception;
	public IIdentity getID();
	//getter/setter methods for the context broker service
	public ICtxBroker getBroker();
	public void setBroker(ICtxBroker cxtBroker);
	
	public IServices getServiceMgmt();

	public void setServiceMgmt(IServices serviceMgmt);
	
	public ICommManager getCommManager();

	public void setCommManager(ICommManager commManager);
	public void swingInitAndGetInputFile();
}
