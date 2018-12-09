package DAO;

import VO.Nyaa_si_FeedMessage;
import VO.RssSrc;
import VO.WebSiteRss;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface Mapper {


    public int insertRssTorrent(Nyaa_si_FeedMessage feedMessage);

    public ArrayList<Nyaa_si_FeedMessage> selectListItem(@Param("alias") String alias);

    public int insertSrcRss(RssSrc rssSrc);

    public ArrayList<RssSrc> selectSrcRss();

    public int insertWebSiteRss(WebSiteRss userRss);

    public ArrayList<WebSiteRss> selectWebSiteRss();


}
