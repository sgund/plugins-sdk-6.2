<%@include file="/init.jsp"%>

<%
	PortletURL portletURL = renderResponse.createRenderURL();
	List<Video> tempVideosList = new ArrayList();
	List<Coordinator> coordinators = new ArrayList<Coordinator>();
	List<Producer> producers = new ArrayList<Producer>();
	List<Lectureseries> lectureseries = new ArrayList<Lectureseries>();

	Long coordinatorId = new Long(0);
	Long producerId = new Long(0);

	Long lectureseriesId = ServletRequestUtils.getLongParameter(request, "lectureseriesId", 0);
	portletURL.setParameter("lectureseriesId", lectureseriesId+"");

	if(permissionAdmin){
		coordinators = CoordinatorLocalServiceUtil.getAllCoordinators(com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS , com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS);
		coordinatorId = ServletRequestUtils.getLongParameter(request, "coordinatorId", 0);
		portletURL.setParameter("coordinatorId", coordinatorId+"");
		producerId = ServletRequestUtils.getLongParameter(request, "producerId", 0);
		portletURL.setParameter("coordinatorId", coordinatorId+"");		
		if(coordinatorId>0){
			Long facilityId = CoordinatorLocalServiceUtil.getCoordinator(coordinatorId).getInstitutionId();
			producers = ProducerLocalServiceUtil.getProducersByInstitutionId(facilityId);
			if(producerId==0)tempVideosList = VideoLocalServiceUtil.getByInstitution(facilityId);
			else {
				lectureseries = LectureseriesLocalServiceUtil.getFilteredBySemesterFacultyProducer(1, "", new Long(0), producerId);
				if(lectureseriesId==0) tempVideosList = VideoLocalServiceUtil.getByProducer(producerId);
				else tempVideosList = VideoLocalServiceUtil.getByProducerAndLectureseries(producerId, lectureseriesId);
			}
		}else{
			tempVideosList = VideoLocalServiceUtil.getVideos(com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS , com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS);
		}
	}else{
		if(permissionCoordinator){
			coordinatorId = remoteUser.getUserId();
			producerId = ServletRequestUtils.getLongParameter(request, "producerId", 0);
			portletURL.setParameter("coordinatorId", coordinatorId+"");
			portletURL.setParameter("producerId", producerId+"");
			Long facilityId = CoordinatorLocalServiceUtil.getCoordinator(coordinatorId).getInstitutionId();
			producers = ProducerLocalServiceUtil.getProducersByInstitutionId(facilityId);
			if(producerId>0){
				lectureseries = LectureseriesLocalServiceUtil.getFilteredBySemesterFacultyProducer(1, "", new Long(0), producerId);
				if(lectureseriesId==0)tempVideosList = VideoLocalServiceUtil.getByProducer(producerId);
				else tempVideosList = VideoLocalServiceUtil.getByProducerAndLectureseries(producerId, lectureseriesId);
			}else{
				tempVideosList = VideoLocalServiceUtil.getByInstitution(facilityId);
			}
		}else{
			if(permissionProducer){
				producerId = remoteUser.getUserId();
				portletURL.setParameter("producerId", producerId+"");
				if(lectureseriesId>0) tempVideosList = VideoLocalServiceUtil.getByProducerAndLectureseries(producerId, lectureseriesId);
				else tempVideosList = VideoLocalServiceUtil.getByProducer(producerId);
				lectureseries = LectureseriesLocalServiceUtil.getFilteredBySemesterFacultyProducer(1, "", new Long(0), producerId);
			}
		}
	}
	
%>

