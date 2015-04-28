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
 * The base model interface for the Video_Category service. Represents a row in the &quot;LG_Video_Category&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link de.uhh.l2g.plugins.model.impl.Video_CategoryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link de.uhh.l2g.plugins.model.impl.Video_CategoryImpl}.
 * </p>
 *
 * @author Iavor Sturm
 * @see Video_Category
 * @see de.uhh.l2g.plugins.model.impl.Video_CategoryImpl
 * @see de.uhh.l2g.plugins.model.impl.Video_CategoryModelImpl
 * @generated
 */
public interface Video_CategoryModel extends BaseModel<Video_Category> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a video_ category model instance should use the {@link Video_Category} interface instead.
	 */

	/**
	 * Returns the primary key of this video_ category.
	 *
	 * @return the primary key of this video_ category
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this video_ category.
	 *
	 * @param primaryKey the primary key of this video_ category
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the video category ID of this video_ category.
	 *
	 * @return the video category ID of this video_ category
	 */
	public long getVideoCategoryId();

	/**
	 * Sets the video category ID of this video_ category.
	 *
	 * @param videoCategoryId the video category ID of this video_ category
	 */
	public void setVideoCategoryId(long videoCategoryId);

	/**
	 * Returns the video ID of this video_ category.
	 *
	 * @return the video ID of this video_ category
	 */
	public long getVideoId();

	/**
	 * Sets the video ID of this video_ category.
	 *
	 * @param videoId the video ID of this video_ category
	 */
	public void setVideoId(long videoId);

	/**
	 * Returns the category ID of this video_ category.
	 *
	 * @return the category ID of this video_ category
	 */
	public long getCategoryId();

	/**
	 * Sets the category ID of this video_ category.
	 *
	 * @param categoryId the category ID of this video_ category
	 */
	public void setCategoryId(long categoryId);

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
	public int compareTo(Video_Category video_Category);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Video_Category> toCacheModel();

	@Override
	public Video_Category toEscapedModel();

	@Override
	public Video_Category toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}