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
 * The base model interface for the Category service. Represents a row in the &quot;LG_Category&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link de.uhh.l2g.plugins.model.impl.CategoryModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link de.uhh.l2g.plugins.model.impl.CategoryImpl}.
 * </p>
 *
 * @author Iavor Sturm
 * @see Category
 * @see de.uhh.l2g.plugins.model.impl.CategoryImpl
 * @see de.uhh.l2g.plugins.model.impl.CategoryModelImpl
 * @generated
 */
public interface CategoryModel extends BaseModel<Category> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a category model instance should use the {@link Category} interface instead.
	 */

	/**
	 * Returns the primary key of this category.
	 *
	 * @return the primary key of this category
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this category.
	 *
	 * @param primaryKey the primary key of this category
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the category ID of this category.
	 *
	 * @return the category ID of this category
	 */
	public long getCategoryId();

	/**
	 * Sets the category ID of this category.
	 *
	 * @param categoryId the category ID of this category
	 */
	public void setCategoryId(long categoryId);

	/**
	 * Returns the parent ID of this category.
	 *
	 * @return the parent ID of this category
	 */
	public long getParentId();

	/**
	 * Sets the parent ID of this category.
	 *
	 * @param parentId the parent ID of this category
	 */
	public void setParentId(long parentId);

	/**
	 * Returns the language ID of this category.
	 *
	 * @return the language ID of this category
	 */
	@AutoEscape
	public String getLanguageId();

	/**
	 * Sets the language ID of this category.
	 *
	 * @param languageId the language ID of this category
	 */
	public void setLanguageId(String languageId);

	/**
	 * Returns the name of this category.
	 *
	 * @return the name of this category
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this category.
	 *
	 * @param name the name of this category
	 */
	public void setName(String name);

	/**
	 * Returns the translation of this category.
	 *
	 * @return the translation of this category
	 */
	@AutoEscape
	public String getTranslation();

	/**
	 * Sets the translation of this category.
	 *
	 * @param translation the translation of this category
	 */
	public void setTranslation(String translation);

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
	public int compareTo(Category category);

	@Override
	public int hashCode();

	@Override
	public CacheModel<Category> toCacheModel();

	@Override
	public Category toEscapedModel();

	@Override
	public Category toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}