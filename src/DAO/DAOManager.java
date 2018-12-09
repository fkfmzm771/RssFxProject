package DAO;


import VO.Nyaa_si_FeedMessage;
import VO.RssSrc;
import VO.WebSiteRss;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;

public class DAOManager {

    private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

    public boolean insertRssTorrent(Nyaa_si_FeedMessage feedMessage) {
        SqlSession session = null;
        int cnt = 0;

        try {
            session = factory.openSession();
            Mapper mapper = session.getMapper(Mapper.class);
            cnt = mapper.insertRssTorrent(feedMessage);

            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        if (cnt == 0)
            return false;
        else
            return true;
    }

    public ArrayList<Nyaa_si_FeedMessage> selectListItem(String alias) {

        ArrayList<Nyaa_si_FeedMessage> messages = null;
        SqlSession session = null;

        try {
            session = factory.openSession();
            Mapper mapper = session.getMapper(Mapper.class);
            messages = mapper.selectListItem(alias);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return messages;
    }


    //사용자 검색 사이트 추가
    public boolean insertWebSiteRss(WebSiteRss webSiteRss) {
        SqlSession session = null;
        int cnt = 0;

        try {
            session = factory.openSession();
            Mapper mapper = session.getMapper(Mapper.class);
            cnt = mapper.insertWebSiteRss(webSiteRss);

            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        if (cnt == 0)
            return false;
        else
            return true;
    }

    public ArrayList<WebSiteRss> selectWebSiteRss() {
        ArrayList<WebSiteRss> webSiteRsses = null;
        SqlSession session = null;

        try {
            session = factory.openSession();
            Mapper mapper = session.getMapper(Mapper.class);
            webSiteRsses = mapper.selectWebSiteRss();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return webSiteRsses;
    }


    //사용자 즐겨찾기 추가
    public boolean insertSrcRss(RssSrc srcRss) {
        SqlSession session = null;
        int cnt = 0;

        try {
            session = factory.openSession();
            Mapper mapper = session.getMapper(Mapper.class);
            cnt = mapper.insertSrcRss(srcRss);

            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        if (cnt == 0)
            return false;
        else
            return true;
    }

    public ArrayList<RssSrc> selectSrcRss() {
        SqlSession session = null;
        ArrayList<RssSrc> messages = null;

        try {
            session = factory.openSession();
            Mapper mapper = session.getMapper(Mapper.class);

            messages = mapper.selectSrcRss();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
        }

        return messages;
    }


}
