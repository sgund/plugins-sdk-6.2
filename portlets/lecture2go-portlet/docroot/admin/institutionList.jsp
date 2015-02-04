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
		 	        <aui:input name='institutionId' type='hidden' value='<%= ParamUtil.getString(renderRequest, "institutionId") %>'/>
		 	        <aui:button value="Refresh" label="Add to List" type="button" onClick="<%= viewURL.toString() %>"></aui:button>          
		 	    </aui:fieldset>

		</liferay-ui:panel>


        <aui:button-row>
            <aui:button type="submit"></aui:button>
            <aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
        </aui:button-row>

</aui:form>

<liferay-ui:search-container 
orderByCol="sort" 
orderByType="asc"  
emptyResultsMessage="there-are-no-institutions"  
delta="20"
deltaConfigurable="true">
    <liferay-ui:search-container-results
        results="<%=InstitutionLocalServiceUtil.getByGroupIdAndParent(new Long(0), new Long(1), searchContainer.getStart(), searchContainer.getEnd())%>"
        total="<%=InstitutionLocalServiceUtil.getByGroupIdAndParentCount(new Long(0), new Long(1))%>" />
		
    <liferay-ui:search-container-row
        className="de.uhh.l2g.plugins.model.Institution" modelVar="institution"
        keyProperty="intsitutionId">
        
        <liferay-ui:search-container-column-text property="sort" name="institution" colspan="2"/>

        <liferay-ui:search-container-column-text property="name" />
        

    </liferay-ui:search-container-row>


    <liferay-ui:search-iterator />
</liferay-ui:search-container>
