package de.uhh.l2g.plugins.util;

/***************************************************************************
 * The Lecture2Go software is based on the liferay portal 6.1.1
 * <http://www.liferay.com>
 *
 * Lecture2Go <http://lecture2go.uni-hamburg.de> is an open source
 * platform for media management and distribution. Our goal is to
 * support the free access to knowledge because this is a component
 * of each democratic society. The open source software is aimed at
 * academic institutions and has to strengthen the blended learning.
 *
 * All Lecture2Go plugins are continuously being developed and improved.
 * For more details please visit <http://lecture2go-open-source.rrz.uni-hamburg.de>
 *
 * @Autor Lecture2Go Team
 * @Version 1.0
 * @Contact lecture2go-open-source@uni-hamburg.de
 *
 * Copyright (c) 2013 University of Hamburg / Computer and Data Center (RRZ)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ***************************************************************************/
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PropsUtil;

import de.uhh.l2g.plugins.model.Host;
import de.uhh.l2g.plugins.model.Lectureseries;
import de.uhh.l2g.plugins.model.Metadata;
import de.uhh.l2g.plugins.model.Producer;
import de.uhh.l2g.plugins.model.Segment;
import de.uhh.l2g.plugins.model.Video;
import de.uhh.l2g.plugins.model.impl.HostImpl;
import de.uhh.l2g.plugins.model.impl.LectureseriesImpl;
import de.uhh.l2g.plugins.model.impl.MetadataImpl;
import de.uhh.l2g.plugins.model.impl.ProducerImpl;
import de.uhh.l2g.plugins.service.HostLocalServiceUtil;
import de.uhh.l2g.plugins.service.LastvideolistLocalServiceUtil;
import de.uhh.l2g.plugins.service.LectureseriesLocalServiceUtil;
import de.uhh.l2g.plugins.service.LicenseLocalServiceUtil;
import de.uhh.l2g.plugins.service.MetadataLocalServiceUtil;
import de.uhh.l2g.plugins.service.ProducerLocalServiceUtil;
import de.uhh.l2g.plugins.service.SegmentLocalServiceUtil;
import de.uhh.l2g.plugins.service.TagcloudLocalServiceUtil;
import de.uhh.l2g.plugins.service.VideoLocalServiceUtil;
import de.uhh.l2g.plugins.service.Video_CategoryLocalServiceUtil;
import de.uhh.l2g.plugins.service.Video_CreatorLocalServiceUtil;
import de.uhh.l2g.plugins.service.Video_InstitutionLocalServiceUtil;
import de.uhh.l2g.plugins.service.Video_LectureseriesLocalServiceUtil;
import de.uhh.l2g.plugins.service.VideohitlistLocalServiceUtil;

public class ProzessManager {
	public final String[] MEDIA_FORMATS = { "pdf","mp3","m4v","mp4","m4a","ogg","flv","webm" };   
	String[] THUMBNAIL_SUFFIX = { ".jpg","_m.jpg","_s.jpg" };   

	Htaccess HTACCESS = new Htaccess();
	
	@SuppressWarnings("static-access")
	public void deactivateDownload(Video video) throws SystemException, PortalException {
		Host host = HostLocalServiceUtil.getHost(video.getHostId());
		Producer producer = ProducerLocalServiceUtil.getProducer(video.getProducerId());
		
		video.setDownloadLink(0);
		VideoLocalServiceUtil.updateVideo(video);
		// generate RSS
		for (String f: MEDIA_FORMATS) {           
			generateRSS(video, f);
		}
		String url = PropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/";
		HTACCESS.makeHtaccess(url, VideoLocalServiceUtil.getByProducerAndDownloadLink(producer.getProducerId(), 0));
	}

	@SuppressWarnings("static-access")
	public void activateDownload(Video video) throws SystemException, PortalException {
		Host host = HostLocalServiceUtil.getHost(video.getHostId());
		Producer producer = ProducerLocalServiceUtil.getProducer(video.getProducerId());
		
		video.setDownloadLink(1);
		VideoLocalServiceUtil.updateVideo(video);
		// generate RSS
		for (String f: MEDIA_FORMATS) {           
			generateRSS(video, f);
		}
		String url = PropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/";
		HTACCESS.makeHtaccess(url, VideoLocalServiceUtil.getByProducerAndDownloadLink(producer.getProducerId(), 0));
	}

