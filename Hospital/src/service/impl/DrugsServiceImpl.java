package service.impl;

import dao.DrugsDao;
import model.Drugs;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import service.DrugsService;
import utils.SqlSessionFactoryUtil;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public class DrugsServiceImpl implements DrugsService{

    SqlSession sqlSession = null;

    @Override
    public List<Drugs> getAllDrugs() {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DrugsDao drugsDao = sqlSession.getMapper(DrugsDao.class);
        List<Drugs> list = drugsDao.getAllDrugs();
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public Drugs loadDrug(String drugcode) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DrugsDao drugsDao = sqlSession.getMapper(DrugsDao.class);
        Drugs drugs = drugsDao.loadDrug(drugcode);
        sqlSession.commit();
        sqlSession.close();
        return drugs;
    }

    @Override
    public int addDrugs(Drugs drugs) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DrugsDao drugsDao = sqlSession.getMapper(DrugsDao.class);
        int i = drugsDao.addDrugs(drugs);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public List<Drugs> getDrugsByInf(String theName) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DrugsDao drugsDao = sqlSession.getMapper(DrugsDao.class);
        List<Drugs> list = drugsDao.getDrugsByInf(theName);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public int updateDrugs(Drugs drugs) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DrugsDao drugsDao = sqlSession.getMapper(DrugsDao.class);
        int i = drugsDao.updateDrugs(drugs);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public Drugs getDrugByName(String drug) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DrugsDao drugsDao = sqlSession.getMapper(DrugsDao.class);
        Drugs drugs = drugsDao.getDrugByName(drug);
        sqlSession.commit();
        sqlSession.close();
        return drugs;
    }

    @Override
    public List<Drugs> getDrugsByTime(@Param("startTime") String startTime, @Param("endTime") String endTime) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DrugsDao drugsDao = sqlSession.getMapper(DrugsDao.class);
        List<Drugs> list = drugsDao.getDrugsByTime(startTime , endTime);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
    @Override
    public int updateALLDrugs(Drugs drugs) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DrugsDao drugsDao = sqlSession.getMapper(DrugsDao.class);
        int i = drugsDao.updateALLDrugs(drugs);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public int deleteDrugs(Drugs drugs) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DrugsDao drugsDao = sqlSession.getMapper(DrugsDao.class);
        int i = drugsDao.deleteDrugs(drugs);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }
}
