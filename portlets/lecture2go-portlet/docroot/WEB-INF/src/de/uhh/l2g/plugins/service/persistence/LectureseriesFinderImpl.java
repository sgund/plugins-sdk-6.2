package de.uhh.l2g.plugins.service.persistence;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.util.dao.orm.CustomSQLUtil;

import de.uhh.l2g.plugins.model.Lectureseries;
import de.uhh.l2g.plugins.model.Video;
import de.uhh.l2g.plugins.model.Video_Lectureseries;
import de.uhh.l2g.plugins.model.impl.LectureseriesImpl;
import de.uhh.l2g.plugins.service.VideoLocalServiceUtil;
import de.uhh.l2g.plugins.service.Video_LectureseriesLocalServiceUtil;

public class LectureseriesFinderImpl extends BasePersistenceImpl<Lectureseries> implements LectureseriesFinder {

	public static final String FIND_ALL_SEMESTERS = LectureseriesFinder.class.getName() + ".findAllSemesters";
	public static final String FIND_ALL_LECTURESERIES_WITH_OPENACCESS_VIDEOS = LectureseriesFinder.class.getName() + ".findAllLectureseriesWithOpenAccessVideos";
	public static final String FIND_ALL_LECTURESERIES_WITH_PASSWORD = LectureseriesFinder.class.getName() + ".findAllLectureseriesWithPassword";
	public static final String FIND_ALL_LECTURESERIES_FOR_VIDEO = LectureseriesFinder.class.getName() + ".findAllLectureseriesForVideo";
	
