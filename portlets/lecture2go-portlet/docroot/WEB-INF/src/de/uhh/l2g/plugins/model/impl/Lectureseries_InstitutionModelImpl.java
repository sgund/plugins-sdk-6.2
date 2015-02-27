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

package de.uhh.l2g.plugins.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import de.uhh.l2g.plugins.model.Lectureseries_Institution;
import de.uhh.l2g.plugins.model.Lectureseries_InstitutionModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Lectureseries_Institution service. Represents a row in the &quot;LG_Lectureseries_Institution&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link de.uhh.l2g.plugins.model.Lectureseries_InstitutionModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link Lectureseries_InstitutionImpl}.
 * </p>
 *
 * @author Iavor Sturm
 * @see Lectureseries_InstitutionImpl
 * @see de.uhh.l2g.plugins.model.Lectureseries_Institution
 * @see de.uhh.l2g.plugins.model.Lectureseries_InstitutionModel
 * @generated
 */
public class Lectureseries_InstitutionModelImpl extends BaseModelImpl<Lectureseries_Institution>
	implements Lectureseries_InstitutionModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a lectureseries_ institution model instance should use the {@link de.uhh.l2g.plugins.model.Lectureseries_Institution} interface instead.
	 */
	public static final String TABLE_NAME = "LG_Lectureseries_Institution";
	public static final Object[][] TABLE_COLUMNS = {
			{ "lectureseriesInstitutionId", Types.BIGINT },
			{ "lectureseriesId", Types.BIGINT },
			{ "institutionId", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table LG_Lectureseries_Institution (lectureseriesInstitutionId LONG not null primary key,lectureseriesId LONG,institutionId LONG)";
	public static final String TABLE_SQL_DROP = "drop table LG_Lectureseries_Institution";
	public static final String ORDER_BY_JPQL = " ORDER BY lectureseries_Institution.lectureseriesInstitutionId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY LG_Lectureseries_Institution.lectureseriesInstitutionId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.de.uhh.l2g.plugins.model.Lectureseries_Institution"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.de.uhh.l2g.plugins.model.Lectureseries_Institution"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.de.uhh.l2g.plugins.model.Lectureseries_Institution"),
			true);
	public static long INSTITUTIONID_COLUMN_BITMASK = 1L;
	public static long LECTURESERIESID_COLUMN_BITMASK = 2L;
	public static long LECTURESERIESINSTITUTIONID_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.de.uhh.l2g.plugins.model.Lectureseries_Institution"));

	public Lectureseries_InstitutionModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _lectureseriesInstitutionId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setLectureseriesInstitutionId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _lectureseriesInstitutionId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Lectureseries_Institution.class;
	}

	@Override
	public String getModelClassName() {
		return Lectureseries_Institution.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("lectureseriesInstitutionId",
			getLectureseriesInstitutionId());
		attributes.put("lectureseriesId", getLectureseriesId());
		attributes.put("institutionId", getInstitutionId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long lectureseriesInstitutionId = (Long)attributes.get(
				"lectureseriesInstitutionId");

		if (lectureseriesInstitutionId != null) {
			setLectureseriesInstitutionId(lectureseriesInstitutionId);
		}

		Long lectureseriesId = (Long)attributes.get("lectureseriesId");

		if (lectureseriesId != null) {
			setLectureseriesId(lectureseriesId);
		}

		Long institutionId = (Long)attributes.get("institutionId");

		if (institutionId != null) {
			setInstitutionId(institutionId);
		}
	}

	@Override
	public long getLectureseriesInstitutionId() {
		return _lectureseriesInstitutionId;
	}

	@Override
	public void setLectureseriesInstitutionId(long lectureseriesInstitutionId) {
		_lectureseriesInstitutionId = lectureseriesInstitutionId;
	}

	@Override
	public long getLectureseriesId() {
		return _lectureseriesId;
	}

	@Override
	public void setLectureseriesId(long lectureseriesId) {
		_columnBitmask |= LECTURESERIESID_COLUMN_BITMASK;

		if (!_setOriginalLectureseriesId) {
			_setOriginalLectureseriesId = true;

			_originalLectureseriesId = _lectureseriesId;
		}

		_lectureseriesId = lectureseriesId;
	}

	public long getOriginalLectureseriesId() {
		return _originalLectureseriesId;
	}

	@Override
	public long getInstitutionId() {
		return _institutionId;
	}

	@Override
	public void setInstitutionId(long institutionId) {
		_columnBitmask |= INSTITUTIONID_COLUMN_BITMASK;

		if (!_setOriginalInstitutionId) {
			_setOriginalInstitutionId = true;

			_originalInstitutionId = _institutionId;
		}

		_institutionId = institutionId;
	}

	public long getOriginalInstitutionId() {
		return _originalInstitutionId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Lectureseries_Institution.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Lectureseries_Institution toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Lectureseries_Institution)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		Lectureseries_InstitutionImpl lectureseries_InstitutionImpl = new Lectureseries_InstitutionImpl();

		lectureseries_InstitutionImpl.setLectureseriesInstitutionId(getLectureseriesInstitutionId());
		lectureseries_InstitutionImpl.setLectureseriesId(getLectureseriesId());
		lectureseries_InstitutionImpl.setInstitutionId(getInstitutionId());

		lectureseries_InstitutionImpl.resetOriginalValues();

		return lectureseries_InstitutionImpl;
	}

	@Override
	public int compareTo(Lectureseries_Institution lectureseries_Institution) {
		long primaryKey = lectureseries_Institution.getPrimaryKey();

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

		if (!(obj instanceof Lectureseries_Institution)) {
			return false;
		}

		Lectureseries_Institution lectureseries_Institution = (Lectureseries_Institution)obj;

		long primaryKey = lectureseries_Institution.getPrimaryKey();

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
		Lectureseries_InstitutionModelImpl lectureseries_InstitutionModelImpl = this;

		lectureseries_InstitutionModelImpl._originalLectureseriesId = lectureseries_InstitutionModelImpl._lectureseriesId;

		lectureseries_InstitutionModelImpl._setOriginalLectureseriesId = false;

		lectureseries_InstitutionModelImpl._originalInstitutionId = lectureseries_InstitutionModelImpl._institutionId;

		lectureseries_InstitutionModelImpl._setOriginalInstitutionId = false;

		lectureseries_InstitutionModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Lectureseries_Institution> toCacheModel() {
		Lectureseries_InstitutionCacheModel lectureseries_InstitutionCacheModel = new Lectureseries_InstitutionCacheModel();

		lectureseries_InstitutionCacheModel.lectureseriesInstitutionId = getLectureseriesInstitutionId();

		lectureseries_InstitutionCacheModel.lectureseriesId = getLectureseriesId();

		lectureseries_InstitutionCacheModel.institutionId = getInstitutionId();

		return lectureseries_InstitutionCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{lectureseriesInstitutionId=");
		sb.append(getLectureseriesInstitutionId());
		sb.append(", lectureseriesId=");
		sb.append(getLectureseriesId());
		sb.append(", institutionId=");
		sb.append(getInstitutionId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("de.uhh.l2g.plugins.model.Lectureseries_Institution");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>lectureseriesInstitutionId</column-name><column-value><![CDATA[");
		sb.append(getLectureseriesInstitutionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lectureseriesId</column-name><column-value><![CDATA[");
		sb.append(getLectureseriesId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>institutionId</column-name><column-value><![CDATA[");
		sb.append(getInstitutionId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Lectureseries_Institution.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			Lectureseries_Institution.class
		};
	private long _lectureseriesInstitutionId;
	private long _lectureseriesId;
	private long _originalLectureseriesId;
	private boolean _setOriginalLectureseriesId;
	private long _institutionId;
	private long _originalInstitutionId;
	private boolean _setOriginalInstitutionId;
	private long _columnBitmask;
	private Lectureseries_Institution _escapedModel;
}