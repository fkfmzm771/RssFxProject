package DAO;


import VO.Nyaa_si_FeedMessage;
import VO.SrcRss;
import VO.UserRss;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

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

    public boolean insertSrcRss(SrcRss srcRss) {
        return false;
    }

    public boolean insertUserRss(UserRss userRss) {
        return false;
    }
}
