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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the LegacyProducer service. Represents a row in the &quot;producer&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link de.uhh.l2g.plugins.migration.model.impl.LegacyProducerModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link de.uhh.l2g.plugins.migration.model.impl.LegacyProducerImpl}.
 * </p>
 *
 * @author unihh
 * @see LegacyProducer
 * @see de.uhh.l2g.plugins.migration.model.impl.LegacyProducerImpl
 * @see de.uhh.l2g.plugins.migration.model.impl.LegacyProducerModelImpl
 * @generated
 */
public interface LegacyProducerModel extends BaseModel<LegacyProducer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a legacy producer model instance should use the {@link LegacyProducer} interface instead.
	 */

	/**
	 * Returns the primary key of this legacy producer.
	 *
	 * @return the primary key of this legacy producer
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this legacy producer.
	 *
	 * @param primaryKey the primary key of this legacy producer
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the ID of this legacy producer.
	 *
	 * @return the ID of this legacy producer
	 */
	public long getId();

	/**
	 * Sets the ID of this legacy producer.
	 *
	 * @param id the ID of this legacy producer
	 */
	public void setId(long id);

	/**
	 * Returns the id num of this legacy producer.
	 *
	 * @return the id num of this legacy producer
	 */
	@AutoEscape
	public String getIdNum();

	/**
	 * Sets the id num of this legacy producer.
	 *
	 * @param idNum the id num of this legacy producer
	 */
	public void setIdNum(String idNum);

	/**
	 * Returns the home dir of this legacy producer.
	 *
	 * @return the home dir of this legacy producer
	 */
	@AutoEscape
	public String getHomeDir();

	/**
	 * Sets the home dir of this legacy producer.
	 *
	 * @param homeDir the home dir of this legacy producer
	 */
	public void setHomeDir(String homeDir);

	/**
	 * Returns the user ID of this legacy producer.
	 *
	 * @return the user ID of this legacy producer
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this legacy producer.
	 *
	 * @param userId the user ID of this legacy producer
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this legacy producer.
	 *
	 * @return the user uuid of this legacy producer
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this legacy producer.
	 *
	 * @param userUuid the user uuid of this legacy producer
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the host ID of this legacy producer.
	 *
	 * @return the host ID of this legacy producer
	 */
	public long getHostId();

	/**
	 * Sets the host ID of this legacy producer.
	 *
	 * @param hostId the host ID of this legacy producer
	 */
	public void setHostId(long hostId);

	/**
	 * Returns the facility ID of this legacy producer.
	 *
	 * @return the facility ID of this legacy producer
	 */
	public long getFacilityId();

	/**
	 * Sets the facility ID of this legacy producer.
	 *
	 * @param facilityId the facility ID of this legacy producer
	 */
	public void setFacilityId(long facilityId);

	/**
	 * Returns the number of productions of this legacy producer.
	 *
	 * @return the number of productions of this legacy producer
	 */
	public long getNumberOfProductions();

	/**
	 * Sets the number of productions of this legacy producer.
	 *
	 * @param numberOfProductions the number of productions of this legacy producer
	 */
	public void setNumberOfProductions(long numberOfProductions);

	/**
	 * Returns the approved of this legacy producer.
	 *
	 * @return the approved of this legacy producer
	 */
	public int getApproved();

	/**
	 * Sets the approved of this legacy producer.
	 *
	 * @param approved the approved of this legacy producer
	 */
	public void setApproved(int approved);

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
		de.uhh.l2g.plugins.migration.model.LegacyProducer legacyProducer);

	@Override
	public int hashCode();

	@Override
	public CacheModel<de.uhh.l2g.plugins.migration.model.LegacyProducer> toCacheModel();

	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyProducer toEscapedModel();

	@Override
	public de.uhh.l2g.plugins.migration.model.LegacyProducer toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}