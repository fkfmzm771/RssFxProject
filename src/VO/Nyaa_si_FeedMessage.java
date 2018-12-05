package VO;

public class Nyaa_si_FeedMessage {
	private String title;   //타이틀
	private String link;    //토런트 파일 링크
	private String guid;    //해당 페이지 링크
	private String pubDate;     //업로드 날짜
	private String nyaa_seeders;    //배포자 숫자
	private String nyaa_leechers;   //공유 인원
	private String nyaa_downloads;  //다운로드 숫자
	private String nyaa_infoHash;   //해쉬코드
	private String nyaa_categoryId; //카테고리 아이디
	private String nyaa_category;   //카테고리
	private String nyaa_size;   //파일 용량
	private String user_alias;   //파일 용량
	private String user_downCheck;   //파일 용량
	private String user_lastDownDate;   //파일 용량


	public Nyaa_si_FeedMessage() {
	}

	public Nyaa_si_FeedMessage(String title, String link, String guid, String pubDate, String nyaa_seeders, String nyaa_leechers, String nyaa_downloads, String nyaa_infoHash, String nyaa_categoryId, String nyaa_category, String nyaa_size, String user_alias, String user_downCheck, String user_lastDownDate) {
		this.title = title;
		this.link = link;
		this.guid = guid;
		this.pubDate = pubDate;
		this.nyaa_seeders = nyaa_seeders;
		this.nyaa_leechers = nyaa_leechers;
		this.nyaa_downloads = nyaa_downloads;
		this.nyaa_infoHash = nyaa_infoHash;
		this.nyaa_categoryId = nyaa_categoryId;
		this.nyaa_category = nyaa_category;
		this.nyaa_size = nyaa_size;
		this.user_alias = user_alias;
		this.user_downCheck = user_downCheck;
		this.user_lastDownDate = user_lastDownDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getNyaa_seeders() {
		return nyaa_seeders;
	}

	public void setNyaa_seeders(String nyaa_seeders) {
		this.nyaa_seeders = nyaa_seeders;
	}

	public String getNyaa_leechers() {
		return nyaa_leechers;
	}

	public void setNyaa_leechers(String nyaa_leechers) {
		this.nyaa_leechers = nyaa_leechers;
	}

	public String getNyaa_downloads() {
		return nyaa_downloads;
	}

	public void setNyaa_downloads(String nyaa_downloads) {
		this.nyaa_downloads = nyaa_downloads;
	}

	public String getNyaa_infoHash() {
		return nyaa_infoHash;
	}

	public void setNyaa_infoHash(String nyaa_infoHash) {
		this.nyaa_infoHash = nyaa_infoHash;
	}

	public String getNyaa_categoryId() {
		return nyaa_categoryId;
	}

	public void setNyaa_categoryId(String nyaa_categoryId) {
		this.nyaa_categoryId = nyaa_categoryId;
	}

	public String getNyaa_category() {
		return nyaa_category;
	}

	public void setNyaa_category(String nyaa_category) {
		this.nyaa_category = nyaa_category;
	}

	public String getNyaa_size() {
		return nyaa_size;
	}

	public void setNyaa_size(String nyaa_size) {
		this.nyaa_size = nyaa_size;
	}

	public String getUser_alias() {
		return user_alias;
	}

	public void setUser_alias(String user_alias) {
		this.user_alias = user_alias;
	}

	public String getUser_downCheck() {
		return user_downCheck;
	}

	public void setUser_downCheck(String user_downCheck) {
		this.user_downCheck = user_downCheck;
	}

	public String getUser_lastDownDate() {
		return user_lastDownDate;
	}

	public void setUser_lastDownDate(String user_lastDownDate) {
		this.user_lastDownDate = user_lastDownDate;
	}

	@Override
	public String toString() {
		return "Nyaa_si_FeedMessage{" +
				"title='" + title + '\'' +
				", link='" + link + '\'' +
				", guid='" + guid + '\'' +
				", pubDate='" + pubDate + '\'' +
				", nyaa_seeders='" + nyaa_seeders + '\'' +
				", nyaa_leechers='" + nyaa_leechers + '\'' +
				", nyaa_downloads='" + nyaa_downloads + '\'' +
				", nyaa_infoHash='" + nyaa_infoHash + '\'' +
				", nyaa_categoryId='" + nyaa_categoryId + '\'' +
				", nyaa_category='" + nyaa_category + '\'' +
				", nyaa_size='" + nyaa_size + '\'' +
				", user_alias='" + user_alias + '\'' +
				", user_downCheck='" + user_downCheck + '\'' +
				", user_lastDownDate='" + user_lastDownDate + '\'' +
				'}';
	}
}
