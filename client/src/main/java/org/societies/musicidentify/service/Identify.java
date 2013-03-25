package org.societies.musicidentify.service;
 
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTracker;
 
public class Identify implements BundleActivator {
 
	

private ServiceTracker musicServiceTracker;
private MusicIdentifyService musicService;
 
public void setService(MusicIdentifyService musicService) {
this.musicService = musicService;
}
 
public void removeService() {
this.musicService = null;
}

 
public void start(BundleContext context) throws Exception {
musicServiceTracker = new ServiceTracker(context, MusicIdentifyService.class.getName(), null);
musicServiceTracker.open();
MusicIdentifyService mi = (MusicIdentifyService) musicServiceTracker.getService();
 

}
 
public void stop(BundleContext context) {
	MusicIdentifyService mi = (MusicIdentifyService) musicServiceTracker.getService();
	if (mi == null) {
		System.out.println("No MusicIdentify service available for termination.");
	} else {
		System.out.println("Music Identify Service terminated successfully");
	}
	musicServiceTracker.close();

}
}