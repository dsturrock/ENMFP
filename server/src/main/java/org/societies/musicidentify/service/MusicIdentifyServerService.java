package org.societies.musicidentify.service;

import org.societies.api.comm.xmpp.interfaces.ICommManager;
import org.societies.api.context.broker.ICtxBroker;
import org.societies.api.identity.IIdentity;
import org.societies.api.personalisation.model.IActionConsumer;
import org.societies.api.services.IServices;

public interface MusicIdentifyServerService extends IActionConsumer {


	public void start()throws Exception;
	public void stop() throws Exception;
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
