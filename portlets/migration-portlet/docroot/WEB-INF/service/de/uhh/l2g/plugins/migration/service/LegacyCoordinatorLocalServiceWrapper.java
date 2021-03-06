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
 * Provides a wrapper for {@link LegacyCoordinatorLocalService}.
 *
 * @author unihh
 * @see LegacyCoordinatorLocalService
 * @generated
 */
public class LegacyCoordinatorLocalServiceWrapper
	implements LegacyCoordinatorLocalService,
		ServiceWrapper<LegacyCoordinatorLocalService> {
	public LegacyCoordinatorLocalServiceWrapper(
		LegacyCoordinatorLocalService legacyCoordinatorLocalService) {
		_legacyCoordinatorLocalService = legacyCoordinatorLocalService;
	}

	/**
	* Adds the legacy coordinator to the database. Also notifies the appropriate model listeners.
	*
	* @param legacyCoordinator the legacy coordinator
	* @return the legacy coordinator that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyCoordinator addLegacyCoordinator(
		de.uhh.l2g.plugins.migration.model.LegacyCoordinator legacyCoordinator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyCoordinatorLocalService.addLegacyCoordinator(legacyCoordinator);
	}

	/**
	* Creates a new legacy coordinator with the primary key. Does not add the legacy coordinator to the database.
	*
	* @param userId the primary key for the new legacy coordinator
	* @return the new legacy coordinator
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyCoordinator createLegacyCoordinator(
		long userId) {
		return _legacyCoordinatorLocalService.createLegacyCoordinator(userId);
	}

	/**
	* Deletes the legacy coordinator with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param userId the primary key of the legacy coordinator
	* @return the legacy coordinator that was removed
	* @throws PortalException if a legacy coordinator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyCoordinator deleteLegacyCoordinator(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _legacyCoordinatorLocalService.deleteLegacyCoordinator(userId);
	}

	/**
	* Deletes the legacy coordinator from the database. Also notifies the appropriate model listeners.
	*
	* @param legacyCoordinator the legacy coordinator
	* @return the legacy coordinator that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyCoordinator deleteLegacyCoordinator(
		de.uhh.l2g.plugins.migration.model.LegacyCoordinator legacyCoordinator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyCoordinatorLocalService.deleteLegacyCoordinator(legacyCoordinator);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _legacyCoordinatorLocalService.dynamicQuery();
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
		return _legacyCoordinatorLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.migration.model.impl.LegacyCoordinatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _legacyCoordinatorLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.migration.model.impl.LegacyCoordinatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _legacyCoordinatorLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
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
		return _legacyCoordinatorLocalService.dynamicQueryCount(dynamicQuery);
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
		return _legacyCoordinatorLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyCoordinator fetchLegacyCoordinator(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyCoordinatorLocalService.fetchLegacyCoordinator(userId);
	}

	/**
	* Returns the legacy coordinator with the primary key.
	*
	* @param userId the primary key of the legacy coordinator
	* @return the legacy coordinator
	* @throws PortalException if a legacy coordinator with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyCoordinator getLegacyCoordinator(
		long userId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _legacyCoordinatorLocalService.getLegacyCoordinator(userId);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _legacyCoordinatorLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the legacy coordinators.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.migration.model.impl.LegacyCoordinatorModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of legacy coordinators
	* @param end the upper bound of the range of legacy coordinators (not inclusive)
	* @return the range of legacy coordinators
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<de.uhh.l2g.plugins.migration.model.LegacyCoordinator> getLegacyCoordinators(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyCoordinatorLocalService.getLegacyCoordinators(start, end);
	}

	/**
	* Returns the number of legacy coordinators.
	*
	* @return the number of legacy coordinators
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getLegacyCoordinatorsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyCoordinatorLocalService.getLegacyCoordinatorsCount();
	}

	/**
	* Updates the legacy coordinator in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param legacyCoordinator the legacy coordinator
	* @return the legacy coordinator that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyCoordinator updateLegacyCoordinator(
		de.uhh.l2g.plugins.migration.model.LegacyCoordinator legacyCoordinator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyCoordinatorLocalService.updateLegacyCoordinator(legacyCoordinator);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _legacyCoordinatorLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_legacyCoordinatorLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _legacyCoordinatorLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public LegacyCoordinatorLocalService getWrappedLegacyCoordinatorLocalService() {
		return _legacyCoordinatorLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedLegacyCoordinatorLocalService(
		LegacyCoordinatorLocalService legacyCoordinatorLocalService) {
		_legacyCoordinatorLocalService = legacyCoordinatorLocalService;
	}

	@Override
	public LegacyCoordinatorLocalService getWrappedService() {
		return _legacyCoordinatorLocalService;
	}

	@Override
	public void setWrappedService(
		LegacyCoordinatorLocalService legacyCoordinatorLocalService) {
		_legacyCoordinatorLocalService = legacyCoordinatorLocalService;
	}

	private LegacyCoordinatorLocalService _legacyCoordinatorLocalService;
}