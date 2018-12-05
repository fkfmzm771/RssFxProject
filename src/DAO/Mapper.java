package DAO;

import VO.SrcRss;
import VO.UserRss;
import VO.Nyaa_si_FeedMessage;

import java.util.ArrayList;

public interface Mapper {


	public int insertRssTorrent(Nyaa_si_FeedMessage feedMessage);
	public int insertSrcRss(SrcRss srcRss);
	public int insertUserRss(UserRss userRss);



}
