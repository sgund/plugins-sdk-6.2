<%!com.liferay.portal.kernel.dao.search.SearchContainer<User> searchContainer = null;%>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.Collections"%>
<%@ page import="java.util.Locale"%>
<%@ page import="java.util.LinkedHashMap"%>
<%@ page import="java.lang.String"%>

<%@ page import="javax.portlet.PortletURL"%>

<%@ page import="com.liferay.portal.kernel.util.HtmlUtil"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="com.liferay.portal.kernel.util.CalendarFactoryUtil"%>
<%@ page import="com.liferay.portal.kernel.dao.search.ResultRow"%>
<%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry"%>
<%@ page import="com.liferay.portal.kernel.exception.SystemException"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="com.liferay.portal.kernel.util.ListUtil"%>
<%@ page import="com.liferay.portal.model.Portlet"%>
<%@ page import="com.liferay.portal.model.User"%>
<%@ page import="com.liferay.portal.model.Role"%>
<%@ page import="com.liferay.portal.security.permission.ActionKeys"%>
<%@ page import="com.liferay.portal.service.permission.PortletPermissionUtil"%>
<%@ page import="com.liferay.portal.service.UserLocalServiceUtil"%>
<%@ page import="com.liferay.portal.service.UserGroupRoleLocalServiceUtil"%>
<%@ page import="com.liferay.portal.service.UserGroupRoleServiceUtil"%>
<%@ page import="com.liferay.portal.service.RoleLocalServiceUtil"%>
<%@ page import="com.liferay.portal.service.PortletLocalServiceUtil"%>
<%@ page import="com.liferay.portal.kernel.util.UnicodeFormatter"%>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@ page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@ page import="com.liferay.taglib.portlet.RenderURLParamsTag"%>
<%@ page import="com.liferay.util.portlet.PortletRequestUtil"%>

<%@ page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page import="org.springframework.scripting.config.LangNamespaceUtils"%>
<%@ page import="org.springframework.web.bind.ServletRequestUtils"%>
<%@ page import="org.springframework.web.portlet.bind.PortletRequestUtils"%>

<%@ page import="de.uhh.l2g.plugins.model.Producer"%>
<%@ page import="de.uhh.l2g.plugins.model.Facility"%>
<%@ page import="de.uhh.l2g.plugins.model.Lectureseries"%>
<%@ page import="de.uhh.l2g.plugins.admin.AdminUserManagement"%>
<%@ page import="de.uhh.l2g.plugins.model.Producer_Lectureseries"%>
<%@ page import="de.uhh.l2g.plugins.service.ProducerLocalServiceUtil"%>
<%@ page import="de.uhh.l2g.plugins.service.FacilityLocalServiceUtil"%>
<%@ page import="de.uhh.l2g.plugins.service.CoordinatorLocalServiceUtil"%>
<%@ page import="de.uhh.l2g.plugins.service.LectureseriesLocalServiceUtil"%>
<%@ page import="de.uhh.l2g.plugins.service.Producer_LectureseriesLocalServiceUtil"%>
<%@ page import="de.uhh.l2g.plugins.service.Lectureseries_FacilityLocalServiceUtil"%>
<%@ page import="de.uhh.l2g.plugins.model.Producer"%>
<%@page import="de.uhh.l2g.plugins.service.VideoLocalServiceUtil"%>
<%@page import="de.uhh.l2g.plugins.model.Video"%>

<portlet:defineObjects />
<liferay-theme:defineObjects/>

<%
//check lecture2go user permissions
User remoteUser = UserLocalServiceUtil.getUser(new Long (request.getRemoteUser()));
//l2go administrator is logged in
boolean permissionAdmin = permissionChecker.hasPermission(remoteUser.getGroupId(), User.class.getName(), remoteUser.getPrimaryKey(), "ADD_L2GOADMIN");
//l2go coordinator is logged in
boolean permissionCoordinator = permissionChecker.hasPermission(remoteUser.getGroupId(), User.class.getName(), remoteUser.getPrimaryKey(), "ADD_L2GOPRODUCER");
%>