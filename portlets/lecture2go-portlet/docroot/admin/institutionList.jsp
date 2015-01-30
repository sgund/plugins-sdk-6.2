<%@include file="/init.jsp"%>
<%@ page import="de.uhh.l2g.plugins.model.Institution" %>
<%@ page import="de.uhh.l2g.plugins.service.InstitutionLocalServiceUtil" %>
<%@ page import="de.uhh.l2g.plugins.service.HostLocalServiceUtil" %>

<liferay-ui:error key="host-or-institution-error" message="host-or-institution-error"/>

<portlet:renderURL var="viewURL"><portlet:param name="jspPage" value="/admin/institutionList.jsp" /></portlet:renderURL>
<portlet:actionURL name="addInstitutionEntry" var="addInstitutionEntryURL"></portlet:actionURL>

<%
long institutionId = Long.valueOf((Long) renderRequest.getAttribute("institutionId"));

PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setParameter("institutionId", institutionId+"");
List<Institution> institutions = InstitutionLocalServiceUtil.getByGroupId(0);

for (int i = 0; i < institutions.size(); i++) {
	Institution curInstitution = (Institution) institutions.get(i);
}
%>

<aui:form action="<%= addInstitutionEntryURL %>" name="<portlet:namespace />fm">

		<aui:fieldset>
			<aui:input name="institution" label="Institution" required="true"/>
            <aui:input name="serverselect" label="Select Streaming Server"></aui:input>

        </aui:fieldset>
        
       <liferay-ui:panel 
		    	defaultState="closed" 
		    	extended="<%= false %>" 
		    	id="advancedPanel" 
		    	persistState="<%= true %>" 
		    	title="Advanced Streaming Server Options">
		
		 	    <aui:fieldset>
					<aui:input label="Server Name" name="name" required="true"></aui:input>
		 	        <aui:input label="Streaming Server Domain or IP" name="ip"></aui:input>
		 	        <aui:input label="HTTP Protocol" name="protocol"></aui:input>
		 	        <aui:input label="Server Template" name="template"></aui:input>  
		 	        <aui:button value="Refresh" label="Add to List" type="button" onClick="<%= viewURL.toString() %>"></aui:button>          
		 	    </aui:fieldset>

		</liferay-ui:panel>


        <aui:button-row>
            <aui:button type="submit"></aui:button>
            <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
        </aui:button-row>

</aui:form>