	public List<Lectureseries> findAllLectureseriesWhithPassword(){
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_ALL_LECTURESERIES_WITH_PASSWORD);
			SQLQuery q = session.createSQLQuery(sql);
			q.addScalar("number_", Type.STRING);
			q.addScalar("eventType", Type.STRING);
			q.addScalar("categoryId", Type.LONG);
			q.addScalar("name", Type.STRING);
			q.addScalar("shortDesc", Type.STRING);
			q.addScalar("termId", Type.LONG);
			q.addScalar("language", Type.STRING);
			q.addScalar("facultyName", Type.STRING);
			q.addScalar("lectureseriesId", Type.STRING);
			q.addScalar("password_", Type.STRING);
			q.addScalar("approved", Type.STRING);
			q.addScalar("longDesc", Type.STRING);
			q.addScalar("latestOpenAccessVideoId", Type.LONG);
			q.setCacheable(false);			
			@SuppressWarnings("unchecked")
			List <Object[]> ls =  (List <Object[]>) QueryUtil.list(q, getDialect(), com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS , com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS);
			return assembleLectureseries(ls);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}
		return null;		
	}
	
	public List<Lectureseries> findAllLectureseriesForVideo(Video video){
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_ALL_LECTURESERIES_FOR_VIDEO);
			SQLQuery q = session.createSQLQuery(sql);
			q.addScalar("number_", Type.STRING);
			q.addScalar("eventType", Type.STRING);
			q.addScalar("categoryId", Type.LONG);
			q.addScalar("name", Type.STRING);
			q.addScalar("shortDesc", Type.STRING);
			q.addScalar("termId", Type.LONG);
			q.addScalar("language", Type.STRING);
			q.addScalar("facultyName", Type.STRING);
			q.addScalar("lectureseriesId", Type.STRING);
			q.addScalar("password_", Type.STRING);
			q.addScalar("approved", Type.STRING);
			q.addScalar("longDesc", Type.STRING);
			q.addScalar("latestOpenAccessVideoId", Type.LONG);
			QueryPos qPos = QueryPos.getInstance(q);
			qPos.add(video.getVideoId());
			q.setCacheable(false);	
			@SuppressWarnings("unchecked")
			List <Object[]> ls =  (List <Object[]>) QueryUtil.list(q, getDialect(), com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS , com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS);
			return assembleLectureseries(ls);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}
		return null;		
	}
	
	public List<Lectureseries> findAllLectureseriesWhithOpenaccessVideos(){
		Session session = null;
		try {
			session = openSession();
			String sql = CustomSQLUtil.get(FIND_ALL_LECTURESERIES_WITH_OPENACCESS_VIDEOS);
			SQLQuery q = session.createSQLQuery(sql);
			q.addScalar("number_", Type.STRING);
			q.addScalar("eventType", Type.STRING);
			q.addScalar("categoryId", Type.LONG);
			q.addScalar("name", Type.STRING);
			q.addScalar("shortDesc", Type.STRING);
			q.addScalar("termId", Type.LONG);
			q.addScalar("language", Type.STRING);
			q.addScalar("facultyName", Type.STRING);
			q.addScalar("lectureseriesId", Type.STRING);
			q.addScalar("password_", Type.STRING);
			q.addScalar("approved", Type.STRING);
			q.addScalar("longDesc", Type.STRING);
			q.addScalar("latestOpenAccessVideoId", Type.LONG);
			q.setCacheable(false);			
			@SuppressWarnings("unchecked")
			List <Object[]> ls =  (List <Object[]>) QueryUtil.list(q, getDialect(), com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS , com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS);
			return assembleLectureseries(ls);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}
		return null;		
	}
	
	public List<Lectureseries> findLatest(int limit) {
		return findFilteredByInstitutionParentInstitutionTermCategoryCreatorSearchString(new Long(0), new Long(0), new Long(0), new Long(0), new Long(0), "", limit);
	}
	
	public List<Lectureseries> findFilteredByInstitutionParentInstitutionTermCategoryCreatorSearchString(Long institutionId, Long parentInstitutionId, Long termId, Long categoryId, Long creatorId, String searchQuery) {
		return findFilteredByInstitutionParentInstitutionTermCategoryCreatorSearchString(institutionId, parentInstitutionId, termId, categoryId, creatorId, searchQuery, 0);
	}
	/**
	 * Queries the database for data by single filter id's
	 * @param institutionId
	 * @param parentInstitutionId
	 * @param termId
	 * @param categoryId
	 * @param creatorId
	 * @return a list with lectureseries which fit to the given filters
	 */
	public List<Lectureseries> findFilteredByInstitutionParentInstitutionTermCategoryCreatorSearchString(Long institutionId, Long parentInstitutionId, Long termId, Long categoryId, Long creatorId, String searchQuery, int limit) {
		int start =com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS;
		int stop =com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS;
		if (limit>0){
			start=0;
			stop = limit;
		}
		//
		Session session = null;
		try {
			session = openSession();
			String sql = sqlFilterForOpenAccessLectureseries(institutionId, parentInstitutionId, termId, categoryId, creatorId, searchQuery);
			SQLQuery q = session.createSQLQuery(sql);
			q.addScalar("number_", Type.STRING);
			q.addScalar("eventType", Type.STRING);
			q.addScalar("categoryId", Type.LONG);
			q.addScalar("name", Type.STRING);
			q.addScalar("shortDesc", Type.STRING);
			q.addScalar("termId", Type.LONG);
			q.addScalar("language", Type.STRING);
			q.addScalar("facultyName", Type.STRING);
			q.addScalar("lectureseriesId", Type.STRING);
			q.addScalar("password_", Type.STRING);
			q.addScalar("approved", Type.STRING);
			q.addScalar("longDesc", Type.STRING);
			q.addScalar("latestOpenAccessVideoId", Type.LONG);
			//additional parameter
			q.addScalar("latestVideoUploadDate", Type.STRING);
			q.addScalar("videoCount", Type.INTEGER);		
			//
			q.setCacheable(false);
			
			/*
			 *  the filter query has a variable number of parameters and those are used in subqueries (lectureseries/single videos/ all videos if a search is used)
			 *  an array is created with the specific filter values and iterated for every subquery
			 */
			QueryPos qPos = QueryPos.getInstance(q);
			for (int i=0;i<=1;i++){ //for both of queries "lQuery" and "vQuery"
				if (termId > 0) qPos.add(termId);
				if (creatorId > 0) qPos.add(creatorId);
				if (categoryId > 0) qPos.add(categoryId);
				if (institutionId > 0) qPos.add(institutionId);
				if (parentInstitutionId > 0) qPos.add(parentInstitutionId);
				if (searchQuery.trim().length()>0) qPos.add("%" + searchQuery + "%");
			}
			
			@SuppressWarnings("unchecked")
			List <Object[]> l =  (List<Object[]>) QueryUtil.list(q, getDialect(),start , stop);
			return assembleLectureseries(l);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}
		return null;
	}
		
	private String sqlFilterForOpenAccessLectureseries(Long institutionId, Long institutionParentId, Long termId, Long categoryId, Long creatorId, String searchQuery) {
		boolean hasInstitution 			= (institutionId > 0);
		boolean hasParentInstitution	= (institutionParentId > 0);
		boolean hasTerm 				= (termId > 0);
		boolean hasCategory 			= (categoryId > 0);
		boolean hasCreator 				= (creatorId > 0);
		boolean hasSearch 				= (searchQuery.trim().length()>0);
		
		// this is an additional query only used for searching. videos which are part of a lectureseries must be searched for the searchquery but are not relevant of the normal filtering
		
		//for lecture series
		String lQuery = "SELECT l.number_, l.eventType, l.categoryId, l.name, l.shortDesc, l.termId, \"\" AS language, \"\" AS facultyName, l.lectureseriesId, NULL AS password_, 1 AS approved, l.longDesc, l. latestOpenAccessVideoId, l.latestVideoUploadDate, COUNT(l.lectureseriesId) as videoCount FROM LG_Video v ";
			   lQuery+= "JOIN LG_Lectureseries AS l ON (v.lectureseriesId = l.lectureseriesId)";
		
		//for videos
		String vQuery = "SELECT \"00.000\" AS number_, NULL AS eventType, 0 AS categoryId, v.title AS name, v.title AS shortDesc, v.termId, \"\" AS language, \"\" AS facultyName, v.videoId AS lectureseriesId, NULL AS password_, 1 AS approved, v.title AS longDesc, v.lectureseriesId AS latestOpenAccessVideoId, v.uploadDate AS latestVideoUploadDate, 1 as videoCount FROM LG_Video v ";
		
		//final query
		String query = "";

		if (hasInstitution || hasParentInstitution) {
			lQuery += "INNER JOIN LG_Lectureseries_Institution AS li ON ( l.lectureseriesId = li.lectureseriesId ) ";
			vQuery += "INNER JOIN LG_Video_Institution AS vi ON ( v.videoId = vi.videoId ) ";
		}

		if (hasTerm) {
			lQuery += "INNER JOIN LG_Term AS t ON ( l.termId = t.termId ) ";
			vQuery += "INNER JOIN LG_Term AS t ON ( v.termId = t.termId ) ";
		}
		
		if (hasCreator) {
			lQuery += "INNER JOIN LG_Lectureseries_Creator AS lc ON ( l.lectureseriesId = lc.lectureseriesId ) ";
			vQuery += "INNER JOIN LG_Video_Creator AS vc ON ( v.videoId = vc.videoId ) ";
		}
		
		if (hasCategory) {
			vQuery += "INNER JOIN LG_Video_Category AS vcat ON ( v.videoId = vcat.videoId ) ";
		}
		
		if(hasSearch){
			lQuery += "INNER JOIN LG_Tagcloud AS tag ON (v.videoId = tag.objectId)  ";
			vQuery += "INNER JOIN LG_Tagcloud AS tag ON (v.videoId = tag.objectId) ";			
		}
		
		lQuery += "WHERE v.openAccess=1 ";
		vQuery += "WHERE v.lectureseriesId<0 AND v.openAccess=1 ";
		
		if (hasTerm) {
			String termQuery = "AND t.termId = ? ";
			lQuery += termQuery;
			vQuery += termQuery;
		}
		
		if (hasCreator) {
			lQuery += "AND lc.creatorId = ? ";
			vQuery += "AND vc.creatorId = ? ";
		}
	
		if (hasCategory) {
			lQuery += "AND l.categoryId = ? ";
			vQuery += "AND vcat.categoryId = ? ";
		}

		if (hasInstitution) {
			lQuery += "AND li.institutionId = ? ";
			vQuery += "AND vi.institutionId = ? ";
		}

		if (hasParentInstitution) {
			lQuery += "AND li.institutionParentId = ? ";
			vQuery += "AND vi.institutionParentId = ? ";
		}

		if(hasSearch){
			lQuery += "AND tag.tags LIKE ? AND tag.objectClassType=\"de.uhh.l2g.plugins.model.impl.VideoImpl\" ";
			vQuery += "AND v.lectureseriesId<0 AND tag.objectClassType=\"de.uhh.l2g.plugins.model.impl.VideoImpl\" AND tag.tags LIKE ? ";			
		}
		
		lQuery += "GROUP BY v.lectureseriesId ";
		
		query = "SELECT * FROM  ( ";
		query+= lQuery;
		query+= "UNION "; 
		query+= vQuery;
		query+= ") ";
		query+= "AS a ";
		query+= "GROUP BY lectureseriesId ";
		query+= "ORDER BY a.latestVideoUploadDate DESC";	
    	 
	    return query;
	}

	public List<Lectureseries> findFilteredByApprovedSemesterFacultyProducer(Integer approved, Long termId, Long facultyId, Long producerId) {
		Session session = null;
		try {
			session = openSession();
			String sql = sqlFilterForLectureseries(approved, termId, facultyId, producerId);
			SQLQuery q = session.createSQLQuery(sql);
			q.addScalar("number_", Type.STRING);
			q.addScalar("eventType", Type.STRING);
			q.addScalar("categoryId", Type.LONG);
			q.addScalar("name", Type.STRING);
			q.addScalar("shortDesc", Type.STRING);
			q.addScalar("termId", Type.LONG);
			q.addScalar("language", Type.STRING);
			q.addScalar("facultyName", Type.STRING);
			q.addScalar("lectureseriesId", Type.STRING);
			q.addScalar("password_", Type.STRING);
			q.addScalar("approved", Type.STRING);
			q.addScalar("longDesc", Type.STRING);
			q.addScalar("latestOpenAccessVideoId", Type.LONG);
			q.setCacheable(false);
			@SuppressWarnings("unchecked")
			List <Object[]> l =  (List<Object[]>) QueryUtil.list(q, getDialect(),com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS , com.liferay.portal.kernel.dao.orm.QueryUtil.ALL_POS);
			return assembleLectureseries(l);
		} catch (Exception e) {
			try {
				throw new SystemException(e);
			} catch (SystemException se) {
				se.printStackTrace();
			}
		} finally {
			closeSession(session);
		}
		return null;
	}
	
	private List<Lectureseries> assembleLectureseries(List<Object[]> objectList){
		List<Lectureseries> ll = new ArrayList<Lectureseries>();
		for (Object[] lectser: objectList){
			LectureseriesImpl l = new LectureseriesImpl();
			l.setNumber((String) lectser[0]);
			l.setEventType((String) lectser[1]);
			l.setCategoryId((Long) lectser[2]);
			l.setName((String) lectser[3]);
			l.setShortDesc((String) lectser[4]);
			l.setTermId((Long) lectser[5]);
			l.setLanguage((String) lectser[6]);
			l.setFacultyName((String) lectser[7]);
			l.setLectureseriesId(new Long((String) lectser[8]));
			l.setPassword((String) lectser[9]);
			l.setApproved(new Integer((String) lectser[10]));
			l.setLongDesc((String) lectser[11]);
			l.setLatestOpenAccessVideoId((Long) lectser[12]);
			//additional parameter
			try{
				String string = (String)lectser[13];
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
				Date date = format.parse(string);
				l.setLatestVideoUploadDate(date);
			}catch (Exception e){}
			try{
				Integer nV = Video_LectureseriesLocalServiceUtil.getNumberOfVideosByLectureseries(l.getLectureseriesId());
				Integer nOAV = Video_LectureseriesLocalServiceUtil.getNumberOfVideosByLectureseriesAndOpenAccess(l.getLectureseriesId(), 1);
				
				l.setNumberOfVideos(nV);
				l.setNumberOfOpenAccessVideos(nOAV);
			}catch (Exception e){}
			// 
			ll.add(l);
		}
		return ll;
	}
	
	private String sqlFilterForLectureseries(Integer approved, Long termId, Long facultyId, Long producerId) {
		// build query
		String query = "SELECT c.number_, c.eventType, c.categoryId, c.name, c.shortDesc, c.longDesc, c.termId, c.language, c.facultyName, c.lectureseriesId, c.password_, c.approved, c.longDesc, c.latestOpenAccessVideoId ";
			   query += "FROM LG_Lectureseries AS c ";

		if (facultyId > 0) {
			query += "INNER JOIN LG_Lectureseries_Institution AS ce ON ( c.lectureseriesId = ce.lectureseriesId ) ";
			query += "INNER JOIN LG_Institution AS e ON ( ce.institutionId = e.institutionId ) ";
		}

		if (producerId > 0) {
			query += "INNER JOIN LG_Producer_Lectureseries AS pc ON ( c.lectureseriesId = pc.lectureseriesId ) ";
			query += "INNER JOIN LG_Producer AS p ON ( pc.producerId = p.producerId ) ";
		}

		if (termId>0 || (approved==1 || approved==0) || facultyId > 0 || producerId > 0) {
			query += "WHERE ";
			int i = 0;
			if (termId > 0) {
				query += "c.termId = \""+termId + "\" ";
				i++;
			}

			if (approved==1 || approved==0) {
				query += i > 0 ? "AND " : "";
				query += "c.approved = "+approved + " ";
				i++;
			}

			if (facultyId > 0) {
				query += i > 0 ? "AND " : "";
				query += "ce.institutionId IN ";
				query += "(select institutionId from LG_Institution AS ein WHERE ein.parentId = "+facultyId+" OR ein.institutionId = "+facultyId+") ";
				i++;
			}

			if (producerId > 0) {
				query += i > 0 ? "AND " : "";
				query += "pc.producerId = "+producerId + " ";
				i++;
			}
		}
		query += "GROUP BY c.lectureseriesId ";
		query += "ORDER BY c.name ASC ";

		return query;
	}
	
}
