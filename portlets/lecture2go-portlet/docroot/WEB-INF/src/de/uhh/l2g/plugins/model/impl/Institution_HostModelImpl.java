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
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import de.uhh.l2g.plugins.model.Institution_Host;
import de.uhh.l2g.plugins.model.Institution_HostModel;

import java.io.Serializable;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * The base model implementation for the Institution_Host service. Represents a row in the &quot;LG_Institution_Host&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link de.uhh.l2g.plugins.model.Institution_HostModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link Institution_HostImpl}.
 * </p>
 *
 * @author Iavor Sturm
 * @see Institution_HostImpl
 * @see de.uhh.l2g.plugins.model.Institution_Host
 * @see de.uhh.l2g.plugins.model.Institution_HostModel
 * @generated
 */
public class Institution_HostModelImpl extends BaseModelImpl<Institution_Host>
	implements Institution_HostModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a institution_ host model instance should use the {@link de.uhh.l2g.plugins.model.Institution_Host} interface instead.
	 */
	public static final String TABLE_NAME = "LG_Institution_Host";
	public static final Object[][] TABLE_COLUMNS = {
			{ "institutionHostId", Types.BIGINT },
			{ "institutionId", Types.BIGINT },
			{ "hostId", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table LG_Institution_Host (institutionHostId LONG not null primary key,institutionId LONG,hostId LONG)";
	public static final String TABLE_SQL_DROP = "drop table LG_Institution_Host";
	public static final String ORDER_BY_JPQL = " ORDER BY institution_Host.institutionHostId ASC";
	public static final String ORDER_BY_SQL = " ORDER BY LG_Institution_Host.institutionHostId ASC";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.de.uhh.l2g.plugins.model.Institution_Host"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.de.uhh.l2g.plugins.model.Institution_Host"),
			true);
	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.column.bitmask.enabled.de.uhh.l2g.plugins.model.Institution_Host"),
			true);
	public static long HOSTID_COLUMN_BITMASK = 1L;
	public static long INSTITUTIONID_COLUMN_BITMASK = 2L;
	public static long INSTITUTIONHOSTID_COLUMN_BITMASK = 4L;
	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.de.uhh.l2g.plugins.model.Institution_Host"));

	public Institution_HostModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _institutionHostId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setInstitutionHostId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _institutionHostId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return Institution_Host.class;
	}

	@Override
	public String getModelClassName() {
		return Institution_Host.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("institutionHostId", getInstitutionHostId());
		attributes.put("institutionId", getInstitutionId());
		attributes.put("hostId", getHostId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long institutionHostId = (Long)attributes.get("institutionHostId");

		if (institutionHostId != null) {
			setInstitutionHostId(institutionHostId);
		}

		Long institutionId = (Long)attributes.get("institutionId");

		if (institutionId != null) {
			setInstitutionId(institutionId);
		}

		Long hostId = (Long)attributes.get("hostId");

		if (hostId != null) {
			setHostId(hostId);
		}
	}

	@Override
	public long getInstitutionHostId() {
		return _institutionHostId;
	}

	@Override
	public void setInstitutionHostId(long institutionHostId) {
		_institutionHostId = institutionHostId;
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

	@Override
	public long getHostId() {
		return _hostId;
	}

	@Override
	public void setHostId(long hostId) {
		_columnBitmask |= HOSTID_COLUMN_BITMASK;

		if (!_setOriginalHostId) {
			_setOriginalHostId = true;

			_originalHostId = _hostId;
		}

		_hostId = hostId;
	}

	public long getOriginalHostId() {
		return _originalHostId;
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(0,
			Institution_Host.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public Institution_Host toEscapedModel() {
		if (_escapedModel == null) {
			_escapedModel = (Institution_Host)ProxyUtil.newProxyInstance(_classLoader,
					_escapedModelInterfaces, new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		Institution_HostImpl institution_HostImpl = new Institution_HostImpl();

		institution_HostImpl.setInstitutionHostId(getInstitutionHostId());
		institution_HostImpl.setInstitutionId(getInstitutionId());
		institution_HostImpl.setHostId(getHostId());

		institution_HostImpl.resetOriginalValues();

		return institution_HostImpl;
	}

	@Override
	public int compareTo(Institution_Host institution_Host) {
		long primaryKey = institution_Host.getPrimaryKey();

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

		if (!(obj instanceof Institution_Host)) {
			return false;
		}

		Institution_Host institution_Host = (Institution_Host)obj;

		long primaryKey = institution_Host.getPrimaryKey();

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
		Institution_HostModelImpl institution_HostModelImpl = this;

		institution_HostModelImpl._originalInstitutionId = institution_HostModelImpl._institutionId;

		institution_HostModelImpl._setOriginalInstitutionId = false;

		institution_HostModelImpl._originalHostId = institution_HostModelImpl._hostId;

		institution_HostModelImpl._setOriginalHostId = false;

		institution_HostModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<Institution_Host> toCacheModel() {
		Institution_HostCacheModel institution_HostCacheModel = new Institution_HostCacheModel();

		institution_HostCacheModel.institutionHostId = getInstitutionHostId();

		institution_HostCacheModel.institutionId = getInstitutionId();

		institution_HostCacheModel.hostId = getHostId();

		return institution_HostCacheModel;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{institutionHostId=");
		sb.append(getInstitutionHostId());
		sb.append(", institutionId=");
		sb.append(getInstitutionId());
		sb.append(", hostId=");
		sb.append(getHostId());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(13);

		sb.append("<model><model-name>");
		sb.append("de.uhh.l2g.plugins.model.Institution_Host");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>institutionHostId</column-name><column-value><![CDATA[");
		sb.append(getInstitutionHostId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>institutionId</column-name><column-value><![CDATA[");
		sb.append(getInstitutionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>hostId</column-name><column-value><![CDATA[");
		sb.append(getHostId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Institution_Host.class.getClassLoader();
	private static Class<?>[] _escapedModelInterfaces = new Class[] {
			Institution_Host.class
		};
	private long _institutionHostId;
	private long _institutionId;
	private long _originalInstitutionId;
	private boolean _setOriginalInstitutionId;
	private long _hostId;
	private long _originalHostId;
	private boolean _setOriginalHostId;
	private long _columnBitmask;
	private Institution_Host _escapedModel;
}