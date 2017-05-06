package service.impl;

import dao.HealthDao;
import model.Health;
import org.apache.ibatis.session.SqlSession;
import service.HealthService;
import utils.SqlSessionFactoryUtil;

/**
 * Created by AceCream on 2017/4/11.
 */
public class HealthServiceImpl implements HealthService{

    SqlSession sqlSession = null;

    @Override
    public Health loadHealth(int theId) {
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        HealthDao healthDao  = sqlSession.getMapper(HealthDao.class);
        Health health = healthDao.loadHealth(theId);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return health;
    }

    @Override
    public int addHealth(Health health) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        HealthDao healthDao = sqlSession.getMapper(HealthDao.class);
        //添加，如果成功i为1，如果失败i为0
        int i = healthDao.addHealth(health);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return i;
    }

    @Override
    public Health LoginHealth(int heaId, String heaName, String heaPassword) {
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        HealthDao healthDao  = sqlSession.getMapper(HealthDao.class);
        Health health = healthDao.LoginHealth(heaId,heaName,heaPassword);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return health;
    }

    @Override
    public int updateHealth(Health health) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        HealthDao healthDao = sqlSession.getMapper(HealthDao.class);
        //添加，如果成功i为1，如果失败i为0
        int i = healthDao.updateHealth(health);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return i;
    }
}
