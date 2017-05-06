package service.impl;

import dao.SystemConDao;
import model.SystemCon;
import org.apache.ibatis.session.SqlSession;
import service.SystemConService;
import utils.SqlSessionFactoryUtil;

/**
 * Created by AceCream on 2017/4/11.
 */
public class SystemConServiceImpl implements SystemConService{

    SqlSession sqlSession = null;

    @Override
    public SystemCon LoginSystemCon(int id,String name, String password) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        SystemConDao systemConDao = sqlSession.getMapper(SystemConDao.class);
        //获取系统管理员，如果有就获取到，没有就为null
        SystemCon systemCon = systemConDao.LoginSystemCon(id,name,password);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return systemCon;
    }

    @Override
    public SystemCon loadSystemCon(int theId) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        SystemConDao systemConDao = sqlSession.getMapper(SystemConDao.class);
        //获取系统管理员，如果有就获取到，没有就为null
        SystemCon systemCon = systemConDao.loadSystemCon(theId);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return systemCon;
    }

    @Override
    public int addSystemCon(SystemCon systemCon) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        SystemConDao systemConDao = sqlSession.getMapper(SystemConDao.class);
        //添加，如果成功i为1，如果失败i为0
        int i = systemConDao.addSystemCon(systemCon);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return i;
    }

    @Override
    public int updateSystemCon(SystemCon systemCon) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        SystemConDao systemConDao = sqlSession.getMapper(SystemConDao.class);
        //更改，如果成功i为1，如果失败i为0
        int i = systemConDao.updateSystemCon(systemCon);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return i;
    }


}
