package org.societies.musicidentify.service;
import java.util.concurrent.Future;

import org.societies.api.identity.IIdentity;
import org.societies.api.identity.Requestor;
import org.societies.api.personalisation.mgmt.IPersonalisationManager;
import org.societies.api.personalisation.model.IAction;
import org.societies.api.schema.servicelifecycle.model.ServiceResourceIdentifier;

public class PersonalisationManager implements IPersonalisationManager{
	
	private IUserCtxBroker ctxBroker;
	
	private IUserPreferenceManagement prefMgr;
	
	public PersonalisationManager(){
			System.out.println(this.getClass().getName()+"New service with interface: "+this.getClass().getName());
	
	}
	//get and set methods for the local preference manager and context broker
	public IUserPreferenceManagement getPrefMgr(){
		System.out.println(this.getClass().getName()+"User Pref Manager returned" +prefMgr);
		return prefMgr;
	}
	
	public void setPrefMgr(IUserPreferenceManagement upm)
	{
			System.out.println(this.getClass().getName()+ "New User Preference Manager: "+upm);
			this.prefMgr=upm;
	}
	
	public IUserCtxBroker getCtxBroker(){
		System.out.println(this.getClass().getName()+"CtxBroker returned: "+ this.ctxBroker);
		return this.ctxBroker;
	}
	
	public void setCtxBroker(IUserCtxBroker broker){
		System.out.println(this.getClass().getName() + "New CtxBroker: "+broker);
		this.CtxBroker=broker;
	}
	public Future<IAction> getIntentAction(Requestor arg0, IIdentity arg1,
			ServiceResourceIdentifier arg2, String arg3) {
		// TODO Auto-generated method stub
		return null;
	}
	public Future<IAction> getPreference(Requestor arg0, IIdentity arg1,
			String arg2, ServiceResourceIdentifier arg3, String arg4) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
