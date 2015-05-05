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

package de.uhh.l2g.plugins.model;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

/**
 * The base model interface for the Upload service. Represents a row in the &quot;LG_Upload&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link de.uhh.l2g.plugins.model.impl.UploadModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link de.uhh.l2g.plugins.model.impl.UploadImpl}.
 * </p>
 *
 * @author Iavor Sturm
 * @see Upload
 * @see de.uhh.l2g.plugins.model.impl.UploadImpl
 * @see de.uhh.l2g.plugins.model.impl.UploadModelImpl
 * @generated
 */
public interface UploadModel extends BaseModel<Upload> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a upload model instance should use the {@link Upload} interface instead.
	 */

	/**
	 * Returns the primary key of this upload.
	 *
	 * @return the primary key of this upload
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this upload.
	 *
	 * @param primaryKey the primary key of this upload
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the upload ID of this upload.
	 *
	 * @return the upload ID of this upload
	 */
	public long getUploadId();

	/**
	 * Sets the upload ID of this upload.
	 *
	 * @param uploadId the upload ID of this upload
	 */
	public void setUploadId(long uploadId);

	/**
	 * Returns the user ID of this upload.
	 *
	 * @return the user ID of this upload
	 */
	public long getUserId();

	/**
	 * Sets the user ID of this upload.
	 *
	 * @param userId the user ID of this upload
	 */
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this upload.
	 *
	 * @return the user uuid of this upload
	 * @throws SystemException if a system exception occurred
	 */
	public String getUserUuid() throws SystemException;

	/**
	 * Sets the user uuid of this upload.
	 *
	 * @param userUuid the user uuid of this upload
	 */
	public void setUserUuid(String userUuid);

	/**
	 * Returns the content length of this upload.
	 *
	 * @return the content length of this upload
	 */
	public long getContentLength();

	/**
	 * Sets the content length of this upload.
	 *
	 * @param contentLength the content length of this upload
	 */
	public void setContentLength(long contentLength);

	/**
	 * Returns the timestamp of this upload.
	 *
	 * @return the timestamp of this upload
	 */
	public long getTimestamp();

	/**
	 * Sets the timestamp of this upload.
	 *
	 * @param timestamp the timestamp of this upload
	 */
	public void setTimestamp(long timestamp);

	/**
	 * Returns the status of this upload.
	 *
	 * @return the status of this upload
	 */
	public int getStatus();

	/**
	 * Sets the status of this upload.
	 *
	 * @param status the status of this upload
	 */
	public void setStatus(int status);

	/**
	 * Returns the video ID of this upload.
	 *
	 * @return the video ID of this upload
	 */
	public long getVideoId();

	/**
	 * Sets the video ID of this upload.
	 *
	 * @param videoId the video ID of this upload
	 */
	public void setVideoId(long videoId);

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
	public int compareTo(Upload upload);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Upload> toCacheModel();

	@Override
	public Upload toEscapedModel();

	@Override
	public Upload toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}