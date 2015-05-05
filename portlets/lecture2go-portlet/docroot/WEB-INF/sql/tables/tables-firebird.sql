create table LG_Category (
	categoryId int64 not null primary key,
	parentId int64,
	languageId varchar(75),
	name varchar(75),
	translation varchar(75)
);

create table LG_Coordinator (
	coordinatorId int64 not null primary key,
	institutionId int64,
	officeId int64
);

create table LG_Host (
	hostId int64 not null primary key,
	protocol varchar(75),
	streamer varchar(75),
	port integer,
	serverRoot varchar(75),
	name varchar(75),
	serverTemplate varchar(75)
);

create table LG_Institution (
	institutionId int64 not null primary key,
	parentId int64,
	name varchar(75),
	typ varchar(75),
	www varchar(75),
	level integer,
	sort integer
);

create table LG_Institution_Host (
	institutionHostId int64 not null primary key,
	institutionId int64,
	hostId int64
);

create table LG_Lastvideolist (
	lastvideolistId integer not null primary key,
	videoId int64
);

create table LG_Lectureseries (
	number_ varchar(75),
	eventType varchar(75),
	eventCategory varchar(75),
	name varchar(75),
	shortDesc varchar(75),
	yearId int64,
	language varchar(75),
	facultyName varchar(75),
	instructorsString varchar(75),
	lectureseriesId int64 not null primary key,
	password_ varchar(75),
	approved integer,
	longDesc varchar(75),
	latestOpenAccessVideoId int64,
	latestVideoUploadDate timestamp,
	latestVideoGenerationDate varchar(75)
);

create table LG_Lectureseries_Category (
	lectureseriesCategoryId int64 not null primary key,
	categoryId int64,
	lectureseriesId int64
);

create table LG_Lectureseries_Institution (
	lectureseriesInstitutionId int64 not null primary key,
	lectureseriesId int64,
	institutionId int64
);

create table LG_License (
	licenseId int64 not null primary key,
	videoId int64,
	ccby integer,
	ccbybc integer,
	ccbyncnd integer,
	ccbyncsa integer,
	ccbysa integer,
	ccbync integer,
	l2go integer
);

create table LG_Metadata (
	metadataId int64 not null primary key,
	URLID varchar(75),
	format varchar(75),
	type_ varchar(75),
	language varchar(75),
	title varchar(75),
	subject varchar(75),
	coverage varchar(75),
	description varchar(75),
	creator varchar(75),
	publisher varchar(75),
	contributor varchar(75),
	rightsHolder varchar(75),
	rights varchar(75),
	provenance varchar(75),
	source varchar(75),
	relation varchar(75),
	audience varchar(75),
	instructionalMethods varchar(75),
	date_ timestamp
);

create table LG_Office (
	officeId int64 not null primary key,
	name varchar(75),
	www varchar(75),
	email varchar(75),
	institutionId int64
);

create table LG_Producer (
	producerId int64 not null primary key,
	idNum varchar(75),
	homeDir varchar(75),
	hostId int64,
	institutionId int64,
	numberOfProductions int64,
	approved integer
);

create table LG_Producer_Lectureseries (
	producerLectureseriesId int64 not null primary key,
	lectureseriesId int64,
	producerId int64
);

create table LG_Segment (
	segmentId int64 not null primary key,
	videoId int64,
	start_ varchar(75),
	title varchar(75),
	description varchar(75),
	end_ varchar(75),
	chapter integer,
	userId int64
);

create table LG_Segment_User_Video (
	segmentId int64,
	userId int64,
	videoId int64,
	segmentUserVideoId int64 not null primary key
);

create table LG_Sys (
	sysId integer not null primary key,
	version varchar(75),
	setupWizard integer
);

create table LG_TestEntity (
	testEntityId int64 not null primary key,
	groupId int64,
	companyId int64,
	userId int64,
	userName varchar(75),
	createDate timestamp,
	modifiedDate timestamp,
	testField varchar(75)
);

create table LG_Upload (
	uploadId int64 not null primary key,
	userId int64,
	contentLength int64,
	timestamp int64,
	status integer,
	videoId int64
);

create table LG_Video (
	videoId int64 not null primary key,
	title varchar(75),
	tags varchar(75),
	lectureseriesId int64,
	producerId int64,
	containerFormat varchar(75),
	filename varchar(75),
	resolution varchar(75),
	duration varchar(75),
	hostId int64,
	fileSize varchar(75),
	generationDate varchar(75),
	openAccess integer,
	downloadLink integer,
	metadataId int64,
	surl varchar(75),
	hits int64,
	uploadDate timestamp,
	permittedToSegment integer,
	rootInstitutionId int64,
	citation2go integer,
	yearId int64
);

create table LG_Video_Category (
	videoCategoryId int64 not null primary key,
	videoId int64,
	categoryId int64
);

create table LG_Video_Institution (
	videoInstitutionId int64 not null primary key,
	videoId int64,
	institutionId int64
);

create table LG_Video_Lectureseries (
	videoLectureseriesId int64 not null primary key,
	videoId int64,
	lectureseriesId int64,
	openAccess integer
);

create table LG_Videohitlist (
	videohitlistId int64 not null primary key,
	hitsPerDay int64,
	hitsPerWeek int64,
	hitsPerMonth int64,
	hitsPerYear int64,
	videoId int64
);

create table LG_Year (
	yearId int64 not null primary key,
	prefix varchar(75),
	name varchar(75)
);
