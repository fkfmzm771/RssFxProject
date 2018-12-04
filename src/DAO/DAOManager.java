package DAO;


import DBVO.SrcRss;
import DBVO.UserRss;
import VO.Nyaa_si_FeedMessage;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;

public class DAOManager implements Mapper{

	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

    @Override
    public ArrayList<Nyaa_si_FeedMessage> insertRssTorrent() {
        return null;
    }

    @Override
    public ArrayList<SrcRss> insertUserSrcRss() {
        return null;
    }

    @Override
    public ArrayList<UserRss> insertUserRss() {
        return null;
    }


//
//	public ArrayList<Person> selectPerson1() {
//		SqlSession session = null;
//		ArrayList<Person> pList = new ArrayList<>();
//
//		try {
//			session = factory.openSession();
//			Mapper mapper = session.getMapper(Mapper.class);
//			pList = mapper.selectPerson1();
//			session.commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (session != null)
//				session.close();
//		}
//
//		return pList;
//	}

}
