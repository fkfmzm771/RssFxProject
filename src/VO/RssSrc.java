package VO;

public class RssSrc {
    private String alias;
    private String rssAdr;

    public RssSrc() {
    }

    public RssSrc(String alias, String rssAdr) {
        this.alias = alias;
        this.rssAdr = rssAdr;
    }

    public String getSrc_alias() {
        return alias;
    }

    public void setSrc_alias(String src_alias) {
        this.alias = src_alias;
    }

    public String getSrc_rssAdr() {
        return rssAdr;
    }

    public void setSrc_rssAdr(String src_rssAdr) {
        this.rssAdr = src_rssAdr;
    }

    @Override
    public String toString() {
        return "RssSrc{" +
                "alias='" + alias + '\'' +
                ", rssAdr='" + rssAdr + '\'' +
                '}';
    }
}
