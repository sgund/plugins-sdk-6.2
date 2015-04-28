/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package de.uhh.l2g.plugins.model;

import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the Institution_Host service. Represents a row in the &quot;LG_Institution_Host&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link de.uhh.l2g.plugins.model.impl.Institution_HostModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link de.uhh.l2g.plugins.model.impl.Institution_HostImpl}.
 * </p>
 *
 * @author Iavor Sturm
 * @see Institution_Host
 * @see de.uhh.l2g.plugins.model.impl.Institution_HostImpl
 * @see de.uhh.l2g.plugins.model.impl.Institution_HostModelImpl
 * @generated
 */
public interface Institution_HostModel extends BaseModel<Institution_Host> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a institution_ host model instance should use the {@link Institution_Host} interface instead.
	 */

	/**
	 * Returns the primary key of this institution_ host.
	 *
	 * @return the primary key of this institution_ host
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this institution_ host.
	 *
	 * @param primaryKey the primary key of this institution_ host
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the institution host ID of this institution_ host.
	 *
	 * @return the institution host ID of this institution_ host
	 */
	public long getInstitutionHostId();

	/**
	 * Sets the institution host ID of this institution_ host.
	 *
	 * @param institutionHostId the institution host ID of this institution_ host
	 */
	public void setInstitutionHostId(long institutionHostId);

	/**
	 * Returns the institution ID of this institution_ host.
	 *
	 * @return the institution ID of this institution_ host
	 */
	public long getInstitutionId();

	/**
	 * Sets the institution ID of this institution_ host.
	 *
	 * @param institutionId the institution ID of this institution_ host
	 */
	public void setInstitutionId(long institutionId);

	/**
	 * Returns the host ID of this institution_ host.
	 *
	 * @return the host ID of this institution_ host
	 */
	public long getHostId();

	/**
	 * Sets the host ID of this institution_ host.
	 *
	 * @param hostId the host ID of this institution_ host
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
	public int compareTo(Institution_Host institution_Host);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Institution_Host> toCacheModel();

	@Override
	public Institution_Host toEscapedModel();

	@Override
	public Institution_Host toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}