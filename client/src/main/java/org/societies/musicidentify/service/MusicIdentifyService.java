package org.societies.musicidentify.service;

import org.societies.api.comm.xmpp.interfaces.ICommManager;
import org.societies.api.context.broker.ICtxBroker;
import org.societies.api.identity.IIdentity;
import org.societies.api.services.IServices;

public interface MusicIdentifyService {
	public IIdentity getID();
	//getter/setter methods for the context broker service
	public ICtxBroker getBroker();
	
	public void setBroker(ICtxBroker cxtBroker);
	
	public IServices getServiceMgmt();
	public void setServiceMgmt(IServices serviceMgmt);
	
	public ICommManager getCommManager() ;

	public void setCommManager(ICommManager commManager);
	
}