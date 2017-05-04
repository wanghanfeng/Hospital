package service.impl;

import dao.ConsumablesDao;
import model.Consumables;
import org.apache.ibatis.session.SqlSession;
import service.ConsumablesService;
import utils.SqlSessionFactoryUtil;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public class ConsumablesServiceImpl implements ConsumablesService{

    SqlSession sqlSession = null;

    @Override
    public List<Consumables> getAllConsumables() {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        ConsumablesDao consumablesDao = sqlSession.getMapper(ConsumablesDao.class);
        //查询所有财务记录
        List<Consumables> list = consumablesDao.getAllConsumables();
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public int addConsumables(Consumables consumables) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        ConsumablesDao consumablesDao = sqlSession.getMapper(ConsumablesDao.class);
        //添加财务记录
        int i = consumablesDao.addConsumables(consumables);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public List<Consumables> getConsumablesByInf(String theName, String theUnit) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        ConsumablesDao consumablesDao = sqlSession.getMapper(ConsumablesDao.class);
        //查记录
        List<Consumables> list = consumablesDao.getConsumablesByInf(theName,theUnit);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
}
