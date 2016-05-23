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

/**
 * The extended model implementation for the Lectureseries service. Represents a row in the &quot;LG_Lectureseries&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link de.uhh.l2g.plugins.model.Lectureseries} interface.
 * </p>
 *
 * @author Iavor Sturm
 */
public class LectureseriesImpl extends LectureseriesBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. All methods that expect a lectureseries model instance should use the {@link de.uhh.l2g.plugins.model.Lectureseries} interface instead.
	 */
	
	private String type;
	
	private int numberOfVideos = 0;
	private int numberOfOpenAccessVideos = 0;
	
	public int getNumberOfVideos() {
		return numberOfVideos;
	}

	public int getNumberOfOpenAccessVideos() {
		return numberOfOpenAccessVideos;
	}


	public void setNumberOfVideos(int numberOfVideos) {
		this.numberOfVideos = numberOfVideos;
	}
	
	public void setNumberOfOpenAccessVideos(int numberOfOpenAccessVideos) {
		this.numberOfOpenAccessVideos = numberOfOpenAccessVideos;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public LectureseriesImpl() {
	}
	
	
}