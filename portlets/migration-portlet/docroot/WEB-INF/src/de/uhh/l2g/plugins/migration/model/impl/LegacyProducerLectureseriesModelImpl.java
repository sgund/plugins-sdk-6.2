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

package de.uhh.l2g.plugins.migration.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries;
import de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseriesModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the LegacyProducerLectureseries service. Represents a row in the &quot;producer_lectureseries&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseriesModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link LegacyProducerLectureseriesImpl}.
 * </p>
 *
 * @author unihh
 * @see LegacyProducerLectureseriesImpl
 * @see de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries
 * @see de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseriesModel
 * @generated
 */
public class LegacyProducerLectureseriesModelImpl extends BaseModelImpl<LegacyProducerLectureseries>
	implements LegacyProducerLectureseriesModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a legacy producer lectureseries model instance should use the {@link de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries} interface instead.
	 */
	public static final String TABLE_NAME = "producer_lectureseries";
	public static final Object[][] TABLE_COLUMNS = {
			{ "id_", Types.BIGINT },
			{ "producerId", Types.BIGINT },
			{ "lectureseriesId", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table producer_lectureseries (id_ LONG not null primary key,producerId LONG,lectureseriesId LONG)";
	public static final String TABLE_SQL_DROP = "drop table producer_lectureseries";
	public static final String ORDER_BY_JPQL = " ORDER BY legacyProducerLectureseries.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY producer_lectureseries.id_ ASC";
	public static final String DATA_SOURCE = "anotherDataSource";
	public static final String SESSION_FACTORY = "anotherSessionFactory";
	public static final String TX_MANAGER = "anotherTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = false;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries"));

	public LegacyProducerLectureseriesModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _id;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _id;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return LegacyProducerLectureseries.class;
	}

	@Override
	public String getModelClassName() {
		return LegacyProducerLectureseries.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("id", getId());
		attributes.put("producerId", getProducerId());
		attributes.put("lectureseriesId", getLectureseriesId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long id = (Long)attributes.get("id");

		if (id != null) {
			setId(id);
		}

		Long producerId = (Long)attributes.get("producerId");

		if (producerId != null) {
			setProducerId(producerId);
		}

		Long lectureseriesId = (Long)attributes.get("lectureseriesId");

		if (lectureseriesId != null) {
			setLectureseriesId(lectureseriesId);
		}
	}

	@Override
	public long getId() {
		return _id;
	}

	@Override
	public void setId(long id) {
		_id = id;
	}

	@Override
	public long getProducerId() {
		return _producerId;
	}

	@Override
	public void setProducerId(long producerId) {
		_producerId = producerId;
	}

	@Override
	public long getLectureseriesId() {
		return _lectureseriesId;
	}

	@Override
	public void setLectureseriesId(long lectureseriesId) {
		_lectureseriesId = lectureseriesId;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			LegacyProducerLectureseries.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public LegacyProducerLectureseries toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (LegacyProducerLectureseries)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		LegacyProducerLectureseriesImpl legacyProducerLectureseriesImpl = new LegacyProducerLectureseriesImpl();

		legacyProducerLectureseriesImpl.setId(getId());
		legacyProducerLectureseriesImpl.setProducerId(getProducerId());
		legacyProducerLectureseriesImpl.setLectureseriesId(getLectureseriesId());

		legacyProducerLectureseriesImpl.resetOriginalValues();

		return legacyProducerLectureseriesImpl;
	}

	@Override
	public int compareTo(
		LegacyProducerLectureseries legacyProducerLectureseries) {
		long primaryKey = legacyProducerLectureseries.getPrimaryKey();

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

		if (!(obj instanceof LegacyProducerLectureseries)) {
			return false;
		}

		LegacyProducerLectureseries legacyProducerLectureseries = (LegacyProducerLectureseries)obj;

		long primaryKey = legacyProducerLectureseries.getPrimaryKey();

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
	}

	@Override
	public CacheModel<LegacyProducerLectureseries> toCacheModel() {
		LegacyProducerLectureseriesCacheModel legacyProducerLectureseriesCacheModel =
			new LegacyProducerLectureseriesCacheModel();

		legacyProducerLectureseriesCacheModel.id = getId();

		legacyProducerLectureseriesCacheModel.producerId = getProducerId();

		legacyProducerLectureseriesCacheModel.lectureseriesId = getLectureseriesId();

		return legacyProducerLectureseriesCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{id=");
		sb.append(getId());
		sb.append(", producerId=");
		sb.append(getProducerId());
		sb.append(", lectureseriesId=");
		sb.append(getLectureseriesId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append(
			"de.uhh.l2g.plugins.migration.model.LegacyProducerLectureseries");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>id</column-name><column-value><![CDATA[");
		sb.append(getId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>producerId</column-name><column-value><![CDATA[");
		sb.append(getProducerId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>lectureseriesId</column-name><column-value><![CDATA[");
		sb.append(getLectureseriesId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = LegacyProducerLectureseries.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			LegacyProducerLectureseries.class
		};
	private long _id;
	private long _producerId;
	private long _lectureseriesId;
	private LegacyProducerLectureseries _escapedModel;
}