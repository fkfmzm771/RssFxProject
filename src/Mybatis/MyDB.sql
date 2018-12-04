CREATE TABLE userRss (
    to_alias varchar2(30) PRIMARY KEY
, to_rssAdr varchar2(200)
);

CREATE TABLE userSrcRss (
    src_alias varchar2(30) PRIMARY KEY
, src_rssAdr varchar2(200)
);

DROP TABLE userrss;

CREATE TABLE srcTorrent (
    to_title varchar2(50)
    ,to_link varchar2(100)
, to_guId varchar2(100)PRIMARY KEY
, to_pubDate DATE
, to_seeders varchar2(20)
, to_downloads varchar2(20)
, to_infoHash varchar2(100)
, to_categoryID varchar2(20)
, to_category varchar2(20)
, to_to_size VARCHAR(20)
, to_alias varchar2(30)
, to_downloadCheck varchar2(20)
, to_lastDownDate DATE
);
    
        