	@SuppressWarnings("static-access")
	public void activateOpenaccess(Video video) throws SystemException, PortalException {
		Host host = HostLocalServiceUtil.getHost(video.getHostId());
		Producer producer = ProducerLocalServiceUtil.getProducer(video.getProducerId());
		Lectureseries lectureseries = new LectureseriesImpl();
		try{
			lectureseries= LectureseriesLocalServiceUtil.getLectureseries(video.getLectureseriesId());
		}catch(Exception e){}

		// first rename the file from the filesystem first
		String path = PropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir();
		String videoPreffix = video.getPreffix();
		String videoSPreffix = video.getSPreffix();
		try {
			// then update the filesystem
			//default media
			File fMp3 = new File(path + "/" + videoSPreffix + ".mp3");
			File fMp4 = new File(path + "/" + videoSPreffix + ".mp4");

			// if mp4 or mp3 exists
			if (fMp4.isFile() || fMp3.isFile()) {
				//for images
				for (String s: THUMBNAIL_SUFFIX) {
					File imgFile = new File(PropsUtil.get("lecture2go.images.system.path") + "/" + videoSPreffix + s);
					imgFile.renameTo(new File(PropsUtil.get("lecture2go.images.system.path") + "/" + videoPreffix + s));
				}
				//for av media
				for (String f: MEDIA_FORMATS) {           
					File file = new File(path + "/" + videoSPreffix + "."+f);
					File newFile = new File(path + "/" + videoPreffix + "."+f);
					file.renameTo(newFile);
				}
				// then update the video in the database
				video.setOpenAccess(1);
				video.setSecureFilename("");
				VideoLocalServiceUtil.updateVideo(video);
			}
		} catch (Exception e) {}
		// generate RSS
		for (String f: MEDIA_FORMATS) {           
			generateRSS(video, f);
		}	
		//
		String url = PropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/";
		HTACCESS.makeHtaccess(url, VideoLocalServiceUtil.getByProducerAndDownloadLink(producer.getProducerId(), 0));
		// refresh last video list
		VideoLocalServiceUtil.createLastVideoList();
		// refresh open acces for lecture series
		LectureseriesLocalServiceUtil.updateOpenAccess(video, lectureseries); 
	}

	@SuppressWarnings("static-access")
	public void deactivateOpenaccess(Video video) throws PortalException, SystemException {
		Host host = HostLocalServiceUtil.getHost(video.getHostId());
		Producer producer = ProducerLocalServiceUtil.getProducer(video.getProducerId());
		Lectureseries lectureseries = new LectureseriesImpl();
		try{
			lectureseries= LectureseriesLocalServiceUtil.getLectureseries(video.getLectureseriesId());
		}catch(Exception e){}
		
		// then update the filesystem
		String path = PropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir();
		String videoPreffix = video.getPreffix();
		
		//default media
		File fMp3 = new File(path + "/" + videoPreffix + ".mp3");
		File fMp4 = new File(path + "/" + videoPreffix + ".mp4");
		//
		if (fMp4.isFile() || fMp3.isFile()) {
			// first update the video in the database and, set openaccess
			video.setOpenAccess(0);
			//generate secure file name
			String secureUrl = Security.createSecureFileName() + "." + video.getContainerFormat();
			video.setSecureFilename(secureUrl);
			VideoLocalServiceUtil.updateVideo(video);
		
			String videoSPreffix = video.getSPreffix();
			//for images
			for (String s: THUMBNAIL_SUFFIX) {
				File imgFile = new File(PropsUtil.get("lecture2go.images.system.path") + "/" + videoPreffix + s);
				imgFile.renameTo(new File(PropsUtil.get("lecture2go.images.system.path") + "/" + videoSPreffix + s));
			}
			//for av media
			for (String f: MEDIA_FORMATS) {           
				File file = new File(path + "/" + videoPreffix + "."+f);
				File newFile = new File(path + "/" + videoSPreffix + "."+f);
				file.renameTo(newFile);
			}
		}
		// delete all symbolic links
		for (String f: MEDIA_FORMATS) {           
			File symLink = new File(PropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + video.getPreffix() + "."+f);
			symLink.delete();
		}
		// generate RSS
		for (String f: MEDIA_FORMATS) {           
			generateRSS(video, f);
		}
		// delete video from videohitlist
		VideohitlistLocalServiceUtil.deleteByVideoId(video.getVideoId());
		
		String url = PropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/";
		HTACCESS.makeHtaccess(url, VideoLocalServiceUtil.getByProducerAndDownloadLink(producer.getProducerId(), 0));
		
		// refresh last video list
		VideoLocalServiceUtil.createLastVideoList();
		
		// refresh open access for lecture series
		LectureseriesLocalServiceUtil.updateOpenAccess(video, lectureseries); 
	}

