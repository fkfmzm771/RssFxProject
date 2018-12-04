package DBVO;

public class UserRss {
    private String alias;
    private String rssAdr;

    public UserRss(String alias, String rssAdr) {
        this.alias = alias;
        this.rssAdr = rssAdr;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getRssAdr() {
        return rssAdr;
    }

    public void setRssAdr(String rssAdr) {
        this.rssAdr = rssAdr;
    }
}
