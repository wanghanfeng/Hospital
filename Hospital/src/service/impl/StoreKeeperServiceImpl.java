package service.impl;

import dao.StoreKeeperDao;
import model.StoreKeeper;
import org.apache.ibatis.session.SqlSession;
import service.StoreKeeperService;
import utils.SqlSessionFactoryUtil;

/**
 * Created by AceCream on 2017/4/11.
 */
public class StoreKeeperServiceImpl implements StoreKeeperService{

    SqlSession sqlSession = null;

    @Override
    public StoreKeeper loadStoreKeeper(int theId) {
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StoreKeeperDao storeKeeperDao = sqlSession.getMapper(StoreKeeperDao.class);
        StoreKeeper storeKeeper = storeKeeperDao.loadStoreKeeper(theId);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return storeKeeper;
    }

    @Override
    public int addStoreKeeper(StoreKeeper storeKeeper) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StoreKeeperDao storeKeeperDao = sqlSession.getMapper(StoreKeeperDao.class);
        //添加，如果成功i为1，如果失败i为0
        int i = storeKeeperDao.addStoreKeeper(storeKeeper);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return i;
    }

    @Override
    public StoreKeeper LoginStoreKeeper(int stoId, String stoName, String stoPassword) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StoreKeeperDao storeKeeperDao = sqlSession.getMapper(StoreKeeperDao.class);
        //添加医生，如果成功i为1，如果失败i为0
        StoreKeeper storeKeeper = storeKeeperDao.LoginStoreKeeper(stoId,stoName,stoPassword);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return storeKeeper;
    }

    @Override
    public int updateStoreKeeper(StoreKeeper storeKeeper) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        StoreKeeperDao storeKeeperDao = sqlSession.getMapper(StoreKeeperDao.class);
        //添加医生，如果成功i为1，如果失败i为0
        int i = storeKeeperDao.updateStoreKeeper(storeKeeper);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return i;
    }
}
