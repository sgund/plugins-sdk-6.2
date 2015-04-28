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

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the Tagcloud service. Represents a row in the &quot;LG_Tagcloud&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link de.uhh.l2g.plugins.model.impl.TagcloudModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link de.uhh.l2g.plugins.model.impl.TagcloudImpl}.
 * </p>
 *
 * @author Iavor Sturm
 * @see Tagcloud
 * @see de.uhh.l2g.plugins.model.impl.TagcloudImpl
 * @see de.uhh.l2g.plugins.model.impl.TagcloudModelImpl
 * @generated
 */
public interface TagcloudModel extends BaseModel<Tagcloud> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a tagcloud model instance should use the {@link Tagcloud} interface instead.
	 */

	/**
	 * Returns the primary key of this tagcloud.
	 *
	 * @return the primary key of this tagcloud
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this tagcloud.
	 *
	 * @param primaryKey the primary key of this tagcloud
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the tagcloud ID of this tagcloud.
	 *
	 * @return the tagcloud ID of this tagcloud
	 */
	public long getTagcloudId();

	/**
	 * Sets the tagcloud ID of this tagcloud.
	 *
	 * @param tagcloudId the tagcloud ID of this tagcloud
	 */
	public void setTagcloudId(long tagcloudId);

	/**
	 * Returns the object class type of this tagcloud.
	 *
	 * @return the object class type of this tagcloud
	 */
	@AutoEscape
	public String getObjectClassType();

	/**
	 * Sets the object class type of this tagcloud.
	 *
	 * @param objectClassType the object class type of this tagcloud
	 */
	public void setObjectClassType(String objectClassType);

	/**
	 * Returns the object ID of this tagcloud.
	 *
	 * @return the object ID of this tagcloud
	 */
	public long getObjectId();

	/**
	 * Sets the object ID of this tagcloud.
	 *
	 * @param objectId the object ID of this tagcloud
	 */
	public void setObjectId(long objectId);

	/**
	 * Returns the tags of this tagcloud.
	 *
	 * @return the tags of this tagcloud
	 */
	@AutoEscape
	public String getTags();

	/**
	 * Sets the tags of this tagcloud.
	 *
	 * @param tags the tags of this tagcloud
	 */
	public void setTags(String tags);

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
	public int compareTo(Tagcloud tagcloud);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Tagcloud> toCacheModel();

	@Override
	public Tagcloud toEscapedModel();

	@Override
	public Tagcloud toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}