<aui:fieldset helpMessage="choose-filter" column="true">
		<aui:layout>
			<%if(permissionAdmin){%>
	 			<aui:column>
							<portlet:renderURL var="sortByCoordinator">
								<portlet:param name="jspPage" value="/admin/videosList.jsp" />
								<portlet:param name="producerId" value="<%=producerId.toString()%>"/>
								<portlet:param name="lectureseriesId" value="<%=lectureseriesId.toString()%>"/>
							</portlet:renderURL>
							<aui:form action="<%= sortByCoordinator.toString() %>" method="post">
								<aui:select name="coordinatorId" label="select-coordinator" onChange="submit();">
									<aui:option value="">select-coordinator</aui:option>
									<%for (int i = 0; i < coordinators.size(); i++) {
										if(coordinators.get(i).getCoordinatorId()==coordinatorId){%>
											<aui:option value='<%=coordinators.get(i).getCoordinatorId()%>' selected="true"><%=coordinators.get(i).getLastName()+", "+coordinators.get(i).getFirstName()%></aui:option>
										<%}else{%>
											<aui:option value='<%=coordinators.get(i).getCoordinatorId()%>'><%=coordinators.get(i).getLastName()+", "+coordinators.get(i).getFirstName()%></aui:option>
										<%}					
									}%>								
								</aui:select>
							</aui:form>	
					</aui:column>	
				<%}%>
				<%if(permissionCoordinator || (permissionAdmin && coordinatorId>0)){%>		
					<aui:column>
							<portlet:renderURL var="sortByProducer">
								<portlet:param name="jspPage" value="/admin/videosList.jsp" />
								<portlet:param name="coordinatorId" value="<%=coordinatorId.toString()%>"/>
								<portlet:param name="lectureseriesId" value="<%=lectureseriesId.toString()%>"/>
							</portlet:renderURL>
							<aui:form action="<%= sortByProducer.toString() %>" method="post">
								<aui:select name="producerId" label="select-producer" onChange="submit();">
									<aui:option value="">select-producer</aui:option>
									<%for (int i = 0; i < producers.size(); i++) {
										if(producers.get(i).getProducerId()==producerId){%>
											<aui:option value='<%=producers.get(i).getProducerId()%>' selected="true"><%=producers.get(i).getLastName()+", "+producers.get(i).getFirstName()%></aui:option>
										<%}else{%>
											<aui:option value='<%=producers.get(i).getProducerId()%>'><%=producers.get(i).getLastName()+", "+producers.get(i).getFirstName()%></aui:option>
										<%}					
									}%>									
								</aui:select>
							</aui:form>		
					</aui:column>
				<%}%>	
				<%if( ((permissionAdmin || permissionCoordinator) && producerId>0) || permissionProducer ){%>		
					<aui:column>
							<portlet:renderURL var="sortByLectureseries">
								<portlet:param name="jspPage" value="/admin/videosList.jsp" />
								<portlet:param name="coordinatorId" value="<%=coordinatorId.toString()%>"/>
								<portlet:param name="producerId" value="<%=producerId.toString()%>"/>
							</portlet:renderURL>
							<aui:form action="<%= sortByLectureseries.toString() %>" method="post">
								<aui:select name="lectureseriesId" label="select-lecture-series" onChange="submit();">
									<aui:option value="">select-lecture-series</aui:option>
									<%for (int i = 0; i < lectureseries.size(); i++) {
										if(lectureseries.get(i).getLectureseriesId()==lectureseriesId){%>
											<aui:option value='<%=lectureseries.get(i).getLectureseriesId()%>' selected="true"><%=lectureseries.get(i).getName()%></aui:option>
										<%}else{%>
											<aui:option value='<%=lectureseries.get(i).getLectureseriesId()%>'><%=lectureseries.get(i).getName()%></aui:option>
										<%}					
									}%>								
								</aui:select>
							</aui:form>				
					</aui:column>	
				<%}%>
		</aui:layout>
		<aui:layout>
			<%if(producerId>0){%>	
				<aui:row>
					<portlet:actionURL name="addVideo" var="addVideoURL">
						<portlet:param name="jspPage" value="/admin/editVideo.jsp" />
						<portlet:param name="lectureseriesId" value='<%=lectureseriesId+""%>'/>
						<portlet:param name="producerId" value='<%=producerId+""%>'/>
					</portlet:actionURL>				
					<aui:button value="add-new-video" onClick="<%=addVideoURL%>"/>
				</aui:row>
			<%}%>
		</aui:layout>
</aui:fieldset>

<liferay-ui:search-container emptyResultsMessage="no-videos-found" delta="10" iteratorURL="<%= portletURL %>">
	<liferay-ui:search-container-results>
		<%
			results = ListUtil.subList(tempVideosList, searchContainer.getStart(), searchContainer.getEnd());
			total = tempVideosList.size();
			pageContext.setAttribute("results", results);
			pageContext.setAttribute("total", total);
		%>
	</liferay-ui:search-container-results>

	<liferay-ui:search-container-row className="de.uhh.l2g.plugins.model.Video" keyProperty="videoId" modelVar="video">
		<portlet:actionURL name="viewVideo" var="viewURL">
			<portlet:param name="videoId" value="<%= String.valueOf(video.getVideoId())%>" />
		</portlet:actionURL>
		<liferay-ui:search-container-column-text name="name">
			<aui:a  href="<%=viewURL.toString()%>"><%=video.getTitle()%></aui:a>
		</liferay-ui:search-container-column-text>
		<liferay-ui:search-container-column-jsp path="/admin/editVideoButton.jsp"/>
	</liferay-ui:search-container-row>

	<liferay-ui:search-iterator />
</liferay-ui:search-container>