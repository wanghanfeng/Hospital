package service.impl;

import dao.HealthScreenDao;
import model.HealthScreen;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import service.HealthScreenService;
import utils.SqlSessionFactoryUtil;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public class HealthScreenServiceImpl implements HealthScreenService{

    SqlSession sqlSession = null;

    @Override
    public List<HealthScreen> getAllhealthScreens() {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        HealthScreenDao healthScreenDao = sqlSession.getMapper(HealthScreenDao.class);
        List<HealthScreen> list = healthScreenDao.getAllhealthScreens();
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public int addHealthScreen(HealthScreen healthScreen) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        HealthScreenDao healthScreenDao = sqlSession.getMapper(HealthScreenDao.class);
        int i = healthScreenDao.addHealthScreen(healthScreen);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public List<HealthScreen> getHealthScreenByInf(String theDate) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        HealthScreenDao healthScreenDao = sqlSession.getMapper(HealthScreenDao.class);
        List<HealthScreen> list = healthScreenDao.getHealthScreenByInf(theDate);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public List<HealthScreen> getHealthScreenByTime(@Param("startTime") String startTime, @Param("endTime") String endTime) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        HealthScreenDao healthScreenDao = sqlSession.getMapper(HealthScreenDao.class);
        List<HealthScreen> list = healthScreenDao.getHealthScreenByTime(startTime , endTime);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public int updateHealthScreen(HealthScreen healthScreen) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        HealthScreenDao healthScreenDao = sqlSession.getMapper(HealthScreenDao.class);
        int i = healthScreenDao.updateHealthScreen(healthScreen);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public int deleteHealthScreen(HealthScreen healthScreen) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        HealthScreenDao healthScreenDao = sqlSession.getMapper(HealthScreenDao.class);
        int i = healthScreenDao.deleteHealthScreen(healthScreen);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }
}
