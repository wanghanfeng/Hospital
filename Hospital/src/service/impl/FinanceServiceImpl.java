package service.impl;

import dao.FinanceDao;
import model.Finance;
import org.apache.ibatis.session.SqlSession;
import service.FinanceService;
import utils.SqlSessionFactoryUtil;

/**
 * Created by AceCream on 2017/4/11.
 */
public class FinanceServiceImpl implements FinanceService {

    SqlSession sqlSession = null;

    @Override
    public Finance loadFinance(int theId) {
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        FinanceDao financeDao = sqlSession.getMapper(FinanceDao.class);
        Finance finance = financeDao.loadFinance(theId);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return finance;
    }

    @Override
    public int addFinance(Finance finance) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        FinanceDao financeDao = sqlSession.getMapper(FinanceDao.class);
        //添加，如果成功i为1，如果失败i为0
        int i = financeDao.addFinance(finance);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return i;
    }

    @Override
    public Finance LoginFince(int finId, String finName, String finPassword) {
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        FinanceDao financeDao = sqlSession.getMapper(FinanceDao.class);
        Finance finance = financeDao.LoginFince(finId,finName,finPassword);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return finance;
    }

    @Override
    public int updateFinance(Finance finance) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        FinanceDao financeDao = sqlSession.getMapper(FinanceDao.class);
        //添加，如果成功i为1，如果失败i为0
        int i = financeDao.updateFinance(finance);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return i;
    }


}
