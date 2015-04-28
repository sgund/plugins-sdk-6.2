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

package de.uhh.l2g.plugins.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import de.uhh.l2g.plugins.model.Category;
import de.uhh.l2g.plugins.model.CategoryModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Category service. Represents a row in the &quot;LG_Category&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link de.uhh.l2g.plugins.model.CategoryModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CategoryImpl}.
 * </p>
 *
 * @author Iavor Sturm
 * @see CategoryImpl
 * @see de.uhh.l2g.plugins.model.Category
 * @see de.uhh.l2g.plugins.model.CategoryModel
 * @generated
 */
public class CategoryModelImpl extends BaseModelImpl<Category>
	implements CategoryModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a category model instance should use the {@link de.uhh.l2g.plugins.model.Category} interface instead.
	 */
	public static final String TABLE_NAME = "LG_Category";
	public static final Object[][] TABLE_COLUMNS = {
			{ "categoryId", Types.BIGINT },
			{ "parentId", Types.BIGINT },
			{ "languageId", Types.VARCHAR },
			{ "name", Types.VARCHAR },
			{ "translation", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table LG_Category (categoryId LONG not null primary key,parentId LONG,languageId VARCHAR(75) null,name VARCHAR(75) null,translation VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table LG_Category";
	public static final String ORDER_BY_JPQL = " ORDER BY category.categoryId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY LG_Category.categoryId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.de.uhh.l2g.plugins.model.Category"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.de.uhh.l2g.plugins.model.Category"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.de.uhh.l2g.plugins.model.Category"),
			true);
	public static long NAME_COLUMN_BITMASK = 1L;
	public static long CATEGORYID_COLUMN_BITMASK = 2L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.de.uhh.l2g.plugins.model.Category"));

	public CategoryModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _categoryId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCategoryId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _categoryId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Category.class;
	}

	@Override
	public String getModelClassName() {
		return Category.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("categoryId", getCategoryId());
		attributes.put("parentId", getParentId());
		attributes.put("languageId", getLanguageId());
		attributes.put("name", getName());
		attributes.put("translation", getTranslation());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long categoryId = (Long)attributes.get("categoryId");

		if (categoryId != null) {
			setCategoryId(categoryId);
		}

		Long parentId = (Long)attributes.get("parentId");

		if (parentId != null) {
			setParentId(parentId);
		}

		String languageId = (String)attributes.get("languageId");

		if (languageId != null) {
			setLanguageId(languageId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String translation = (String)attributes.get("translation");

		if (translation != null) {
			setTranslation(translation);
		}
	}

	@Override
	public long getCategoryId() {
		return _categoryId;
	}

	@Override
	public void setCategoryId(long categoryId) {
		_categoryId = categoryId;
	}

	@Override
	public long getParentId() {
		return _parentId;
	}

	@Override
	public void setParentId(long parentId) {
		_parentId = parentId;
	}

	@Override
	public String getLanguageId() {
		if (_languageId == null) {
			return StringPool.BLANK;
		}
		else {
			return _languageId;
		}
	}

	@Override
	public void setLanguageId(String languageId) {
		_languageId = languageId;
	}

	@Override
	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	@Override
	public void setName(String name) {
		_columnBitmask |= NAME_COLUMN_BITMASK;

		if (_originalName == null) {
			_originalName = _name;
		}

		_name = name;
	}

	public String getOriginalName() {
		return GetterUtil.getString(_originalName);
	}

	@Override
	public String getTranslation() {
		if (_translation == null) {
			return StringPool.BLANK;
		}
		else {
			return _translation;
		}
	}

	@Override
	public void setTranslation(String translation) {
		_translation = translation;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Category.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Category toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Category)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CategoryImpl categoryImpl = new CategoryImpl();

		categoryImpl.setCategoryId(getCategoryId());
		categoryImpl.setParentId(getParentId());
		categoryImpl.setLanguageId(getLanguageId());
		categoryImpl.setName(getName());
		categoryImpl.setTranslation(getTranslation());

		categoryImpl.resetOriginalValues();

		return categoryImpl;
	}

	@Override
	public int compareTo(Category category) {
		long primaryKey = category.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof Category)) {
			return false;
		}

		Category category = (Category)obj;

		long primaryKey = category.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		CategoryModelImpl categoryModelImpl = this;

		categoryModelImpl._originalName = categoryModelImpl._name;

		categoryModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Category> toCacheModel() {
		CategoryCacheModel categoryCacheModel = new CategoryCacheModel();

		categoryCacheModel.categoryId = getCategoryId();

		categoryCacheModel.parentId = getParentId();

		categoryCacheModel.languageId = getLanguageId();

		String languageId = categoryCacheModel.languageId;

		if ((languageId != null) && (languageId.length() == 0)) {
			categoryCacheModel.languageId = null;
		}

		categoryCacheModel.name = getName();

		String name = categoryCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			categoryCacheModel.name = null;
		}

		categoryCacheModel.translation = getTranslation();

		String translation = categoryCacheModel.translation;

		if ((translation != null) && (translation.length() == 0)) {
			categoryCacheModel.translation = null;
		}

		return categoryCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{categoryId=");
		sb.append(getCategoryId());
		sb.append(", parentId=");
		sb.append(getParentId());
		sb.append(", languageId=");
		sb.append(getLanguageId());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", translation=");
		sb.append(getTranslation());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(19);

		sb.append("<model><model-name>");
		sb.append("de.uhh.l2g.plugins.model.Category");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>categoryId</column-name><column-value><![CDATA[");
		sb.append(getCategoryId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>parentId</column-name><column-value><![CDATA[");
		sb.append(getParentId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>languageId</column-name><column-value><![CDATA[");
		sb.append(getLanguageId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>translation</column-name><column-value><![CDATA[");
		sb.append(getTranslation());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Category.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			Category.class
		};
	private long _categoryId;
	private long _parentId;
	private String _languageId;
	private String _name;
	private String _originalName;
	private String _translation;
	private long _columnBitmask;
	private Category _escapedModel;
}