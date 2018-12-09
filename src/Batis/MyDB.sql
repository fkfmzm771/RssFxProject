
CREATE TABLE srcTorrent (
title varchar2(300)
,link varchar2(100)
,guId varchar2(100)
,pubDate varchar2(100)
,seeders varchar2(100)
,leechers varchar2(100)
,downloads varchar2(100)
,infoHash varchar2(100)
,categoryID varchar2(100)
,category varchar2(100)
,torrentsize varchar2(100)
,alias varchar2(100)
,downloadCheck varchar2(100)
,lastDownDate varchar2(100)
);

CREATE TABLE srcRss (
    alias varchar2(100) PRIMARY KEY
  , rssAdr varchar2(200)
);

CREATE TABLE webSiteRss (
    SiteAlias varchar2(100) PRIMARY KEY
  , SiteRssAdr varchar2(200)
);
