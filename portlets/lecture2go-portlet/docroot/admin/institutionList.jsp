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
<portlet:actionURL name="updateInstitutionEntry" var="updateInstitutionEntryURL"></portlet:actionURL>

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
 			ResultRow container_row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);
 			Institution institution_row = (Institution)row.getObject();
 			long institution_id = institution_row.getInstitutionId();
 			String id_row = "Inst"+String.valueOf(institution_row.getInstitutionId());
 			String name_row = String.valueOf(institution_row.getName());
 			String streamer_row = String.valueOf(institution_row.getTyp());
 			String curParam_row = "curInner"+String.valueOf(institution_row.getInstitutionId());

 		%>

 		 <liferay-ui:search-container-row-parameter name="rowId" value="<%= institution_row.toString() %>"/>

        <liferay-ui:search-container-column-text property="sort" name="Order"/>

        <liferay-ui:search-container-column-text name="Institution">

 		<aui:form action="<%= updateInstitutionEntryURL %>" name="<portlet:namespace />fm">
 			<aui:fieldset>
				<aui:input name="institution" label="Institution Name" value = "<%= name_row %>" required="true"/>
				<aui:input name="streamer" label="Streamer" value = "<%= streamer_row %>" disabled="true"/>
			</aui:fieldset>
				<aui:button type="submit"></aui:button>
 		</aui:form>
 		</liferay-ui:search-container-column-text>


 		<liferay-ui:search-container-column-text name="Advanced">

		<%

			//System.out.println(institution_id+" "+InstitutionLocalServiceUtil.getByGroupIdAndParent(new Long(0), institution_id).toString());
		%>


		<liferay-ui:panel
				defaultState="closed"
				extended="<%= false %>"
				id="<%= id_row %>"
				persistState="<%= true %>"
				title="<%= name_row %>" >


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

        			<liferay-ui:search-container-column-text property="name" name="Institution" />

        		</liferay-ui:search-container-row>
        	<liferay-ui:search-iterator searchContainer="<%= searchSubInstitutionContainer %>" />
			</liferay-ui:search-container>



		</liferay-ui:panel>
		</liferay-ui:search-container-column-text>


    </liferay-ui:search-container-row>


    <liferay-ui:search-iterator searchContainer="<%= searchInstitutionContainer %>" />
</liferay-ui:search-container>
