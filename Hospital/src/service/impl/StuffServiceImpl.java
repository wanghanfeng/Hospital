package service.impl;

import dao.StuffDao;
import model.Stuff;
import org.apache.ibatis.session.SqlSession;
import service.StuffService;
import utils.SqlSessionFactoryUtil;

import java.util.List;

/**
 * Created by AceCream on 2017/4/16.
 */
public class StuffServiceImpl implements StuffService{

    SqlSession sqlSession = null;

    @Override
    public int addStuff(Stuff stuff) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StuffDao stuffDao = sqlSession.getMapper(StuffDao.class);
        int i = stuffDao.addStuff(stuff);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public Stuff loadStuff(String stuffcode) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StuffDao stuffDao = sqlSession.getMapper(StuffDao.class);
        Stuff stuff = stuffDao.loadStuff(stuffcode);
        sqlSession.commit();
        sqlSession.close();
        return stuff;
    }

    @Override
    public int updateStuff(Stuff tempStuff) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StuffDao stuffDao = sqlSession.getMapper(StuffDao.class);
        int i = stuffDao.updateStuff(tempStuff);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public List<Stuff> getAllStuff() {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StuffDao stuffDao = sqlSession.getMapper(StuffDao.class);
        List<Stuff> list = stuffDao.getAllStuff();
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public List<Stuff> getStuffByInf(String theName) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StuffDao stuffDao = sqlSession.getMapper(StuffDao.class);
        List<Stuff> list = stuffDao.getStuffByInf(theName);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
}
