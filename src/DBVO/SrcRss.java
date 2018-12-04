package DBVO;

public class SrcRss {
    private String src_alias;
    private String src_rssAdr;

    public SrcRss(String alias, String rssAdr) {
        this.src_alias = alias;
        this.src_rssAdr = rssAdr;
    }

    public String getSrc_alias() {
        return src_alias;
    }

    public void setSrc_alias(String src_alias) {
        this.src_alias = src_alias;
    }

    public String getSrc_rssAdr() {
        return src_rssAdr;
    }

    public void setSrc_rssAdr(String src_rssAdr) {
        this.src_rssAdr = src_rssAdr;
    }
}
