package service.impl;

import dao.NurseRecordsDao;
import model.NurseRecords;
import org.apache.ibatis.session.SqlSession;
import service.NurseRecordsService;
import utils.SqlSessionFactoryUtil;

import java.util.List;

/**
 * Created by AceCream on 2017/4/14.
 */
public class NurseRecordsServiceImpl implements NurseRecordsService{

    SqlSession sqlSession = null;

    @Override
    public int addNurseRecords(NurseRecords nurseRecords) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        NurseRecordsDao nurseRecordsDao = sqlSession.getMapper(NurseRecordsDao.class);
        //添加护理记录
        int i = nurseRecordsDao.addNurseRecords(nurseRecords);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public List<NurseRecords> getAllNurseRecords() {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        NurseRecordsDao nurseRecordsDao = sqlSession.getMapper(NurseRecordsDao.class);
        //查找所有护理记录
        List<NurseRecords> list = nurseRecordsDao.getAllNurseRecords();
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public List<NurseRecords> getNurseRecordsByInf(String theName, String theSex, String theUnit) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        NurseRecordsDao nurseRecordsDao = sqlSession.getMapper(NurseRecordsDao.class);
        //查找所有护理记录
        List<NurseRecords> list = nurseRecordsDao.getNurseRecordsByInf(theName,theSex,theUnit);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
}
