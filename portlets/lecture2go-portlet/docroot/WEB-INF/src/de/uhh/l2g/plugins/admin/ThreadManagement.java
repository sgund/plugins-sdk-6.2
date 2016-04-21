package de.uhh.l2g.plugins.admin;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

import de.uhh.l2g.plugins.model.ScheduledThread;
import de.uhh.l2g.plugins.util.PermissionManager;
import de.uhh.l2g.plugins.util.PortletScheduler;

public class ThreadManagement extends MVCPortlet {

	


	/**Set default permissions (assumes fixed and unique role names)
	 * 
	 * @param pm - PermissionManager 
	 * @throws PortalException 
	 * @throws SystemException 
	 */
	private void setDefaultPermissions(PermissionManager pm) throws SystemException, PortalException{
		

	}
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException, IOException {
        
		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
			         ScheduledThread.class.getName(), renderRequest);
        
	
	    } catch (Exception e) {
	    	throw new PortletException(e);
	    }

		super.render(renderRequest, renderResponse);
	 
	}
	
	public void startJob(ActionRequest request, ActionResponse response){
		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					ScheduledThread.class.getName(), request);
			 
			  String schedulerClassName = ParamUtil.getString(request, "schedulerName");
			 
			  //Make sure to use the appropriate Message Consumer 
			  ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader(serviceContext.getPortletId()); //Where portletID is not null
			  PortletScheduler scheduler = (PortletScheduler) classLoader.loadClass(schedulerClassName).newInstance();
			  scheduler.initScheduler(schedulerClassName, serviceContext.getPortletId());
			  System.out.println(scheduler.getPortletId());
			  scheduler.start();

		 } catch (Exception e) {
			SessionErrors.add(request, e.getClass().getName());
			PortalUtil.copyRequestParameters(request, response);
			
			System.out.println(e.getClass().getName());
			e.printStackTrace();

			response.setRenderParameter("mvcPath",
					"/admin/jobs.jsp");
		}
	}
	
	public void resumeJob(ActionRequest request, ActionResponse response){
		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					ScheduledThread.class.getName(), request);
			 
			  String schedulerClassName = ParamUtil.getString(request, "schedulerName");
			 
			  //Make sure to use the appropriate Message Consumer 
			  ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader(serviceContext.getPortletId()); //Where portletID is not null
			  PortletScheduler scheduler = (PortletScheduler) classLoader.loadClass(schedulerClassName).newInstance();
			  scheduler.initScheduler(schedulerClassName, serviceContext.getPortletId());
			  System.out.println(scheduler.getPortletId());
			  scheduler.resume();

		 } catch (Exception e) {
			SessionErrors.add(request, e.getClass().getName());
			PortalUtil.copyRequestParameters(request, response);
			
			System.out.println(e.getClass().getName());
			e.printStackTrace();

			response.setRenderParameter("mvcPath",
					"/admin/jobs.jsp");
		}
	}
	public void pauseJob(ActionRequest request, ActionResponse response){		
		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					ScheduledThread.class.getName(), request);
			  String schedulerClassName = ParamUtil.getString(request, "schedulerName");
				
			 //Make sure to use the appropriate Message Consumer 
			  ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader(serviceContext.getPortletId()); //Where portletID is not null
			  PortletScheduler scheduler = (PortletScheduler) classLoader.loadClass(schedulerClassName).newInstance();
			  
			  scheduler.initScheduler(schedulerClassName, serviceContext.getPortletId());
			  System.out.println(scheduler.getPortletId());
			  
			  scheduler.pause();

		 } catch (Exception e) {
			SessionErrors.add(request, e.getClass().getName());
			PortalUtil.copyRequestParameters(request, response);
			
			System.out.println(e.getClass().getName());
			e.printStackTrace();
			
			response.setRenderParameter("mvcPath",
					"/admin/jobs.jsp");
		}
	}
	
	public void updateJob(ActionRequest request, ActionResponse response){		
		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					ScheduledThread.class.getName(), request);
			  String schedulerClassName = ParamUtil.getString(request, "schedulerName");
				
			 //Make sure to use the appropriate Message Consumer 
			  ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader(serviceContext.getPortletId()); //Where portletID is not null
			  PortletScheduler scheduler = (PortletScheduler) classLoader.loadClass(schedulerClassName).newInstance();
			  
			  scheduler.initScheduler(schedulerClassName, serviceContext.getPortletId());
			  System.out.println(scheduler.getPortletId());
			  
			  scheduler.update();

		 } catch (Exception e) {
			SessionErrors.add(request, e.getClass().getName());
			PortalUtil.copyRequestParameters(request, response);
			
			System.out.println(e.getClass().getName());
			e.printStackTrace();
			
			response.setRenderParameter("mvcPath",
					"/admin/jobs.jsp");
		}
	}
	public void stopJob(ActionRequest request, ActionResponse response){		
		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					ScheduledThread.class.getName(), request);
			  String schedulerClassName = ParamUtil.getString(request, "schedulerName");
				
			 //Make sure to use the appropriate Message Consumer 
			  ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader(serviceContext.getPortletId()); //Where portletID is not null
			  PortletScheduler scheduler = (PortletScheduler) classLoader.loadClass(schedulerClassName).newInstance();
			  
			  scheduler.initScheduler(schedulerClassName, serviceContext.getPortletId());
			  System.out.println(scheduler.getPortletId());
			  
			  scheduler.stop();

		 } catch (Exception e) {
			SessionErrors.add(request, e.getClass().getName());
			PortalUtil.copyRequestParameters(request, response);
			
			System.out.println(e.getClass().getName());
			e.printStackTrace();
			
			response.setRenderParameter("mvcPath",
					"/admin/jobs.jsp");
		}
	}
	
	public void removeJob(ActionRequest request, ActionResponse response){		
		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					ScheduledThread.class.getName(), request);
			  String schedulerClassName = ParamUtil.getString(request, "schedulerName");
				
			 //Make sure to use the appropriate Message Consumer 
			  ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader(serviceContext.getPortletId()); //Where portletID is not null
			  PortletScheduler scheduler = (PortletScheduler) classLoader.loadClass(schedulerClassName).newInstance();
			  
			  scheduler.initScheduler(schedulerClassName, serviceContext.getPortletId());
			  System.out.println(scheduler.getPortletId());
			  
			  scheduler.stop();

		 } catch (Exception e) {
			SessionErrors.add(request, e.getClass().getName());
			PortalUtil.copyRequestParameters(request, response);
			
			System.out.println(e.getClass().getName());
			e.printStackTrace();
			
			response.setRenderParameter("mvcPath",
					"/admin/jobs.jsp");
		}
	}
	
	public void removeAllJobs(ActionRequest request, ActionResponse response){
		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					ScheduledThread.class.getName(), request);
			
			  String schedulerClassName = ParamUtil.getString(request, "schedulerName");
			  if (schedulerClassName.isEmpty()){
				  PortletScheduler.removeAllPortletSchedulerJobs();
			  }
			  else{	//Use the correct Message Consumer 
			  	ClassLoader classLoader = PortletClassLoaderUtil.getClassLoader(serviceContext.getPortletId()); //Where portletID is not null
			  	PortletScheduler scheduler = (PortletScheduler) classLoader.loadClass(schedulerClassName).newInstance();
			  
			  	scheduler.removeAllJobs();
			  }
		 } catch (Exception e) {
			SessionErrors.add(request, e.getClass().getName());
			PortalUtil.copyRequestParameters(request, response);

			response.setRenderParameter("mvcPath",
					"/admin/jobs.jsp");
		}
	}
	
	/**
	 * 
	 * TODO: Remove duplicates from List before the can be scheduled, or correctly remove allready unscheduled Entries
	 */
	public void init() throws PortletException{	
		super.init();
		
		/*Drop table LG_VideoStatistics and initialize view
		 * 
		 */
	}
	
	public void destroy(){
	//	System.out.println(this.getPortletName());
	//	PortletScheduler.ListSchedulerEntries(this.getPortletName());
		super.destroy();
	}
	
}
