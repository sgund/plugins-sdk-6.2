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

package de.uhh.l2g.plugins.migration.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link LegacyProducerLectureseriesLocalService}.
 *
 * @author unihh
 * @see LegacyProducerLectureseriesLocalService
 * @generated
 */
public class LegacyProducerLectureseriesLocalServiceWrapper
	implements LegacyProducerLectureseriesLocalService,
		ServiceWrapper<LegacyProducerLectureseriesLocalService> {
	public LegacyProducerLectureseriesLocalServiceWrapper(
		LegacyProducerLectureseriesLocalService legacyProducerLectureseriesLocalService) {
		_legacyProducerLectureseriesLocalService = legacyProducerLectureseriesLocalService;
	}

	/**
	* Adds the legacy producer lectureseries to the database. Also notifies the appropriate model listeners.
	*
	* @param legacyProducerLectureseries the legacy producer lectureseries
	* @return the legacy producer lectureseries that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries addLegacyProducerLectureseries(
		de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries legacyProducerLectureseries)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.addLegacyProducerLectureseries(legacyProducerLectureseries);
	}

	/**
	* Creates a new legacy producer lectureseries with the primary key. Does not add the legacy producer lectureseries to the database.
	*
	* @param id the primary key for the new legacy producer lectureseries
	* @return the new legacy producer lectureseries
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries createLegacyProducerLectureseries(
		long id) {
		return _legacyProducerLectureseriesLocalService.createLegacyProducerLectureseries(id);
	}

	/**
	* Deletes the legacy producer lectureseries with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the legacy producer lectureseries
	* @return the legacy producer lectureseries that was removed
	* @throws PortalException if a legacy producer lectureseries with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries deleteLegacyProducerLectureseries(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.deleteLegacyProducerLectureseries(id);
	}

	/**
	* Deletes the legacy producer lectureseries from the database. Also notifies the appropriate model listeners.
	*
	* @param legacyProducerLectureseries the legacy producer lectureseries
	* @return the legacy producer lectureseries that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries deleteLegacyProducerLectureseries(
		de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries legacyProducerLectureseries)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.deleteLegacyProducerLectureseries(legacyProducerLectureseries);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _legacyProducerLectureseriesLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.migration.model.impl.LegacyProducerLectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.migration.model.impl.LegacyProducerLectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.dynamicQuery(dynamicQuery,
			start, end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries fetchLegacyProducerLectureseries(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.fetchLegacyProducerLectureseries(id);
	}

	/**
	* Returns the legacy producer lectureseries with the primary key.
	*
	* @param id the primary key of the legacy producer lectureseries
	* @return the legacy producer lectureseries
	* @throws PortalException if a legacy producer lectureseries with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries getLegacyProducerLectureseries(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.getLegacyProducerLectureseries(id);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the legacy producer lectureserieses.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.migration.model.impl.LegacyProducerLectureseriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of legacy producer lectureserieses
	* @param end the upper bound of the range of legacy producer lectureserieses (not inclusive)
	* @return the range of legacy producer lectureserieses
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries> getLegacyProducerLectureserieses(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.getLegacyProducerLectureserieses(start,
			end);
	}

	/**
	* Returns the number of legacy producer lectureserieses.
	*
	* @return the number of legacy producer lectureserieses
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getLegacyProducerLectureseriesesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.getLegacyProducerLectureseriesesCount();
	}

	/**
	* Updates the legacy producer lectureseries in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param legacyProducerLectureseries the legacy producer lectureseries
	* @return the legacy producer lectureseries that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries updateLegacyProducerLectureseries(
		de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries legacyProducerLectureseries)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyProducerLectureseriesLocalService.updateLegacyProducerLectureseries(legacyProducerLectureseries);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _legacyProducerLectureseriesLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_legacyProducerLectureseriesLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _legacyProducerLectureseriesLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public LegacyProducerLectureseriesLocalService getWrappedLegacyProducerLectureseriesLocalService() {
		return _legacyProducerLectureseriesLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedLegacyProducerLectureseriesLocalService(
		LegacyProducerLectureseriesLocalService legacyProducerLectureseriesLocalService) {
		_legacyProducerLectureseriesLocalService = legacyProducerLectureseriesLocalService;
	}

	@Override
	public LegacyProducerLectureseriesLocalService getWrappedService() {
		return _legacyProducerLectureseriesLocalService;
	}

	@Override
	public void setWrappedService(
		LegacyProducerLectureseriesLocalService legacyProducerLectureseriesLocalService) {
		_legacyProducerLectureseriesLocalService = legacyProducerLectureseriesLocalService;
	}

	private LegacyProducerLectureseriesLocalService _legacyProducerLectureseriesLocalService;
}