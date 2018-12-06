package DAO;

import VO.Nyaa_si_FeedMessage;
import VO.SrcRss;
import VO.UserRss;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface Mapper {


    public int insertRssTorrent(Nyaa_si_FeedMessage feedMessage);

    public int insertSrcRss(SrcRss srcRss);

    public int insertUserRss(UserRss userRss);

    public ArrayList<Nyaa_si_FeedMessage> selectListItem(@Param("alias") String alias);

}
