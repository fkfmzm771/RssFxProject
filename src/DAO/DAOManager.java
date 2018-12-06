package DAO;


import VO.Nyaa_si_FeedMessage;
import VO.SrcRss;
import VO.UserRss;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;

public class DAOManager{

    private static SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

    public static boolean insertRssTorrent(Nyaa_si_FeedMessage feedMessage) {
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

    public static boolean insertSrcRss(SrcRss srcRss) {
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

    public static boolean insertUserRss(UserRss userRss) {
        SqlSession session = null;
        int cnt = 0;

        try {
            session = factory.openSession();
            Mapper mapper = session.getMapper(Mapper.class);
            cnt = mapper.insertUserRss(userRss);

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


    public static ArrayList<Nyaa_si_FeedMessage> selectListItem(String alias){

        ArrayList<Nyaa_si_FeedMessage> messages = new ArrayList<>();
        SqlSession session = null;
        Nyaa_si_FeedMessage feedMessage = null;
        try {
            session = factory.openSession();
            Mapper mapper = session.getMapper(Mapper.class);
            messages = mapper.selectListItem(alias);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (session != null) session.close();
        }
        return messages;

    }

}
