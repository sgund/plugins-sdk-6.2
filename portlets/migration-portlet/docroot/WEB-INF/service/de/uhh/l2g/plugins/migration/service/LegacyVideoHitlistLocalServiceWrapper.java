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
 * Provides a wrapper for {@link LegacyVideoHitlistLocalService}.
 *
 * @author unihh
 * @see LegacyVideoHitlistLocalService
 * @generated
 */
public class LegacyVideoHitlistLocalServiceWrapper
	implements LegacyVideoHitlistLocalService,
		ServiceWrapper<LegacyVideoHitlistLocalService> {
	public LegacyVideoHitlistLocalServiceWrapper(
		LegacyVideoHitlistLocalService legacyVideoHitlistLocalService) {
		_legacyVideoHitlistLocalService = legacyVideoHitlistLocalService;
	}

	/**
	* Adds the legacy video hitlist to the database. Also notifies the appropriate model listeners.
	*
	* @param legacyVideoHitlist the legacy video hitlist
	* @return the legacy video hitlist that was added
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyVideoHitlist addLegacyVideoHitlist(
		de.uhh.l2g.plugins.migration.model.LegacyVideoHitlist legacyVideoHitlist)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyVideoHitlistLocalService.addLegacyVideoHitlist(legacyVideoHitlist);
	}

	/**
	* Creates a new legacy video hitlist with the primary key. Does not add the legacy video hitlist to the database.
	*
	* @param id the primary key for the new legacy video hitlist
	* @return the new legacy video hitlist
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyVideoHitlist createLegacyVideoHitlist(
		long id) {
		return _legacyVideoHitlistLocalService.createLegacyVideoHitlist(id);
	}

	/**
	* Deletes the legacy video hitlist with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param id the primary key of the legacy video hitlist
	* @return the legacy video hitlist that was removed
	* @throws PortalException if a legacy video hitlist with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyVideoHitlist deleteLegacyVideoHitlist(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _legacyVideoHitlistLocalService.deleteLegacyVideoHitlist(id);
	}

	/**
	* Deletes the legacy video hitlist from the database. Also notifies the appropriate model listeners.
	*
	* @param legacyVideoHitlist the legacy video hitlist
	* @return the legacy video hitlist that was removed
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyVideoHitlist deleteLegacyVideoHitlist(
		de.uhh.l2g.plugins.migration.model.LegacyVideoHitlist legacyVideoHitlist)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyVideoHitlistLocalService.deleteLegacyVideoHitlist(legacyVideoHitlist);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _legacyVideoHitlistLocalService.dynamicQuery();
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
		return _legacyVideoHitlistLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.migration.model.impl.LegacyVideoHitlistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _legacyVideoHitlistLocalService.dynamicQuery(dynamicQuery,
			start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.migration.model.impl.LegacyVideoHitlistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
		return _legacyVideoHitlistLocalService.dynamicQuery(dynamicQuery,
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
		return _legacyVideoHitlistLocalService.dynamicQueryCount(dynamicQuery);
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
		return _legacyVideoHitlistLocalService.dynamicQueryCount(dynamicQuery,
			projection);
	}

	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyVideoHitlist fetchLegacyVideoHitlist(
		long id) throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyVideoHitlistLocalService.fetchLegacyVideoHitlist(id);
	}

	/**
	* Returns the legacy video hitlist with the primary key.
	*
	* @param id the primary key of the legacy video hitlist
	* @return the legacy video hitlist
	* @throws PortalException if a legacy video hitlist with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyVideoHitlist getLegacyVideoHitlist(
		long id)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _legacyVideoHitlistLocalService.getLegacyVideoHitlist(id);
	}

	@Override
	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _legacyVideoHitlistLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the legacy video hitlists.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link de.uhh.l2g.plugins.migration.model.impl.LegacyVideoHitlistModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of legacy video hitlists
	* @param end the upper bound of the range of legacy video hitlists (not inclusive)
	* @return the range of legacy video hitlists
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.util.List<de.uhh.l2g.plugins.migration.model.LegacyVideoHitlist> getLegacyVideoHitlists(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyVideoHitlistLocalService.getLegacyVideoHitlists(start, end);
	}

	/**
	* Returns the number of legacy video hitlists.
	*
	* @return the number of legacy video hitlists
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public int getLegacyVideoHitlistsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyVideoHitlistLocalService.getLegacyVideoHitlistsCount();
	}

	/**
	* Updates the legacy video hitlist in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param legacyVideoHitlist the legacy video hitlist
	* @return the legacy video hitlist that was updated
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyVideoHitlist updateLegacyVideoHitlist(
		de.uhh.l2g.plugins.migration.model.LegacyVideoHitlist legacyVideoHitlist)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _legacyVideoHitlistLocalService.updateLegacyVideoHitlist(legacyVideoHitlist);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	@Override
	public java.lang.String getBeanIdentifier() {
		return _legacyVideoHitlistLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	@Override
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_legacyVideoHitlistLocalService.setBeanIdentifier(beanIdentifier);
	}

	@Override
	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _legacyVideoHitlistLocalService.invokeMethod(name,
			parameterTypes, arguments);
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
	 */
	public LegacyVideoHitlistLocalService getWrappedLegacyVideoHitlistLocalService() {
		return _legacyVideoHitlistLocalService;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
	 */
	public void setWrappedLegacyVideoHitlistLocalService(
		LegacyVideoHitlistLocalService legacyVideoHitlistLocalService) {
		_legacyVideoHitlistLocalService = legacyVideoHitlistLocalService;
	}

	@Override
	public LegacyVideoHitlistLocalService getWrappedService() {
		return _legacyVideoHitlistLocalService;
	}

	@Override
	public void setWrappedService(
		LegacyVideoHitlistLocalService legacyVideoHitlistLocalService) {
		_legacyVideoHitlistLocalService = legacyVideoHitlistLocalService;
	}

	private LegacyVideoHitlistLocalService _legacyVideoHitlistLocalService;
}