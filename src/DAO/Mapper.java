package DAO;

import DBVO.SrcRss;
import DBVO.UserRss;
import VO.Nyaa_si_FeedMessage;

import java.util.ArrayList;

public interface Mapper {


	public ArrayList<Nyaa_si_FeedMessage> insertRssTorrent();
	public ArrayList<SrcRss> insertUserSrcRss();
	public ArrayList<UserRss> insertUserRss();


}
