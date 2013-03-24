package org.societies.musicidentify.service;

/**

 * Copyright (c) 2011, SOCIETIES Consortium (WATERFORD INSTITUTE OF TECHNOLOGY (TSSG), HERIOT-WATT UNIVERSITY (HWU), SOLUTA.NET 
 * (SN), GERMAN AEROSPACE CENTRE (Deutsches Zentrum fuer Luft- und Raumfahrt e.V.) (DLR), Zavod za varnostne tehnologije
 * informacijske družbe in elektronsko poslovanje (SETCCE), INSTITUTE OF COMMUNICATION AND COMPUTER SYSTEMS (ICCS), LAKE
 * COMMUNICATIONS (LAKE), INTEL PERFORMANCE LEARNING SOLUTIONS LTD (INTEL), PORTUGAL TELECOM INOVAÇÃO, SA (PTIN), IBM Corp., 
 * INSTITUT TELECOM (ITSUD), AMITEC DIACHYTI EFYIA PLIROFORIKI KAI EPIKINONIES ETERIA PERIORISMENIS EFTHINIS (AMITEC), TELECOM 
 * ITALIA S.p.a.(TI),  TRIALOG (TRIALOG), Stiftelsen SINTEF (SINTEF), NEC EUROPE LTD (NEC))
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following
 * conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
 *    disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING,
 * BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT 
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import javax.swing.*;
import javax.swing.filechooser.*;

import org.jaudiotagger.audio.*;
import org.jaudiotagger.tag.*;
import org.json.*;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import org.societies.api.comm.xmpp.interfaces.ICommManager;
import org.societies.api.context.CtxException;
import org.societies.api.context.broker.ICtxBroker;
import org.societies.api.context.model.*;
import org.societies.api.identity.*;
import org.societies.api.schema.servicelifecycle.model.ServiceResourceIdentifier;
import org.societies.api.services.IServices;
import org.springframework.scheduling.annotation.Async;

public class MusicIdentifyServer implements MusicIdentifyServerService{

	//Fields to get the context information related to the current user
	public ICtxBroker ctxBrokerService;
	private ICommManager commManager;
	public CtxModelObject userTaste;
	public CtxModelObject userPlaylist;
	private IIdentity user;
	public IServices serviceMgmt; 
	
	//Initialise logwriter
	//private static Logger log = LoggerFactory.getLogger(MusicIdentifyServer.class);	

	// private Requestor requestor;
	private RequestorService requestorService;
	private ServiceResourceIdentifier myServiceID;
	private String myServiceName;
	private String myServiceType;
	
	private boolean bInitialising = false;
	private boolean bInitialised = false;

	
	
	public MusicIdentifyServer(){
		
		
		
	}
	public void init() {
		
		String OS =System.getProperty("os.name");
		System.out.println("OS: "+ OS);
		
		user = getCommManager().getIdManager().getThisNetworkNode();
	}
	
	
	public IIdentity getID(){
		System.out.println("User ID: "+this.user);
		return this.user;
	}
	//getter/setter methods for the context broker service
	public ICtxBroker getBroker(){
		System.out.println("Context Broker: " +this.ctxBrokerService);
		return this.ctxBrokerService;
	}
	
	public void setBroker(ICtxBroker cxtBroker){
		this.ctxBrokerService=cxtBroker;
	}
	
	public IServices getServiceMgmt() {
		return serviceMgmt;
	}

	public void setServiceMgmt(IServices serviceMgmt) {
		this.serviceMgmt = serviceMgmt;
	}
	
	public ICommManager getCommManager() {
		return commManager;
	}

	public void setCommManager(ICommManager commManager) {
		this.commManager = commManager;
	}
	
//	private void generatePlaylist(){
//		
//		String searchQuery="";
////		int max = Integer.parseInt(getContextAttribute("PrimaryGenreCount"));
////		primaryGenre = getContextAttribute("PrimaryGenre");
////		int max2= Integer.parseInt(getContextAttribute("SecondaryGenreCount"));
////		secondaryGenre= getContextAttribute("SecondaryGenre");
//
//		int max =0;
//		int max2=0;
//		for(Entry<String,Integer> genreRecord : genreTally.entrySet()){
//			if(genreRecord.getValue()>max){
//				if(primaryGenre!="")
//					secondaryGenre=primaryGenre;
//				primaryGenre=genreRecord.getKey();
//				max2=max;
//				max=genreRecord.getValue();
//				
//			}
//		}
//		//Updated the user's music taste with the two most dominant genres.
//		if(primaryGenre!=""){
////			updateContextAttribute("PrimaryGenre", primaryGenre);
////			updateContextAttribute("PrimaryGenreCount",Integer.toString(max));
//			searchQuery+=primaryGenre.toLowerCase();
//		}
//		if(primaryGenre!=""&&secondaryGenre!="")
//				searchQuery+="+";
//		if(secondaryGenre!=""){
////			updateContextAttribute("SecondaryGenre", secondaryGenre);
////			updateContextAttribute("SecondaryGenreCount",Integer.toString(max2));
//			searchQuery+=secondaryGenre.toLowerCase();
//		}
//			
//		try{
//			//Call the music identification services URL and generate some recommended songs to match the users
//			String url ="http://developer.echonest.com/api/v4/playlist/static?api_key=77ENISIQZSEQKAUFV&artist="+URLEncoder.encode(artist,"UTF-8")+"&style="+URLEncoder.encode(searchQuery,"UTF-8")+"&format=json&results=10&type=artist-radio";
//			URL ENMFPUrl = new URL(url);
//			String JSONString="";
//			BufferedReader urlStream = new BufferedReader(new InputStreamReader(ENMFPUrl.openStream()));
//			String line = null;  
//			while ((line = urlStream.readLine()) != null && line!="") {  
//				System.out.println(line);
//				JSONString=line;
//			} 
//			JSONObject response = new JSONObject(JSONString);
//			response=response.getJSONObject("response");
//			JSONArray songs = response.getJSONArray("songs");
//			JSONObject song=null;
//			for(int i=0; i<songs.length();i++){
//				song=songs.getJSONObject(i);
//				String artist = song.getString("artist_name");
//				String title = song.getString("title");
//				automaticPlaylist+=artist+" - "+title+"\n";
//			}
//			
//			System.out.println("Finished with URL call");
//			urlStream.close();
//			
//			}
//			catch(Exception e){
//				System.out.println("Error calling ENMFP URL: "+ e);
//			}
//		
//		
//		
//		
//	}
    
//    private void updateContextAttribute(String ctxAttribName, String value) {
//		//log.debug("updateContextAtribute Start");
//
//		if (myServiceID == null)
//			myServiceID = getServiceMgmt().getMyServiceId(this.getClass());
//
//		if (requestorService == null)
//			requestorService = new RequestorService(user, myServiceID);
//
//		try {
//			// retrieve the CtxEntityIdentifier of the CSS owner context entity
//			CtxEntityIdentifier ownerEntityId = this
//					.getBroker()
//					.retrieveIndividualEntityId(
//							requestorService,
//							getCommManager().getIdManager()
//									.getThisNetworkNode()).get();
//			// create a context attribute under the CSS owner context entity
//
//			Future<List<CtxIdentifier>> ctxIdentLookupFut = this.getBroker()
//					.lookup(requestorService, ownerEntityId,
//							CtxModelType.ATTRIBUTE, ctxAttribName);
//			// Thread.sleep(1000);
//			List<CtxIdentifier> ctxIdentLookup = ctxIdentLookupFut.get();
//			CtxIdentifier ctxIdent = null;
//			CtxAttribute ctxAttr = null;
//
//			if ((ctxIdentLookup != null) && (ctxIdentLookup.size() > 0))
//				ctxIdent = ctxIdentLookup.get(0);
//
//			if (ctxIdent == null) {
//				ctxAttr = this
//						.getBroker()
//						.createAttribute(requestorService, ownerEntityId,
//								ctxAttribName).get();
//			} else {
//				Future<CtxModelObject> netUserAttrFut = this.getBroker()
//						.retrieve(requestorService, ctxIdent);
//				// Thread.sleep(1000);
//				ctxAttr = (CtxAttribute) netUserAttrFut.get();
//			}
//			// assign a String value to the attribute
//			ctxAttr.setStringValue(value);
//			ctxAttr.setValueType(CtxAttributeValueType.STRING);
//
//			// update the attribute in the Context DB
//			ctxAttr = (CtxAttribute) this.getBroker()
//					.update(requestorService, ctxAttr).get();
//
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (CtxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}

//	private String getContextAttribute(String ctxAttribName) {
//		//log.debug("getContextAtribute Start");
//		CtxAttribute ctxAttr = null;
//
//		if (requestorService == null) {
//			if (myServiceID == null)
//				myServiceID = getServiceMgmt().getMyServiceId(this.getClass());
//			requestorService = new RequestorService(user, myServiceID);
//		}
//
//		try {
//			// retrieve the CtxEntityIdentifier of the CSS owner context entity
//			CtxEntityIdentifier ownerEntityId = this
//					.getBroker()
//					.retrieveIndividualEntityId(
//							requestorService,
//							getCommManager().getIdManager()
//									.getThisNetworkNode()).get();
//			// create a context attribute under the CSS owner context entity
//
//			Future<List<CtxIdentifier>> ctxIdentLookupFut = this.getBroker()
//					.lookup(requestorService, ownerEntityId,
//							CtxModelType.ATTRIBUTE, ctxAttribName);
//			// Thread.sleep(1000);
//			List<CtxIdentifier> ctxIdentLookup = ctxIdentLookupFut.get();
//			CtxIdentifier ctxIdent = null;
//
//			if ((ctxIdentLookup != null) && (ctxIdentLookup.size() > 0))
//				ctxIdent = ctxIdentLookup.get(0);
//
//			if (ctxIdent == null) {
//				ctxAttr = this
//						.getBroker()
//						.createAttribute(requestorService, ownerEntityId,
//								ctxAttribName).get();
//			} else {
//				Future<CtxModelObject> ctxAttrFut = this.getBroker()
//						.retrieve(requestorService, ctxIdent);
//				// Thread.sleep(1000);
//				ctxAttr = (CtxAttribute) ctxAttrFut.get();
//
//			}
//
//
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (CtxException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		//log.debug("getContextAtribute End");
//
//		return ctxAttr.getStringValue();
//
//	}

 

}
