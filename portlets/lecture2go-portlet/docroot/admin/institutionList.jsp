<%@include file="/init.jsp"%>
<%@ page import="de.uhh.l2g.plugins.model.Institution" %>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
<%@ page import="de.uhh.l2g.plugins.model.Host" %>
<%@ page import="de.uhh.l2g.plugins.service.InstitutionLocalServiceUtil" %>
<%@ page import="de.uhh.l2g.plugins.service.HostLocalServiceUtil" %>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchContainer" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%!com.liferay.portal.kernel.dao.search.SearchContainer<Institution> searchInstitutionContainer = null;%>
<%!com.liferay.portal.kernel.dao.search.SearchContainer<Institution> searchSubInstitutionContainer = null;%>

<liferay-ui:error key="host-or-institution-error" message="host-or-institution-error"/>

<portlet:renderURL var="viewURL"><portlet:param name="jspPage" value="/admin/institutionList.jsp" /></portlet:renderURL>
<liferay-portlet:renderURL varImpl="outerURL"><portlet:param name="jspPage" value="/admin/institutionList.jsp" /></liferay-portlet:renderURL>
<liferay-portlet:renderURL varImpl="innerURL"><portlet:param name="jspPage" value="/admin/institutionList.jsp" /></liferay-portlet:renderURL>
<portlet:actionURL name="addInstitutionEntry" var="addInstitutionEntryURL"></portlet:actionURL>
<portlet:actionURL name="addSubInstitutionEntry" var="addSubInstitutionEntryURL"></portlet:actionURL>
<portlet:actionURL name="updateInstitutionEntry" var="updateInstitutionEntryURL"></portlet:actionURL>
<portlet:actionURL name="updateSubInstitutionEntry" var="updateSubInstitutionEntryURL"></portlet:actionURL>
<portlet:actionURL name="updateServerEntry" var="updateServerEntryURL"></portlet:actionURL>
<portlet:actionURL name="updateTopLevelInstitutionEntry" var="updateTopLevelInstitutionEntryURL"></portlet:actionURL>

<%
long institutionId = Long.valueOf((Long) renderRequest.getAttribute("institutionId"));
long hostId = Long.valueOf((Long) renderRequest.getAttribute("hostId"));

PortletURL portletURL = renderResponse.createRenderURL();
portletURL.setParameter("institutionId", institutionId+"");
portletURL.setParameter("hostId", hostId+"");
List<Institution> institutions = InstitutionLocalServiceUtil.getByGroupIdAndParent(0,1);

for (int i = 0; i < institutions.size(); i++) {
	Institution curInstitution = (Institution) institutions.get(i);
	long curId = curInstitution.getInstitutionId();
}
%>

<liferay-ui:panel title="Edit Institution Settings" collapsible="true" id="institutionSettings"
				defaultState="open"
				extended="<%= false %>"
				persistState="<%= true %>">
<aui:form action="<%= addInstitutionEntryURL %>" name="<portlet:namespace />fm">

		<aui:fieldset>
			<aui:input name="institution" label="Institution" required="true" inlineField="true"/>

            <aui:input name="serverselect" label="Select Streaming Server" inlineField="true"></aui:input>
			<aui:button type="submit" value="Add" ></aui:button>
			<aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
        </aui:fieldset>

</aui:form>
</liferay-ui:panel>
       <liferay-ui:panel title="Streaming Server Options" collapsible="true" id="serverSettings"
		    	defaultState="open"
		    	extended="<%= false %>"
		    	persistState="<%= true %>">
<aui:form action="<%= updateServerEntryURL %>" name="<portlet:namespace />fm" inlineLabel="true">
				<aui:button-row>
		 	    <aui:fieldset column="true">
					<aui:input label="Server Name" name="name" required="true" inlineField="true"></aui:input>
		 	        <aui:input label="Streaming Server Domain or IP" name="ip" inlineField="true"></aui:input>
		 	        <aui:input label="HTTP Protocol" name="protocol" inlineField="true"></aui:input>
		 	        <aui:input label="Server Template" name="template"></aui:input>
		 	        <aui:input name='hostId' type='hidden' inlineField="true" value='<%= ParamUtil.getString(renderRequest, "hostId") %>'/>
		 	        <aui:button type="submit"></aui:button>
					<aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
		 	    </aui:fieldset>
		 	    </aui:button-row>
</aui:form>
</liferay-ui:panel>

<liferay-ui:panel title="Top Level Institution" collapsible="true" id="topLevelInstitutionSettings"
				defaultState="closed"
				extended="<%= false %>"
				persistState="<%= true %>">