	public void deleteThumbnails(Video video) {
			String videoPreffix = "";
			if (video.getOpenAccess()==1) videoPreffix = video.getPreffix();
			else videoPreffix = video.getSPreffix();
			
			//delete
			for (String s: THUMBNAIL_SUFFIX) {
				File file = new File(PropsUtil.get("lecture2go.images.system.path") + "/" + videoPreffix + s);
				file.delete();
			}
	}

	@SuppressWarnings("static-access")
	public boolean deleteVideo(Video video) throws PortalException, SystemException{
		Host host = new HostImpl();
		Lectureseries lectureseries = new LectureseriesImpl();
		
		try {
			lectureseries = LectureseriesLocalServiceUtil.getLectureseries(video.getLectureseriesId());
		} catch (Exception e3) {
			e3.printStackTrace();
		}

		try {
			host = HostLocalServiceUtil.getHost(video.getHostId());
		} catch (PortalException e1) {
			e1.printStackTrace();
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		
		Producer producer = new ProducerImpl();
		try {
			producer = ProducerLocalServiceUtil.getProducer(video.getProducerId());
		} catch (PortalException e1) {
			e1.printStackTrace();
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		
		Metadata metadata = new MetadataImpl();
		try {
			metadata = MetadataLocalServiceUtil.getMetadata(video.getMetadataId());
		} catch (PortalException e1) {
			e1.printStackTrace();
		} catch (SystemException e1) {
			e1.printStackTrace();
		}

		// delete video_lectureseries
		Video_LectureseriesLocalServiceUtil.removeByVideoId(video.getVideoId());
				
		// delete video_institution
		Video_InstitutionLocalServiceUtil.removeByVideoId(video.getVideoId());
				
		// delete all segment images from repository location
		try{
			List<Segment> segmentList = SegmentLocalServiceUtil.getSegmentsByVideoId(video.getVideoId());
			SegmentLocalServiceUtil.deleteThumbhailsFromSegments(segmentList);
		}catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		
		//and all segments
		try {
			SegmentLocalServiceUtil.deleteByVideoId(video.getVideoId());
		} catch (SystemException e2) {
			e2.printStackTrace();
		}
		
		LicenseLocalServiceUtil.deleteByVideoId(video.getVideoId());

		// delete video from videohitlist
		try {
			VideohitlistLocalServiceUtil.deleteByVideoId(video.getVideoId());
		} catch (SystemException e2) {
			e2.printStackTrace();
		}
		
		// delete meta data which belongs to video 
		try {
			MetadataLocalServiceUtil.deleteMetadata(metadata);
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		
		// delete video itself
		try {
			VideoLocalServiceUtil.deleteVideo(video);
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		
		//delete physical files 
		String videoPreffix = "";
		if (video.getOpenAccess()==1) videoPreffix = video.getPreffix();
		else videoPreffix = video.getSPreffix();
		
		// delete all video contents
		if (video.getFilename() != null) {
			for (String f: MEDIA_FORMATS) {           
				//all media
				File file = new File(PropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/" + videoPreffix + "." + f);
				file.delete();
				//all symbolic links
				File symLink = new File(PropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + videoPreffix + "." + f);
				symLink.delete();
			}
			//all thumn nails
			deleteThumbnails(video);
		}
		
		// generate RSS
		for (String f: MEDIA_FORMATS) {           
			generateRSS(video, f);
		}
		
		// update last video list
		LastvideolistLocalServiceUtil.deleteByVideoId(video.getVideoId());
		
		// update uploads for producer
		int n = 0;
		n = VideoLocalServiceUtil.getByProducer(producer.getProducerId()).size();
		producer.setNumberOfProductions(n);
		ProducerLocalServiceUtil.updateProducer(producer);
		
		//Update htaccess
		String url = PropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/";
		HTACCESS.makeHtaccess(url, VideoLocalServiceUtil.getByProducerAndDownloadLink(producer.getProducerId(), 0));
		
		// refresh open access for lecture series
		if(lectureseries.getLectureseriesId()>0)LectureseriesLocalServiceUtil.updateOpenAccess(video, lectureseries); 
		
		//delete creators from video
		Video_CreatorLocalServiceUtil.deleteByVideoId(video.getVideoId());
		
		//delete tag cloud for this video
		TagcloudLocalServiceUtil.deleteByObjectId(video.getVideoId());
		
		//delete all categories for video
		Video_CategoryLocalServiceUtil.removeByVideo(video.getVideoId());
		return true;
	}
	
	@SuppressWarnings("static-access")
	public boolean deleteFilesImagesFromVideo(Video video){
		Host host = new HostImpl();
		try {
			host = HostLocalServiceUtil.getHost(video.getHostId());
		} catch (PortalException e1) {
			e1.printStackTrace();
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		
		Producer producer = new ProducerImpl();
		try {
			producer = ProducerLocalServiceUtil.getProducer(video.getProducerId());
		} catch (PortalException e1) {
			//e1.printStackTrace();
		} catch (SystemException e1) {
			//e1.printStackTrace();
		}
		
		// delete all segments and images from repository location
		try{
			List<Segment> segmentList = SegmentLocalServiceUtil.getSegmentsByVideoId(video.getVideoId());
			SegmentLocalServiceUtil.deleteThumbhailsFromSegments(segmentList);
			
		}catch (PortalException e) {
			e.printStackTrace();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		//and all segments
		try {
			SegmentLocalServiceUtil.deleteByVideoId(video.getVideoId());
		} catch (SystemException e2) {
			e2.printStackTrace();
		}
		
		//delete physical files 
		String videoPreffix = "";
		if (video.getOpenAccess()==1) videoPreffix = video.getPreffix();
		else videoPreffix = video.getSPreffix();
		
		for (String f: MEDIA_FORMATS) {           
			//all media
			File file = new File(PropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/" + videoPreffix + "." + f);
			file.delete();
			//all symbolic links
			File symLink = new File(PropsUtil.get("lecture2go.media.repository") + "/" + "abo" + "/" + videoPreffix + "." + f);
			symLink.delete();
		}
		//all thumn nails
		deleteThumbnails(video);
		
		// generate RSS
		for (String f: MEDIA_FORMATS) {           
			generateRSS(video, f);
		}
		
		//Update htaccess
		String url = PropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/";
		try {
			HTACCESS.makeHtaccess(url, VideoLocalServiceUtil.getByProducerAndDownloadLink(producer.getProducerId(), 0));
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		// set empty parameter to video itself
		try {
			video.setFilename("");
			video.setContainerFormat("");
			video.setDate("");
			video.setGenerationDate("");
			video.setSecureFilename(Security.createSecureFileName()+".xx");
			video.setUploadDate(null);
			video.setDownloadLink(0);
			video.setOpenAccess(0);
			video.setResolution("");
			video.setDuration("");
			video.setFileSize("");
			VideoLocalServiceUtil.updateVideo(video);
		} catch (SystemException e1) {
			e1.printStackTrace();
		}
		return true;
	}

	public void generateRSS(Video video, String type) {
		Long lsId = video.getLectureseriesId();
		// RSS generate for this lecture
		if(lsId>=0){
			RSSManager rssMan = new RSSManager();
			String feedName = "";
			String title = "";
			try{
				title = LectureseriesLocalServiceUtil.getLectureseries(lsId).getName();
			}catch(Exception e){}
			
			rssMan.setTitle(title);
			try {
				List<Video> videoList = VideoLocalServiceUtil.getByLectureseriesAndOpenaccess(lsId, 1);
				
				for (String f: MEDIA_FORMATS) {           
					if (type.equals(f)) feedName = "" + lsId + "."+f+".xml";
					rssMan.setRssFilename(feedName);
					if (type.equals(f))	rssMan.createRssFile(videoList, f);
				}
				
			} catch (Exception e) {
				try {
					for (String f: MEDIA_FORMATS) { 
						if (type.equals(f))	rssMan.createRssFile(null, f);
					}
				} catch (IOException ie) {
				} catch (PortalException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				} catch (SystemException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
				}
			}
		}
		// RSS end
	}

	public void addNewMediaDirectoryForProducer(Host host, Producer producer) throws IOException {
		File folder = new File(PropsUtil.get("lecture2go.media.repository") + "/" + host.getServerRoot() + "/" + producer.getHomeDir() + "/");
		if (!folder.exists()) {
			if (folder.mkdir()) {
				Runtime runtime = Runtime.getRuntime();
				String[] cmdArray = { PropsUtil.get("lecture2go.shell.bin"), "-c", "chown nobody " + folder.getAbsolutePath() };
				runtime.exec(cmdArray);
				String[] cmdArray1 = { PropsUtil.get("lecture2go.shell.bin"), "-c", "chown nobody:nobody " + folder.getAbsolutePath() };
				runtime.exec(cmdArray1);
				String[] cmdArray2 = { PropsUtil.get("lecture2go.shell.bin"), "-c", "chmod 701 " + folder.getAbsolutePath() };
				runtime.exec(cmdArray2);
				File prodFolder = new File(PropsUtil.get("lecture2go.httpstreaming.video.repository") + "/" + producer.getInstitutionId() + "l2g" + producer.getHomeDir());
				if (!prodFolder.exists()) {
					String cmd = "ln -s " + folder.getAbsolutePath() + " " + prodFolder.getAbsolutePath();
					runtime.exec(cmd);
				}
			}
		}
	}
}