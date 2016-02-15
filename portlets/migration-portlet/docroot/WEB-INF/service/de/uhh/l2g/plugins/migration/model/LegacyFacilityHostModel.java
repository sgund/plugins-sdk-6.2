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

package de.uhh.l2g.plugins.migration.model;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the LegacyFacilityHost service. Represents a row in the &quot;facility_host&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link de.uhh.l2g.plugins.migration.model.impl.LegacyFacilityHostModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link de.uhh.l2g.plugins.migration.model.impl.LegacyFacilityHostImpl}.
 * </p>
 *
 * @author unihh
 * @see LegacyFacilityHost
 * @see de.uhh.l2g.plugins.migration.model.impl.LegacyFacilityHostImpl
 * @see de.uhh.l2g.plugins.migration.model.impl.LegacyFacilityHostModelImpl
 * @generated
 */
public interface LegacyFacilityHostModel extends BaseModel<LegacyFacilityHost> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a legacy facility host model instance should use the {@link LegacyFacilityHost} interface instead.
	 */

	/**
	 * Returns the primary key of this legacy facility host.
	 *
	 * @return the primary key of this legacy facility host
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this legacy facility host.
	 *
	 * @param primaryKey the primary key of this legacy facility host
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the facility host ID of this legacy facility host.
	 *
	 * @return the facility host ID of this legacy facility host
	 */
	public long getFacilityHostId();

	/**
	 * Sets the facility host ID of this legacy facility host.
	 *
	 * @param facilityHostId the facility host ID of this legacy facility host
	 */
	public void setFacilityHostId(long facilityHostId);

	/**
	 * Returns the facility ID of this legacy facility host.
	 *
	 * @return the facility ID of this legacy facility host
	 */
	public long getFacilityId();

	/**
	 * Sets the facility ID of this legacy facility host.
	 *
	 * @param facilityId the facility ID of this legacy facility host
	 */
	public void setFacilityId(long facilityId);

	/**
	 * Returns the host ID of this legacy facility host.
	 *
	 * @return the host ID of this legacy facility host
	 */
	public long getHostId();

	/**
	 * Sets the host ID of this legacy facility host.
	 *
	 * @param hostId the host ID of this legacy facility host
	 */
	public void setHostId(long hostId);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		de.uhh.l2g.plugins.migration.model.LegacyFacilityHost legacyFacilityHost);

	@Override
	public int hashCode();

	@Override
	public CacheModel<de.uhh.l2g.plugins.migration.model.LegacyFacilityHost> toCacheModel();

	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyFacilityHost toEscapedModel();

	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyFacilityHost toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}