<aui:form action="<%= updateTopLevelInstitutionEntryURL %>" name="<portlet:namespace />fm">
	<aui:fieldset>
			<aui:input name="institution" label="Institution" required="true" inlineField="true"/>
			<aui:button type="submit"></aui:button>
			<aui:button type="cancel" onClick="<%= viewURL.toString() %>"></aui:button>
	</aui:fieldset>
</aui:form>
</liferay-ui:panel>

<liferay-ui:panel title="List of Institutions" collapsible="false" id="outerList">

<liferay-ui:search-container searchContainer="<%= searchInstitutionContainer %>"
curParam ="curOuter"
orderByCol="sort"
orderByType="asc"
emptyResultsMessage="there-are-no-institutions"
delta="20"
iteratorURL="<%= outerURL %>"
deltaConfigurable="true">
    <liferay-ui:search-container-results
        results="<%=InstitutionLocalServiceUtil.getByGroupIdAndParent(new Long(0), new Long(1), searchContainer.getStart(), searchContainer.getEnd())%>"
        total="<%=InstitutionLocalServiceUtil.getByGroupIdAndParentCount(new Long(0), new Long(1))%>" />

    <liferay-ui:search-container-row
        className="de.uhh.l2g.plugins.model.Institution" modelVar="institution"
        keyProperty="institutionId"  escapedModel="<%= false %>" indexVar="i">

        <%
 			ResultRow container_row = (ResultRow)request.getAttribute("curOuter");
 			Institution institution_row = (Institution)row.getObject();
 			long institution_id = institution_row.getInstitutionId();
 			String id_row = "Inst"+String.valueOf(institution_row.getInstitutionId());
 			String curParam_row = "curInner"+String.valueOf(institution_row.getInstitutionId());

 		%>

 		 <liferay-ui:search-container-row-parameter name="rowId" value="<%= institution_row.toString() %>"/>

        <liferay-ui:search-container-column-text property="sort" name="Order"/>

        <liferay-ui:search-container-column-text name="Institution">

 		<aui:form action="<%= updateInstitutionEntryURL %>" name="<portlet:namespace />fm">
 			<aui:fieldset>
				<aui:input name="institution" label="Institution Name" inlineField="true" value = "<%= institution.getName() %>" />
				<aui:input name="streamer" label="Streamer" inlineField="true" value = "<%= institution.getTyp() %>" disabled="true"/>
				<aui:button type="submit"></aui:button>
			</aui:fieldset>
 		</aui:form>
 		<aui:form action="<%= addSubInstitutionEntryURL %>" name="<portlet:namespace />fm">
 			<aui:fieldset>
				<aui:input name="subinstitution" label="SubInstitution Name" inlineField="true" value = "<%= institution.getName() %>" />
				<aui:button type="submit" value="Add"></aui:button>
			</aui:fieldset>
 		</aui:form>

		<liferay-ui:panel
				defaultState="closed"
				extended="<%= false %>"
				id="<%= id_row %>"
				persistState="<%= true %>"
				title="<%= institution.getName() %>" >


			<liferay-ui:search-container searchContainer="<%= searchSubInstitutionContainer %>"
				curParam ="<%=curParam_row%>"
				orderByCol="sort"
				orderByType="asc"
				emptyResultsMessage="there-are-no-institutions"
				iteratorURL="<%= innerURL %>"
				delta="20"
				deltaConfigurable="true" >


				<liferay-ui:search-container-results
        			results="<%=InstitutionLocalServiceUtil.getByGroupIdAndParent(new Long(0), institution_id, searchContainer.getStart(), searchContainer.getEnd())%>"
        			total="<%=InstitutionLocalServiceUtil.getByGroupIdAndParentCount(new Long(0), institution_id)%>" />

				<liferay-ui:search-container-row
				className="de.uhh.l2g.plugins.model.Institution" modelVar="subInstitution"
				keyProperty="institutionId"  escapedModel="<%= false %>" indexVar="j">


        			<liferay-ui:search-container-column-text property="sort" name="Order"/>

        			<liferay-ui:search-container-column-text name="Institution" >
        			 <aui:form action="<%= updateSubInstitutionEntryURL %>" name="<portlet:namespace />fm">
 						<aui:fieldset>
							<aui:input name="institution" label="Institution Name" inlineField="true" value = "<%= subInstitution.getName() %>" />
							<aui:button type="submit"></aui:button>
						</aui:fieldset>
 						</aui:form>
        			</liferay-ui:search-container-column-text>

        		</liferay-ui:search-container-row>
        	<liferay-ui:search-iterator searchContainer="<%= searchSubInstitutionContainer %>" />
			</liferay-ui:search-container>



		</liferay-ui:panel>
		</liferay-ui:search-container-column-text>


    </liferay-ui:search-container-row>


    <liferay-ui:search-iterator searchContainer="<%= searchInstitutionContainer %>" />
</liferay-ui:search-container>
</liferay-ui:panel>
