package VO;

public class WebSiteRss {
    private String siteAlias;
    private String siteRssAdr;


    public WebSiteRss() {
    }

    public WebSiteRss(String alias, String rssAdr) {
        this.siteAlias = alias;
        this.siteRssAdr = rssAdr;
    }

    public String getAlias() {
        return siteAlias;
    }

    public void setAlias(String alias) {
        this.siteAlias = alias;
    }

    public String getRssAdr() {
        return siteRssAdr;
    }

    public void setRssAdr(String rssAdr) {
        this.siteRssAdr = rssAdr;
    }
}
