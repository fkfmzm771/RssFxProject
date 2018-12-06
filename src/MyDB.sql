CREATE TABLE userRss (
    to_alias varchar2(30) PRIMARY KEY
, to_rssAdr varchar2(200)
);

CREATE TABLE userSrcRss (
    src_alias varchar2(30) PRIMARY KEY
, src_rssAdr varchar2(200)
);

DROP TABLE userRss;

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


select * from srctorrent where TO_ALIAS = 'nn';
select * from usersrcrss;
drop table srctorrent;

CREATE TABLE userSrcRss (
    src_alias varchar2(100) PRIMARY KEY
, src_rssAdr varchar2(200)
);

drop table usersrcrss;

commit;



CREATE TABLE srcTorrent (
to_title varchar2(100)
,to_link varchar2(100)
, to_guId varchar2(100)
, to_pubDate varchar2(100)
, to_seeders varchar2(100)
, to_leechers varchar2(100)
, to_downloads varchar2(100)
, to_infoHash varchar2(100)
, to_categoryID varchar2(100)
, to_category varchar2(100)
, to_to_size VARCHAR(100)
, to_alias varchar2(100)
, to_downloadCheck varchar2(100)
, to_lastDownDate varchar2(100)
);



commit;
        