/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package de.uhh.l2g.plugins.service.persistence;

import com.liferay.portal.kernel.cache.CacheRegistryUtil;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.InstanceFactory;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnmodifiableList;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import de.uhh.l2g.plugins.NoSuchLectureseriesException;
import de.uhh.l2g.plugins.model.Lectureseries;
import de.uhh.l2g.plugins.model.impl.LectureseriesImpl;
import de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * The persistence implementation for the lectureseries service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Iavor Sturm
 * @see LectureseriesPersistence
 * @see LectureseriesUtil
 * @generated
 */
public class LectureseriesPersistenceImpl extends BasePersistenceImpl<Lectureseries>
	implements LectureseriesPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link LectureseriesUtil} to access the lectureseries persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = LectureseriesImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_SEMESTER = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findBySemester",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SEMESTER =
		new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findBySemester", new String[] { String.class.getName() },
			LectureseriesModelImpl.SEMESTERNAME_COLUMN_BITMASK |
			LectureseriesModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_SEMESTER = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countBySemester",
			new String[] { String.class.getName() });

	/**
	 * Returns all the lectureserieses where semesterName = &#63;.
	 *
	 * @param semesterName the semester name
	 * @return the matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findBySemester(String semesterName)
		throws SystemException {
		return findBySemester(semesterName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lectureserieses where semesterName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param semesterName the semester name
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @return the range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findBySemester(String semesterName, int start,
		int end) throws SystemException {
		return findBySemester(semesterName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lectureserieses where semesterName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param semesterName the semester name
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findBySemester(String semesterName, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SEMESTER;
			finderArgs = new Object[] { semesterName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_SEMESTER;
			finderArgs = new Object[] {
					semesterName,
					
					start, end, orderByComparator
				};
		}

		List<Lectureseries> list = (List<Lectureseries>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Lectureseries lectureseries : list) {
				if (!Validator.equals(semesterName,
							lectureseries.getSemesterName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LECTURESERIES_WHERE);

			boolean bindSemesterName = false;

			if (semesterName == null) {
				query.append(_FINDER_COLUMN_SEMESTER_SEMESTERNAME_1);
			}
			else if (semesterName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SEMESTER_SEMESTERNAME_3);
			}
			else {
				bindSemesterName = true;

				query.append(_FINDER_COLUMN_SEMESTER_SEMESTERNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSemesterName) {
					qPos.add(semesterName);
				}

				if (!pagination) {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Lectureseries>(list);
				}
				else {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first lectureseries in the ordered set where semesterName = &#63;.
	 *
	 * @param semesterName the semester name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findBySemester_First(String semesterName,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchBySemester_First(semesterName,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("semesterName=");
		msg.append(semesterName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the first lectureseries in the ordered set where semesterName = &#63;.
	 *
	 * @param semesterName the semester name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchBySemester_First(String semesterName,
		OrderByComparator orderByComparator) throws SystemException {
		List<Lectureseries> list = findBySemester(semesterName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lectureseries in the ordered set where semesterName = &#63;.
	 *
	 * @param semesterName the semester name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findBySemester_Last(String semesterName,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchBySemester_Last(semesterName,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("semesterName=");
		msg.append(semesterName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the last lectureseries in the ordered set where semesterName = &#63;.
	 *
	 * @param semesterName the semester name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchBySemester_Last(String semesterName,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySemester(semesterName);

		if (count == 0) {
			return null;
		}

		List<Lectureseries> list = findBySemester(semesterName, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lectureserieses before and after the current lectureseries in the ordered set where semesterName = &#63;.
	 *
	 * @param lectureseriesId the primary key of the current lectureseries
	 * @param semesterName the semester name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries[] findBySemester_PrevAndNext(long lectureseriesId,
		String semesterName, OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = findByPrimaryKey(lectureseriesId);

		Session session = null;

		try {
			session = openSession();

			Lectureseries[] array = new LectureseriesImpl[3];

			array[0] = getBySemester_PrevAndNext(session, lectureseries,
					semesterName, orderByComparator, true);

			array[1] = lectureseries;

			array[2] = getBySemester_PrevAndNext(session, lectureseries,
					semesterName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Lectureseries getBySemester_PrevAndNext(Session session,
		Lectureseries lectureseries, String semesterName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LECTURESERIES_WHERE);

		boolean bindSemesterName = false;

		if (semesterName == null) {
			query.append(_FINDER_COLUMN_SEMESTER_SEMESTERNAME_1);
		}
		else if (semesterName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_SEMESTER_SEMESTERNAME_3);
		}
		else {
			bindSemesterName = true;

			query.append(_FINDER_COLUMN_SEMESTER_SEMESTERNAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindSemesterName) {
			qPos.add(semesterName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lectureseries);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Lectureseries> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lectureserieses where semesterName = &#63; from the database.
	 *
	 * @param semesterName the semester name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeBySemester(String semesterName) throws SystemException {
		for (Lectureseries lectureseries : findBySemester(semesterName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lectureseries);
		}
	}

	/**
	 * Returns the number of lectureserieses where semesterName = &#63;.
	 *
	 * @param semesterName the semester name
	 * @return the number of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countBySemester(String semesterName) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_SEMESTER;

		Object[] finderArgs = new Object[] { semesterName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LECTURESERIES_WHERE);

			boolean bindSemesterName = false;

			if (semesterName == null) {
				query.append(_FINDER_COLUMN_SEMESTER_SEMESTERNAME_1);
			}
			else if (semesterName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SEMESTER_SEMESTERNAME_3);
			}
			else {
				bindSemesterName = true;

				query.append(_FINDER_COLUMN_SEMESTER_SEMESTERNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindSemesterName) {
					qPos.add(semesterName);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_SEMESTER_SEMESTERNAME_1 = "lectureseries.semesterName IS NULL";
	private static final String _FINDER_COLUMN_SEMESTER_SEMESTERNAME_2 = "lectureseries.semesterName = ?";
	private static final String _FINDER_COLUMN_SEMESTER_SEMESTERNAME_3 = "(lectureseries.semesterName IS NULL OR lectureseries.semesterName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_LANGUAGE = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByLanguage",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LANGUAGE =
		new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByLanguage", new String[] { String.class.getName() },
			LectureseriesModelImpl.LANGUAGE_COLUMN_BITMASK |
			LectureseriesModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_LANGUAGE = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByLanguage",
			new String[] { String.class.getName() });

	/**
	 * Returns all the lectureserieses where language = &#63;.
	 *
	 * @param language the language
	 * @return the matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByLanguage(String language)
		throws SystemException {
		return findByLanguage(language, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the lectureserieses where language = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param language the language
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @return the range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByLanguage(String language, int start,
		int end) throws SystemException {
		return findByLanguage(language, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lectureserieses where language = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param language the language
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByLanguage(String language, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LANGUAGE;
			finderArgs = new Object[] { language };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_LANGUAGE;
			finderArgs = new Object[] { language, start, end, orderByComparator };
		}

		List<Lectureseries> list = (List<Lectureseries>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Lectureseries lectureseries : list) {
				if (!Validator.equals(language, lectureseries.getLanguage())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LECTURESERIES_WHERE);

			boolean bindLanguage = false;

			if (language == null) {
				query.append(_FINDER_COLUMN_LANGUAGE_LANGUAGE_1);
			}
			else if (language.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LANGUAGE_LANGUAGE_3);
			}
			else {
				bindLanguage = true;

				query.append(_FINDER_COLUMN_LANGUAGE_LANGUAGE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLanguage) {
					qPos.add(language);
				}

				if (!pagination) {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Lectureseries>(list);
				}
				else {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first lectureseries in the ordered set where language = &#63;.
	 *
	 * @param language the language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByLanguage_First(String language,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByLanguage_First(language,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("language=");
		msg.append(language);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the first lectureseries in the ordered set where language = &#63;.
	 *
	 * @param language the language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByLanguage_First(String language,
		OrderByComparator orderByComparator) throws SystemException {
		List<Lectureseries> list = findByLanguage(language, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lectureseries in the ordered set where language = &#63;.
	 *
	 * @param language the language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByLanguage_Last(String language,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByLanguage_Last(language,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("language=");
		msg.append(language);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the last lectureseries in the ordered set where language = &#63;.
	 *
	 * @param language the language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByLanguage_Last(String language,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByLanguage(language);

		if (count == 0) {
			return null;
		}

		List<Lectureseries> list = findByLanguage(language, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lectureserieses before and after the current lectureseries in the ordered set where language = &#63;.
	 *
	 * @param lectureseriesId the primary key of the current lectureseries
	 * @param language the language
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries[] findByLanguage_PrevAndNext(long lectureseriesId,
		String language, OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = findByPrimaryKey(lectureseriesId);

		Session session = null;

		try {
			session = openSession();

			Lectureseries[] array = new LectureseriesImpl[3];

			array[0] = getByLanguage_PrevAndNext(session, lectureseries,
					language, orderByComparator, true);

			array[1] = lectureseries;

			array[2] = getByLanguage_PrevAndNext(session, lectureseries,
					language, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Lectureseries getByLanguage_PrevAndNext(Session session,
		Lectureseries lectureseries, String language,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LECTURESERIES_WHERE);

		boolean bindLanguage = false;

		if (language == null) {
			query.append(_FINDER_COLUMN_LANGUAGE_LANGUAGE_1);
		}
		else if (language.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_LANGUAGE_LANGUAGE_3);
		}
		else {
			bindLanguage = true;

			query.append(_FINDER_COLUMN_LANGUAGE_LANGUAGE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindLanguage) {
			qPos.add(language);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lectureseries);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Lectureseries> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lectureserieses where language = &#63; from the database.
	 *
	 * @param language the language
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByLanguage(String language) throws SystemException {
		for (Lectureseries lectureseries : findByLanguage(language,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lectureseries);
		}
	}

	/**
	 * Returns the number of lectureserieses where language = &#63;.
	 *
	 * @param language the language
	 * @return the number of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByLanguage(String language) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_LANGUAGE;

		Object[] finderArgs = new Object[] { language };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LECTURESERIES_WHERE);

			boolean bindLanguage = false;

			if (language == null) {
				query.append(_FINDER_COLUMN_LANGUAGE_LANGUAGE_1);
			}
			else if (language.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LANGUAGE_LANGUAGE_3);
			}
			else {
				bindLanguage = true;

				query.append(_FINDER_COLUMN_LANGUAGE_LANGUAGE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindLanguage) {
					qPos.add(language);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_LANGUAGE_LANGUAGE_1 = "lectureseries.language IS NULL";
	private static final String _FINDER_COLUMN_LANGUAGE_LANGUAGE_2 = "lectureseries.language = ?";
	private static final String _FINDER_COLUMN_LANGUAGE_LANGUAGE_3 = "(lectureseries.language IS NULL OR lectureseries.language = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NUMBER = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByNumber",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NUMBER =
		new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByNumber", new String[] { String.class.getName() },
			LectureseriesModelImpl.NUMBER_COLUMN_BITMASK |
			LectureseriesModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NUMBER = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByNumber",
			new String[] { String.class.getName() });

	/**
	 * Returns all the lectureserieses where number = &#63;.
	 *
	 * @param number the number
	 * @return the matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByNumber(String number)
		throws SystemException {
		return findByNumber(number, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lectureserieses where number = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param number the number
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @return the range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByNumber(String number, int start, int end)
		throws SystemException {
		return findByNumber(number, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lectureserieses where number = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param number the number
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByNumber(String number, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NUMBER;
			finderArgs = new Object[] { number };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NUMBER;
			finderArgs = new Object[] { number, start, end, orderByComparator };
		}

		List<Lectureseries> list = (List<Lectureseries>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Lectureseries lectureseries : list) {
				if (!Validator.equals(number, lectureseries.getNumber())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LECTURESERIES_WHERE);

			boolean bindNumber = false;

			if (number == null) {
				query.append(_FINDER_COLUMN_NUMBER_NUMBER_1);
			}
			else if (number.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NUMBER_NUMBER_3);
			}
			else {
				bindNumber = true;

				query.append(_FINDER_COLUMN_NUMBER_NUMBER_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindNumber) {
					qPos.add(number);
				}

				if (!pagination) {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Lectureseries>(list);
				}
				else {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first lectureseries in the ordered set where number = &#63;.
	 *
	 * @param number the number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByNumber_First(String number,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByNumber_First(number,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("number=");
		msg.append(number);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the first lectureseries in the ordered set where number = &#63;.
	 *
	 * @param number the number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByNumber_First(String number,
		OrderByComparator orderByComparator) throws SystemException {
		List<Lectureseries> list = findByNumber(number, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lectureseries in the ordered set where number = &#63;.
	 *
	 * @param number the number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByNumber_Last(String number,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByNumber_Last(number,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("number=");
		msg.append(number);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the last lectureseries in the ordered set where number = &#63;.
	 *
	 * @param number the number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByNumber_Last(String number,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByNumber(number);

		if (count == 0) {
			return null;
		}

		List<Lectureseries> list = findByNumber(number, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lectureserieses before and after the current lectureseries in the ordered set where number = &#63;.
	 *
	 * @param lectureseriesId the primary key of the current lectureseries
	 * @param number the number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries[] findByNumber_PrevAndNext(long lectureseriesId,
		String number, OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = findByPrimaryKey(lectureseriesId);

		Session session = null;

		try {
			session = openSession();

			Lectureseries[] array = new LectureseriesImpl[3];

			array[0] = getByNumber_PrevAndNext(session, lectureseries, number,
					orderByComparator, true);

			array[1] = lectureseries;

			array[2] = getByNumber_PrevAndNext(session, lectureseries, number,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Lectureseries getByNumber_PrevAndNext(Session session,
		Lectureseries lectureseries, String number,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LECTURESERIES_WHERE);

		boolean bindNumber = false;

		if (number == null) {
			query.append(_FINDER_COLUMN_NUMBER_NUMBER_1);
		}
		else if (number.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_NUMBER_NUMBER_3);
		}
		else {
			bindNumber = true;

			query.append(_FINDER_COLUMN_NUMBER_NUMBER_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindNumber) {
			qPos.add(number);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lectureseries);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Lectureseries> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lectureserieses where number = &#63; from the database.
	 *
	 * @param number the number
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByNumber(String number) throws SystemException {
		for (Lectureseries lectureseries : findByNumber(number,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lectureseries);
		}
	}

	/**
	 * Returns the number of lectureserieses where number = &#63;.
	 *
	 * @param number the number
	 * @return the number of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByNumber(String number) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NUMBER;

		Object[] finderArgs = new Object[] { number };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LECTURESERIES_WHERE);

			boolean bindNumber = false;

			if (number == null) {
				query.append(_FINDER_COLUMN_NUMBER_NUMBER_1);
			}
			else if (number.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NUMBER_NUMBER_3);
			}
			else {
				bindNumber = true;

				query.append(_FINDER_COLUMN_NUMBER_NUMBER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindNumber) {
					qPos.add(number);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_NUMBER_NUMBER_1 = "lectureseries.number IS NULL";
	private static final String _FINDER_COLUMN_NUMBER_NUMBER_2 = "lectureseries.number = ?";
	private static final String _FINDER_COLUMN_NUMBER_NUMBER_3 = "(lectureseries.number IS NULL OR lectureseries.number = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByType",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByType", new String[] { String.class.getName() },
			LectureseriesModelImpl.EVENTTYPE_COLUMN_BITMASK |
			LectureseriesModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_TYPE = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByType",
			new String[] { String.class.getName() });

	/**
	 * Returns all the lectureserieses where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @return the matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByType(String eventType)
		throws SystemException {
		return findByType(eventType, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lectureserieses where eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventType the event type
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @return the range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByType(String eventType, int start, int end)
		throws SystemException {
		return findByType(eventType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lectureserieses where eventType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventType the event type
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByType(String eventType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { eventType };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_TYPE;
			finderArgs = new Object[] { eventType, start, end, orderByComparator };
		}

		List<Lectureseries> list = (List<Lectureseries>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Lectureseries lectureseries : list) {
				if (!Validator.equals(eventType, lectureseries.getEventType())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LECTURESERIES_WHERE);

			boolean bindEventType = false;

			if (eventType == null) {
				query.append(_FINDER_COLUMN_TYPE_EVENTTYPE_1);
			}
			else if (eventType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TYPE_EVENTTYPE_3);
			}
			else {
				bindEventType = true;

				query.append(_FINDER_COLUMN_TYPE_EVENTTYPE_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEventType) {
					qPos.add(eventType.toLowerCase());
				}

				if (!pagination) {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Lectureseries>(list);
				}
				else {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first lectureseries in the ordered set where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByType_First(String eventType,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByType_First(eventType,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventType=");
		msg.append(eventType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the first lectureseries in the ordered set where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByType_First(String eventType,
		OrderByComparator orderByComparator) throws SystemException {
		List<Lectureseries> list = findByType(eventType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lectureseries in the ordered set where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByType_Last(String eventType,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByType_Last(eventType,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventType=");
		msg.append(eventType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the last lectureseries in the ordered set where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByType_Last(String eventType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByType(eventType);

		if (count == 0) {
			return null;
		}

		List<Lectureseries> list = findByType(eventType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lectureserieses before and after the current lectureseries in the ordered set where eventType = &#63;.
	 *
	 * @param lectureseriesId the primary key of the current lectureseries
	 * @param eventType the event type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries[] findByType_PrevAndNext(long lectureseriesId,
		String eventType, OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = findByPrimaryKey(lectureseriesId);

		Session session = null;

		try {
			session = openSession();

			Lectureseries[] array = new LectureseriesImpl[3];

			array[0] = getByType_PrevAndNext(session, lectureseries, eventType,
					orderByComparator, true);

			array[1] = lectureseries;

			array[2] = getByType_PrevAndNext(session, lectureseries, eventType,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Lectureseries getByType_PrevAndNext(Session session,
		Lectureseries lectureseries, String eventType,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LECTURESERIES_WHERE);

		boolean bindEventType = false;

		if (eventType == null) {
			query.append(_FINDER_COLUMN_TYPE_EVENTTYPE_1);
		}
		else if (eventType.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_TYPE_EVENTTYPE_3);
		}
		else {
			bindEventType = true;

			query.append(_FINDER_COLUMN_TYPE_EVENTTYPE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindEventType) {
			qPos.add(eventType.toLowerCase());
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lectureseries);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Lectureseries> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lectureserieses where eventType = &#63; from the database.
	 *
	 * @param eventType the event type
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByType(String eventType) throws SystemException {
		for (Lectureseries lectureseries : findByType(eventType,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lectureseries);
		}
	}

	/**
	 * Returns the number of lectureserieses where eventType = &#63;.
	 *
	 * @param eventType the event type
	 * @return the number of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByType(String eventType) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_TYPE;

		Object[] finderArgs = new Object[] { eventType };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LECTURESERIES_WHERE);

			boolean bindEventType = false;

			if (eventType == null) {
				query.append(_FINDER_COLUMN_TYPE_EVENTTYPE_1);
			}
			else if (eventType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TYPE_EVENTTYPE_3);
			}
			else {
				bindEventType = true;

				query.append(_FINDER_COLUMN_TYPE_EVENTTYPE_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEventType) {
					qPos.add(eventType.toLowerCase());
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_TYPE_EVENTTYPE_1 = "lectureseries.eventType IS NULL";
	private static final String _FINDER_COLUMN_TYPE_EVENTTYPE_2 = "lower(lectureseries.eventType) = ?";
	private static final String _FINDER_COLUMN_TYPE_EVENTTYPE_3 = "(lectureseries.eventType IS NULL OR lectureseries.eventType = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_CATEGORY = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByCategory",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORY =
		new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCategory", new String[] { String.class.getName() },
			LectureseriesModelImpl.EVENTCATEGORY_COLUMN_BITMASK |
			LectureseriesModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_CATEGORY = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCategory",
			new String[] { String.class.getName() });

	/**
	 * Returns all the lectureserieses where eventCategory = &#63;.
	 *
	 * @param eventCategory the event category
	 * @return the matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByCategory(String eventCategory)
		throws SystemException {
		return findByCategory(eventCategory, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lectureserieses where eventCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventCategory the event category
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @return the range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByCategory(String eventCategory, int start,
		int end) throws SystemException {
		return findByCategory(eventCategory, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lectureserieses where eventCategory = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param eventCategory the event category
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByCategory(String eventCategory, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORY;
			finderArgs = new Object[] { eventCategory };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_CATEGORY;
			finderArgs = new Object[] {
					eventCategory,
					
					start, end, orderByComparator
				};
		}

		List<Lectureseries> list = (List<Lectureseries>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Lectureseries lectureseries : list) {
				if (!Validator.equals(eventCategory,
							lectureseries.getEventCategory())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LECTURESERIES_WHERE);

			boolean bindEventCategory = false;

			if (eventCategory == null) {
				query.append(_FINDER_COLUMN_CATEGORY_EVENTCATEGORY_1);
			}
			else if (eventCategory.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CATEGORY_EVENTCATEGORY_3);
			}
			else {
				bindEventCategory = true;

				query.append(_FINDER_COLUMN_CATEGORY_EVENTCATEGORY_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEventCategory) {
					qPos.add(eventCategory);
				}

				if (!pagination) {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Lectureseries>(list);
				}
				else {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first lectureseries in the ordered set where eventCategory = &#63;.
	 *
	 * @param eventCategory the event category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByCategory_First(String eventCategory,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByCategory_First(eventCategory,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventCategory=");
		msg.append(eventCategory);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the first lectureseries in the ordered set where eventCategory = &#63;.
	 *
	 * @param eventCategory the event category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByCategory_First(String eventCategory,
		OrderByComparator orderByComparator) throws SystemException {
		List<Lectureseries> list = findByCategory(eventCategory, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lectureseries in the ordered set where eventCategory = &#63;.
	 *
	 * @param eventCategory the event category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByCategory_Last(String eventCategory,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByCategory_Last(eventCategory,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("eventCategory=");
		msg.append(eventCategory);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the last lectureseries in the ordered set where eventCategory = &#63;.
	 *
	 * @param eventCategory the event category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByCategory_Last(String eventCategory,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCategory(eventCategory);

		if (count == 0) {
			return null;
		}

		List<Lectureseries> list = findByCategory(eventCategory, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lectureserieses before and after the current lectureseries in the ordered set where eventCategory = &#63;.
	 *
	 * @param lectureseriesId the primary key of the current lectureseries
	 * @param eventCategory the event category
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries[] findByCategory_PrevAndNext(long lectureseriesId,
		String eventCategory, OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = findByPrimaryKey(lectureseriesId);

		Session session = null;

		try {
			session = openSession();

			Lectureseries[] array = new LectureseriesImpl[3];

			array[0] = getByCategory_PrevAndNext(session, lectureseries,
					eventCategory, orderByComparator, true);

			array[1] = lectureseries;

			array[2] = getByCategory_PrevAndNext(session, lectureseries,
					eventCategory, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Lectureseries getByCategory_PrevAndNext(Session session,
		Lectureseries lectureseries, String eventCategory,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LECTURESERIES_WHERE);

		boolean bindEventCategory = false;

		if (eventCategory == null) {
			query.append(_FINDER_COLUMN_CATEGORY_EVENTCATEGORY_1);
		}
		else if (eventCategory.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_CATEGORY_EVENTCATEGORY_3);
		}
		else {
			bindEventCategory = true;

			query.append(_FINDER_COLUMN_CATEGORY_EVENTCATEGORY_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindEventCategory) {
			qPos.add(eventCategory);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lectureseries);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Lectureseries> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lectureserieses where eventCategory = &#63; from the database.
	 *
	 * @param eventCategory the event category
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByCategory(String eventCategory)
		throws SystemException {
		for (Lectureseries lectureseries : findByCategory(eventCategory,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lectureseries);
		}
	}

	/**
	 * Returns the number of lectureserieses where eventCategory = &#63;.
	 *
	 * @param eventCategory the event category
	 * @return the number of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByCategory(String eventCategory) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_CATEGORY;

		Object[] finderArgs = new Object[] { eventCategory };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LECTURESERIES_WHERE);

			boolean bindEventCategory = false;

			if (eventCategory == null) {
				query.append(_FINDER_COLUMN_CATEGORY_EVENTCATEGORY_1);
			}
			else if (eventCategory.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CATEGORY_EVENTCATEGORY_3);
			}
			else {
				bindEventCategory = true;

				query.append(_FINDER_COLUMN_CATEGORY_EVENTCATEGORY_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindEventCategory) {
					qPos.add(eventCategory);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CATEGORY_EVENTCATEGORY_1 = "lectureseries.eventCategory IS NULL";
	private static final String _FINDER_COLUMN_CATEGORY_EVENTCATEGORY_2 = "lectureseries.eventCategory = ?";
	private static final String _FINDER_COLUMN_CATEGORY_EVENTCATEGORY_3 = "(lectureseries.eventCategory IS NULL OR lectureseries.eventCategory = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByName",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByName", new String[] { String.class.getName() },
			LectureseriesModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_NAME = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByName",
			new String[] { String.class.getName() });

	/**
	 * Returns all the lectureserieses where name = &#63;.
	 *
	 * @param name the name
	 * @return the matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByName(String name)
		throws SystemException {
		return findByName(name, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lectureserieses where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @return the range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByName(String name, int start, int end)
		throws SystemException {
		return findByName(name, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lectureserieses where name = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param name the name
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByName(String name, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_NAME;
			finderArgs = new Object[] { name, start, end, orderByComparator };
		}

		List<Lectureseries> list = (List<Lectureseries>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Lectureseries lectureseries : list) {
				if (!Validator.equals(name, lectureseries.getName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LECTURESERIES_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				if (!pagination) {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Lectureseries>(list);
				}
				else {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first lectureseries in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByName_First(String name,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByName_First(name, orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the first lectureseries in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByName_First(String name,
		OrderByComparator orderByComparator) throws SystemException {
		List<Lectureseries> list = findByName(name, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lectureseries in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByName_Last(String name,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByName_Last(name, orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("name=");
		msg.append(name);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the last lectureseries in the ordered set where name = &#63;.
	 *
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByName_Last(String name,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByName(name);

		if (count == 0) {
			return null;
		}

		List<Lectureseries> list = findByName(name, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lectureserieses before and after the current lectureseries in the ordered set where name = &#63;.
	 *
	 * @param lectureseriesId the primary key of the current lectureseries
	 * @param name the name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries[] findByName_PrevAndNext(long lectureseriesId,
		String name, OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = findByPrimaryKey(lectureseriesId);

		Session session = null;

		try {
			session = openSession();

			Lectureseries[] array = new LectureseriesImpl[3];

			array[0] = getByName_PrevAndNext(session, lectureseries, name,
					orderByComparator, true);

			array[1] = lectureseries;

			array[2] = getByName_PrevAndNext(session, lectureseries, name,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Lectureseries getByName_PrevAndNext(Session session,
		Lectureseries lectureseries, String name,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LECTURESERIES_WHERE);

		boolean bindName = false;

		if (name == null) {
			query.append(_FINDER_COLUMN_NAME_NAME_1);
		}
		else if (name.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_NAME_NAME_3);
		}
		else {
			bindName = true;

			query.append(_FINDER_COLUMN_NAME_NAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindName) {
			qPos.add(name);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lectureseries);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Lectureseries> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lectureserieses where name = &#63; from the database.
	 *
	 * @param name the name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByName(String name) throws SystemException {
		for (Lectureseries lectureseries : findByName(name, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null)) {
			remove(lectureseries);
		}
	}

	/**
	 * Returns the number of lectureserieses where name = &#63;.
	 *
	 * @param name the name
	 * @return the number of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByName(String name) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_NAME;

		Object[] finderArgs = new Object[] { name };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LECTURESERIES_WHERE);

			boolean bindName = false;

			if (name == null) {
				query.append(_FINDER_COLUMN_NAME_NAME_1);
			}
			else if (name.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_NAME_NAME_3);
			}
			else {
				bindName = true;

				query.append(_FINDER_COLUMN_NAME_NAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindName) {
					qPos.add(name);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_NAME_NAME_1 = "lectureseries.name IS NULL";
	private static final String _FINDER_COLUMN_NAME_NAME_2 = "lectureseries.name = ?";
	private static final String _FINDER_COLUMN_NAME_NAME_3 = "(lectureseries.name IS NULL OR lectureseries.name = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_FACULTY = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByFaculty",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FACULTY =
		new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByFaculty", new String[] { String.class.getName() },
			LectureseriesModelImpl.FACULTYNAME_COLUMN_BITMASK |
			LectureseriesModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_FACULTY = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByFaculty",
			new String[] { String.class.getName() });

	/**
	 * Returns all the lectureserieses where facultyName = &#63;.
	 *
	 * @param facultyName the faculty name
	 * @return the matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByFaculty(String facultyName)
		throws SystemException {
		return findByFaculty(facultyName, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the lectureserieses where facultyName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param facultyName the faculty name
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @return the range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByFaculty(String facultyName, int start,
		int end) throws SystemException {
		return findByFaculty(facultyName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lectureserieses where facultyName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param facultyName the faculty name
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByFaculty(String facultyName, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FACULTY;
			finderArgs = new Object[] { facultyName };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_FACULTY;
			finderArgs = new Object[] { facultyName, start, end, orderByComparator };
		}

		List<Lectureseries> list = (List<Lectureseries>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Lectureseries lectureseries : list) {
				if (!Validator.equals(facultyName,
							lectureseries.getFacultyName())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LECTURESERIES_WHERE);

			boolean bindFacultyName = false;

			if (facultyName == null) {
				query.append(_FINDER_COLUMN_FACULTY_FACULTYNAME_1);
			}
			else if (facultyName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_FACULTY_FACULTYNAME_3);
			}
			else {
				bindFacultyName = true;

				query.append(_FINDER_COLUMN_FACULTY_FACULTYNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindFacultyName) {
					qPos.add(facultyName);
				}

				if (!pagination) {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Lectureseries>(list);
				}
				else {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first lectureseries in the ordered set where facultyName = &#63;.
	 *
	 * @param facultyName the faculty name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByFaculty_First(String facultyName,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByFaculty_First(facultyName,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("facultyName=");
		msg.append(facultyName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the first lectureseries in the ordered set where facultyName = &#63;.
	 *
	 * @param facultyName the faculty name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByFaculty_First(String facultyName,
		OrderByComparator orderByComparator) throws SystemException {
		List<Lectureseries> list = findByFaculty(facultyName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lectureseries in the ordered set where facultyName = &#63;.
	 *
	 * @param facultyName the faculty name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByFaculty_Last(String facultyName,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByFaculty_Last(facultyName,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("facultyName=");
		msg.append(facultyName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the last lectureseries in the ordered set where facultyName = &#63;.
	 *
	 * @param facultyName the faculty name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByFaculty_Last(String facultyName,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByFaculty(facultyName);

		if (count == 0) {
			return null;
		}

		List<Lectureseries> list = findByFaculty(facultyName, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lectureserieses before and after the current lectureseries in the ordered set where facultyName = &#63;.
	 *
	 * @param lectureseriesId the primary key of the current lectureseries
	 * @param facultyName the faculty name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries[] findByFaculty_PrevAndNext(long lectureseriesId,
		String facultyName, OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = findByPrimaryKey(lectureseriesId);

		Session session = null;

		try {
			session = openSession();

			Lectureseries[] array = new LectureseriesImpl[3];

			array[0] = getByFaculty_PrevAndNext(session, lectureseries,
					facultyName, orderByComparator, true);

			array[1] = lectureseries;

			array[2] = getByFaculty_PrevAndNext(session, lectureseries,
					facultyName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Lectureseries getByFaculty_PrevAndNext(Session session,
		Lectureseries lectureseries, String facultyName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LECTURESERIES_WHERE);

		boolean bindFacultyName = false;

		if (facultyName == null) {
			query.append(_FINDER_COLUMN_FACULTY_FACULTYNAME_1);
		}
		else if (facultyName.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_FACULTY_FACULTYNAME_3);
		}
		else {
			bindFacultyName = true;

			query.append(_FINDER_COLUMN_FACULTY_FACULTYNAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindFacultyName) {
			qPos.add(facultyName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lectureseries);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Lectureseries> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lectureserieses where facultyName = &#63; from the database.
	 *
	 * @param facultyName the faculty name
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByFaculty(String facultyName) throws SystemException {
		for (Lectureseries lectureseries : findByFaculty(facultyName,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lectureseries);
		}
	}

	/**
	 * Returns the number of lectureserieses where facultyName = &#63;.
	 *
	 * @param facultyName the faculty name
	 * @return the number of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByFaculty(String facultyName) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_FACULTY;

		Object[] finderArgs = new Object[] { facultyName };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LECTURESERIES_WHERE);

			boolean bindFacultyName = false;

			if (facultyName == null) {
				query.append(_FINDER_COLUMN_FACULTY_FACULTYNAME_1);
			}
			else if (facultyName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_FACULTY_FACULTYNAME_3);
			}
			else {
				bindFacultyName = true;

				query.append(_FINDER_COLUMN_FACULTY_FACULTYNAME_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindFacultyName) {
					qPos.add(facultyName);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_FACULTY_FACULTYNAME_1 = "lectureseries.facultyName IS NULL";
	private static final String _FINDER_COLUMN_FACULTY_FACULTYNAME_2 = "lectureseries.facultyName = ?";
	private static final String _FINDER_COLUMN_FACULTY_FACULTYNAME_3 = "(lectureseries.facultyName IS NULL OR lectureseries.facultyName = '')";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_APPROVED = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByApproved",
			new String[] {
				Integer.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPROVED =
		new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByApproved", new String[] { Integer.class.getName() },
			LectureseriesModelImpl.APPROVED_COLUMN_BITMASK |
			LectureseriesModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_APPROVED = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByApproved",
			new String[] { Integer.class.getName() });

	/**
	 * Returns all the lectureserieses where approved = &#63;.
	 *
	 * @param approved the approved
	 * @return the matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByApproved(int approved)
		throws SystemException {
		return findByApproved(approved, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the lectureserieses where approved = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param approved the approved
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @return the range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByApproved(int approved, int start, int end)
		throws SystemException {
		return findByApproved(approved, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lectureserieses where approved = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param approved the approved
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByApproved(int approved, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPROVED;
			finderArgs = new Object[] { approved };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_APPROVED;
			finderArgs = new Object[] { approved, start, end, orderByComparator };
		}

		List<Lectureseries> list = (List<Lectureseries>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Lectureseries lectureseries : list) {
				if ((approved != lectureseries.getApproved())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LECTURESERIES_WHERE);

			query.append(_FINDER_COLUMN_APPROVED_APPROVED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(approved);

				if (!pagination) {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Lectureseries>(list);
				}
				else {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first lectureseries in the ordered set where approved = &#63;.
	 *
	 * @param approved the approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByApproved_First(int approved,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByApproved_First(approved,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("approved=");
		msg.append(approved);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the first lectureseries in the ordered set where approved = &#63;.
	 *
	 * @param approved the approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByApproved_First(int approved,
		OrderByComparator orderByComparator) throws SystemException {
		List<Lectureseries> list = findByApproved(approved, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lectureseries in the ordered set where approved = &#63;.
	 *
	 * @param approved the approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByApproved_Last(int approved,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByApproved_Last(approved,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("approved=");
		msg.append(approved);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the last lectureseries in the ordered set where approved = &#63;.
	 *
	 * @param approved the approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByApproved_Last(int approved,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByApproved(approved);

		if (count == 0) {
			return null;
		}

		List<Lectureseries> list = findByApproved(approved, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lectureserieses before and after the current lectureseries in the ordered set where approved = &#63;.
	 *
	 * @param lectureseriesId the primary key of the current lectureseries
	 * @param approved the approved
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries[] findByApproved_PrevAndNext(long lectureseriesId,
		int approved, OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = findByPrimaryKey(lectureseriesId);

		Session session = null;

		try {
			session = openSession();

			Lectureseries[] array = new LectureseriesImpl[3];

			array[0] = getByApproved_PrevAndNext(session, lectureseries,
					approved, orderByComparator, true);

			array[1] = lectureseries;

			array[2] = getByApproved_PrevAndNext(session, lectureseries,
					approved, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Lectureseries getByApproved_PrevAndNext(Session session,
		Lectureseries lectureseries, int approved,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LECTURESERIES_WHERE);

		query.append(_FINDER_COLUMN_APPROVED_APPROVED_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		qPos.add(approved);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lectureseries);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Lectureseries> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lectureserieses where approved = &#63; from the database.
	 *
	 * @param approved the approved
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByApproved(int approved) throws SystemException {
		for (Lectureseries lectureseries : findByApproved(approved,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lectureseries);
		}
	}

	/**
	 * Returns the number of lectureserieses where approved = &#63;.
	 *
	 * @param approved the approved
	 * @return the number of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByApproved(int approved) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_APPROVED;

		Object[] finderArgs = new Object[] { approved };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LECTURESERIES_WHERE);

			query.append(_FINDER_COLUMN_APPROVED_APPROVED_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(approved);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_APPROVED_APPROVED_2 = "lectureseries.approved = ?";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_BY_PASSWORD = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findByPassword",
			new String[] {
				String.class.getName(),
				
			Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			});
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PASSWORD =
		new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED,
			LectureseriesImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByPassword", new String[] { String.class.getName() },
			LectureseriesModelImpl.PASSWORD_COLUMN_BITMASK |
			LectureseriesModelImpl.NAME_COLUMN_BITMASK);
	public static final FinderPath FINDER_PATH_COUNT_BY_PASSWORD = new FinderPath(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByPassword",
			new String[] { String.class.getName() });

	/**
	 * Returns all the lectureserieses where password = &#63;.
	 *
	 * @param password the password
	 * @return the matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByPassword(String password)
		throws SystemException {
		return findByPassword(password, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the lectureserieses where password = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param password the password
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @return the range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByPassword(String password, int start,
		int end) throws SystemException {
		return findByPassword(password, start, end, null);
	}

	/**
	 * Returns an ordered range of all the lectureserieses where password = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param password the password
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findByPassword(String password, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PASSWORD;
			finderArgs = new Object[] { password };
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_BY_PASSWORD;
			finderArgs = new Object[] { password, start, end, orderByComparator };
		}

		List<Lectureseries> list = (List<Lectureseries>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if ((list != null) && !list.isEmpty()) {
			for (Lectureseries lectureseries : list) {
				if (!Validator.equals(password, lectureseries.getPassword())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_LECTURESERIES_WHERE);

			boolean bindPassword = false;

			if (password == null) {
				query.append(_FINDER_COLUMN_PASSWORD_PASSWORD_1);
			}
			else if (password.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PASSWORD_PASSWORD_3);
			}
			else {
				bindPassword = true;

				query.append(_FINDER_COLUMN_PASSWORD_PASSWORD_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}
			else
			 if (pagination) {
				query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPassword) {
					qPos.add(password);
				}

				if (!pagination) {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Lectureseries>(list);
				}
				else {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first lectureseries in the ordered set where password = &#63;.
	 *
	 * @param password the password
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByPassword_First(String password,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByPassword_First(password,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("password=");
		msg.append(password);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the first lectureseries in the ordered set where password = &#63;.
	 *
	 * @param password the password
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByPassword_First(String password,
		OrderByComparator orderByComparator) throws SystemException {
		List<Lectureseries> list = findByPassword(password, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last lectureseries in the ordered set where password = &#63;.
	 *
	 * @param password the password
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByPassword_Last(String password,
		OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByPassword_Last(password,
				orderByComparator);

		if (lectureseries != null) {
			return lectureseries;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("password=");
		msg.append(password);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLectureseriesException(msg.toString());
	}

	/**
	 * Returns the last lectureseries in the ordered set where password = &#63;.
	 *
	 * @param password the password
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching lectureseries, or <code>null</code> if a matching lectureseries could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByPassword_Last(String password,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPassword(password);

		if (count == 0) {
			return null;
		}

		List<Lectureseries> list = findByPassword(password, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the lectureserieses before and after the current lectureseries in the ordered set where password = &#63;.
	 *
	 * @param lectureseriesId the primary key of the current lectureseries
	 * @param password the password
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries[] findByPassword_PrevAndNext(long lectureseriesId,
		String password, OrderByComparator orderByComparator)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = findByPrimaryKey(lectureseriesId);

		Session session = null;

		try {
			session = openSession();

			Lectureseries[] array = new LectureseriesImpl[3];

			array[0] = getByPassword_PrevAndNext(session, lectureseries,
					password, orderByComparator, true);

			array[1] = lectureseries;

			array[2] = getByPassword_PrevAndNext(session, lectureseries,
					password, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	protected Lectureseries getByPassword_PrevAndNext(Session session,
		Lectureseries lectureseries, String password,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LECTURESERIES_WHERE);

		boolean bindPassword = false;

		if (password == null) {
			query.append(_FINDER_COLUMN_PASSWORD_PASSWORD_1);
		}
		else if (password.equals(StringPool.BLANK)) {
			query.append(_FINDER_COLUMN_PASSWORD_PASSWORD_3);
		}
		else {
			bindPassword = true;

			query.append(_FINDER_COLUMN_PASSWORD_PASSWORD_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			query.append(LectureseriesModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		Query q = session.createQuery(sql);

		q.setFirstResult(0);
		q.setMaxResults(2);

		QueryPos qPos = QueryPos.getInstance(q);

		if (bindPassword) {
			qPos.add(password);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(lectureseries);

			for (Object value : values) {
				qPos.add(value);
			}
		}

		List<Lectureseries> list = q.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the lectureserieses where password = &#63; from the database.
	 *
	 * @param password the password
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeByPassword(String password) throws SystemException {
		for (Lectureseries lectureseries : findByPassword(password,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {
			remove(lectureseries);
		}
	}

	/**
	 * Returns the number of lectureserieses where password = &#63;.
	 *
	 * @param password the password
	 * @return the number of matching lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countByPassword(String password) throws SystemException {
		FinderPath finderPath = FINDER_PATH_COUNT_BY_PASSWORD;

		Object[] finderArgs = new Object[] { password };

		Long count = (Long)FinderCacheUtil.getResult(finderPath, finderArgs,
				this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_LECTURESERIES_WHERE);

			boolean bindPassword = false;

			if (password == null) {
				query.append(_FINDER_COLUMN_PASSWORD_PASSWORD_1);
			}
			else if (password.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PASSWORD_PASSWORD_3);
			}
			else {
				bindPassword = true;

				query.append(_FINDER_COLUMN_PASSWORD_PASSWORD_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindPassword) {
					qPos.add(password);
				}

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_PASSWORD_PASSWORD_1 = "lectureseries.password IS NULL";
	private static final String _FINDER_COLUMN_PASSWORD_PASSWORD_2 = "lectureseries.password = ?";
	private static final String _FINDER_COLUMN_PASSWORD_PASSWORD_3 = "(lectureseries.password IS NULL OR lectureseries.password = '')";

	public LectureseriesPersistenceImpl() {
		setModelClass(Lectureseries.class);
	}

	/**
	 * Caches the lectureseries in the entity cache if it is enabled.
	 *
	 * @param lectureseries the lectureseries
	 */
	@Override
	public void cacheResult(Lectureseries lectureseries) {
		EntityCacheUtil.putResult(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesImpl.class, lectureseries.getPrimaryKey(),
			lectureseries);

		lectureseries.resetOriginalValues();
	}

	/**
	 * Caches the lectureserieses in the entity cache if it is enabled.
	 *
	 * @param lectureserieses the lectureserieses
	 */
	@Override
	public void cacheResult(List<Lectureseries> lectureserieses) {
		for (Lectureseries lectureseries : lectureserieses) {
			if (EntityCacheUtil.getResult(
						LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
						LectureseriesImpl.class, lectureseries.getPrimaryKey()) == null) {
				cacheResult(lectureseries);
			}
			else {
				lectureseries.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all lectureserieses.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		if (_HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE) {
			CacheRegistryUtil.clear(LectureseriesImpl.class.getName());
		}

		EntityCacheUtil.clearCache(LectureseriesImpl.class.getName());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the lectureseries.
	 *
	 * <p>
	 * The {@link com.liferay.portal.kernel.dao.orm.EntityCache} and {@link com.liferay.portal.kernel.dao.orm.FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Lectureseries lectureseries) {
		EntityCacheUtil.removeResult(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesImpl.class, lectureseries.getPrimaryKey());

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Lectureseries> lectureserieses) {
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Lectureseries lectureseries : lectureserieses) {
			EntityCacheUtil.removeResult(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
				LectureseriesImpl.class, lectureseries.getPrimaryKey());
		}
	}

	/**
	 * Creates a new lectureseries with the primary key. Does not add the lectureseries to the database.
	 *
	 * @param lectureseriesId the primary key for the new lectureseries
	 * @return the new lectureseries
	 */
	@Override
	public Lectureseries create(long lectureseriesId) {
		Lectureseries lectureseries = new LectureseriesImpl();

		lectureseries.setNew(true);
		lectureseries.setPrimaryKey(lectureseriesId);

		return lectureseries;
	}

	/**
	 * Removes the lectureseries with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param lectureseriesId the primary key of the lectureseries
	 * @return the lectureseries that was removed
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries remove(long lectureseriesId)
		throws NoSuchLectureseriesException, SystemException {
		return remove((Serializable)lectureseriesId);
	}

	/**
	 * Removes the lectureseries with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the lectureseries
	 * @return the lectureseries that was removed
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries remove(Serializable primaryKey)
		throws NoSuchLectureseriesException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Lectureseries lectureseries = (Lectureseries)session.get(LectureseriesImpl.class,
					primaryKey);

			if (lectureseries == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLectureseriesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(lectureseries);
		}
		catch (NoSuchLectureseriesException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Lectureseries removeImpl(Lectureseries lectureseries)
		throws SystemException {
		lectureseries = toUnwrappedModel(lectureseries);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(lectureseries)) {
				lectureseries = (Lectureseries)session.get(LectureseriesImpl.class,
						lectureseries.getPrimaryKeyObj());
			}

			if (lectureseries != null) {
				session.delete(lectureseries);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (lectureseries != null) {
			clearCache(lectureseries);
		}

		return lectureseries;
	}

	@Override
	public Lectureseries updateImpl(
		de.uhh.l2g.plugins.model.Lectureseries lectureseries)
		throws SystemException {
		lectureseries = toUnwrappedModel(lectureseries);

		boolean isNew = lectureseries.isNew();

		LectureseriesModelImpl lectureseriesModelImpl = (LectureseriesModelImpl)lectureseries;

		Session session = null;

		try {
			session = openSession();

			if (lectureseries.isNew()) {
				session.save(lectureseries);

				lectureseries.setNew(false);
			}
			else {
				session.merge(lectureseries);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew || !LectureseriesModelImpl.COLUMN_BITMASK_ENABLED) {
			FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}

		else {
			if ((lectureseriesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SEMESTER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lectureseriesModelImpl.getOriginalSemesterName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SEMESTER, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SEMESTER,
					args);

				args = new Object[] { lectureseriesModelImpl.getSemesterName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_SEMESTER, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_SEMESTER,
					args);
			}

			if ((lectureseriesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LANGUAGE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lectureseriesModelImpl.getOriginalLanguage()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LANGUAGE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LANGUAGE,
					args);

				args = new Object[] { lectureseriesModelImpl.getLanguage() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_LANGUAGE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_LANGUAGE,
					args);
			}

			if ((lectureseriesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NUMBER.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lectureseriesModelImpl.getOriginalNumber()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NUMBER, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NUMBER,
					args);

				args = new Object[] { lectureseriesModelImpl.getNumber() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NUMBER, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NUMBER,
					args);
			}

			if ((lectureseriesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lectureseriesModelImpl.getOriginalEventType()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);

				args = new Object[] { lectureseriesModelImpl.getEventType() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_TYPE, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_TYPE,
					args);
			}

			if ((lectureseriesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lectureseriesModelImpl.getOriginalEventCategory()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CATEGORY, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORY,
					args);

				args = new Object[] { lectureseriesModelImpl.getEventCategory() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_CATEGORY, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_CATEGORY,
					args);
			}

			if ((lectureseriesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lectureseriesModelImpl.getOriginalName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);

				args = new Object[] { lectureseriesModelImpl.getName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_NAME, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_NAME,
					args);
			}

			if ((lectureseriesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FACULTY.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lectureseriesModelImpl.getOriginalFacultyName()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FACULTY, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FACULTY,
					args);

				args = new Object[] { lectureseriesModelImpl.getFacultyName() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_FACULTY, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_FACULTY,
					args);
			}

			if ((lectureseriesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPROVED.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lectureseriesModelImpl.getOriginalApproved()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPROVED, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPROVED,
					args);

				args = new Object[] { lectureseriesModelImpl.getApproved() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_APPROVED, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_APPROVED,
					args);
			}

			if ((lectureseriesModelImpl.getColumnBitmask() &
					FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PASSWORD.getColumnBitmask()) != 0) {
				Object[] args = new Object[] {
						lectureseriesModelImpl.getOriginalPassword()
					};

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PASSWORD, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PASSWORD,
					args);

				args = new Object[] { lectureseriesModelImpl.getPassword() };

				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_BY_PASSWORD, args);
				FinderCacheUtil.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_BY_PASSWORD,
					args);
			}
		}

		EntityCacheUtil.putResult(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
			LectureseriesImpl.class, lectureseries.getPrimaryKey(),
			lectureseries);

		return lectureseries;
	}

	protected Lectureseries toUnwrappedModel(Lectureseries lectureseries) {
		if (lectureseries instanceof LectureseriesImpl) {
			return lectureseries;
		}

		LectureseriesImpl lectureseriesImpl = new LectureseriesImpl();

		lectureseriesImpl.setNew(lectureseries.isNew());
		lectureseriesImpl.setPrimaryKey(lectureseries.getPrimaryKey());

		lectureseriesImpl.setNumber(lectureseries.getNumber());
		lectureseriesImpl.setEventType(lectureseries.getEventType());
		lectureseriesImpl.setEventCategory(lectureseries.getEventCategory());
		lectureseriesImpl.setName(lectureseries.getName());
		lectureseriesImpl.setShortDesc(lectureseries.getShortDesc());
		lectureseriesImpl.setSemesterName(lectureseries.getSemesterName());
		lectureseriesImpl.setLanguage(lectureseries.getLanguage());
		lectureseriesImpl.setFacultyName(lectureseries.getFacultyName());
		lectureseriesImpl.setInstructorsString(lectureseries.getInstructorsString());
		lectureseriesImpl.setLectureseriesId(lectureseries.getLectureseriesId());
		lectureseriesImpl.setPassword(lectureseries.getPassword());
		lectureseriesImpl.setApproved(lectureseries.getApproved());
		lectureseriesImpl.setLongDesc(lectureseries.getLongDesc());

		return lectureseriesImpl;
	}

	/**
	 * Returns the lectureseries with the primary key or throws a {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the lectureseries
	 * @return the lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByPrimaryKey(Serializable primaryKey)
		throws NoSuchLectureseriesException, SystemException {
		Lectureseries lectureseries = fetchByPrimaryKey(primaryKey);

		if (lectureseries == null) {
			if (_log.isWarnEnabled()) {
				_log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchLectureseriesException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return lectureseries;
	}

	/**
	 * Returns the lectureseries with the primary key or throws a {@link de.uhh.l2g.plugins.NoSuchLectureseriesException} if it could not be found.
	 *
	 * @param lectureseriesId the primary key of the lectureseries
	 * @return the lectureseries
	 * @throws de.uhh.l2g.plugins.NoSuchLectureseriesException if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries findByPrimaryKey(long lectureseriesId)
		throws NoSuchLectureseriesException, SystemException {
		return findByPrimaryKey((Serializable)lectureseriesId);
	}

	/**
	 * Returns the lectureseries with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the lectureseries
	 * @return the lectureseries, or <code>null</code> if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		Lectureseries lectureseries = (Lectureseries)EntityCacheUtil.getResult(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
				LectureseriesImpl.class, primaryKey);

		if (lectureseries == _nullLectureseries) {
			return null;
		}

		if (lectureseries == null) {
			Session session = null;

			try {
				session = openSession();

				lectureseries = (Lectureseries)session.get(LectureseriesImpl.class,
						primaryKey);

				if (lectureseries != null) {
					cacheResult(lectureseries);
				}
				else {
					EntityCacheUtil.putResult(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
						LectureseriesImpl.class, primaryKey, _nullLectureseries);
				}
			}
			catch (Exception e) {
				EntityCacheUtil.removeResult(LectureseriesModelImpl.ENTITY_CACHE_ENABLED,
					LectureseriesImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return lectureseries;
	}

	/**
	 * Returns the lectureseries with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param lectureseriesId the primary key of the lectureseries
	 * @return the lectureseries, or <code>null</code> if a lectureseries with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public Lectureseries fetchByPrimaryKey(long lectureseriesId)
		throws SystemException {
		return fetchByPrimaryKey((Serializable)lectureseriesId);
	}

	/**
	 * Returns all the lectureserieses.
	 *
	 * @return the lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the lectureserieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @return the range of lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the lectureserieses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.model.impl.LectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of lectureserieses
	 * @param end the upper bound of the range of lectureserieses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public List<Lectureseries> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Lectureseries> list = (List<Lectureseries>)FinderCacheUtil.getResult(finderPath,
				finderArgs, this);

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_LECTURESERIES);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_LECTURESERIES;

				if (pagination) {
					sql = sql.concat(LectureseriesModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = new UnmodifiableList<Lectureseries>(list);
				}
				else {
					list = (List<Lectureseries>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				FinderCacheUtil.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the lectureserieses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public void removeAll() throws SystemException {
		for (Lectureseries lectureseries : findAll()) {
			remove(lectureseries);
		}
	}

	/**
	 * Returns the number of lectureserieses.
	 *
	 * @return the number of lectureserieses
	 * @throws SystemException if a system exception occurred
	 */
	@Override
	public int countAll() throws SystemException {
		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_LECTURESERIES);

				count = (Long)q.uniqueResult();

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				FinderCacheUtil.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	/**
	 * Initializes the lectureseries persistence.
	 */
	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.de.uhh.l2g.plugins.model.Lectureseries")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Lectureseries>> listenersList = new ArrayList<ModelListener<Lectureseries>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Lectureseries>)InstanceFactory.newInstance(
							getClassLoader(), listenerClassName));
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	public void destroy() {
		EntityCacheUtil.removeCache(LectureseriesImpl.class.getName());
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		FinderCacheUtil.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	private static final String _SQL_SELECT_LECTURESERIES = "SELECT lectureseries FROM Lectureseries lectureseries";
	private static final String _SQL_SELECT_LECTURESERIES_WHERE = "SELECT lectureseries FROM Lectureseries lectureseries WHERE ";
	private static final String _SQL_COUNT_LECTURESERIES = "SELECT COUNT(lectureseries) FROM Lectureseries lectureseries";
	private static final String _SQL_COUNT_LECTURESERIES_WHERE = "SELECT COUNT(lectureseries) FROM Lectureseries lectureseries WHERE ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "lectureseries.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Lectureseries exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No Lectureseries exists with the key {";
	private static final boolean _HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE = GetterUtil.getBoolean(PropsUtil.get(
				PropsKeys.HIBERNATE_CACHE_USE_SECOND_LEVEL_CACHE));
	private static Log _log = LogFactoryUtil.getLog(LectureseriesPersistenceImpl.class);
	private static Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"number", "password"
			});
	private static Lectureseries _nullLectureseries = new LectureseriesImpl() {
			@Override
			public Object clone() {
				return this;
			}

			@Override
			public CacheModel<Lectureseries> toCacheModel() {
				return _nullLectureseriesCacheModel;
			}
		};

	private static CacheModel<Lectureseries> _nullLectureseriesCacheModel = new CacheModel<Lectureseries>() {
			@Override
			public Lectureseries toEntityModel() {
				return _nullLectureseries;
			}
		};
}