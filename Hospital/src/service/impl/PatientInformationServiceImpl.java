package service.impl;

import dao.PatientInformationDao;
import model.PatientInformation;
import org.apache.ibatis.session.SqlSession;
import service.PatientInformationService;
import utils.SqlSessionFactoryUtil;

import java.util.List;

/**
 * Created by AceCream on 2017/4/12.
 */
public class PatientInformationServiceImpl implements PatientInformationService {

    SqlSession sqlSession = null;

    @Override
    public List<PatientInformation> getAllPatient() {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        PatientInformationDao patientInformationDao = sqlSession.getMapper(PatientInformationDao.class);
        //查找所有病人
        List<PatientInformation> list = patientInformationDao.getAllPatient();
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public int addPatientInformation(PatientInformation patientInformation) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        PatientInformationDao patientInformationDao = sqlSession.getMapper(PatientInformationDao.class);
        //添加病人
        int i = patientInformationDao.addPatientInformation(patientInformation);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public List<PatientInformation> getPatientsByInf(String theName, String theSex, String theUnit) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        PatientInformationDao patientInformationDao = sqlSession.getMapper(PatientInformationDao.class);
        //查找病人
        List<PatientInformation> list = patientInformationDao.getPatientsByInf(theName,theSex,theUnit);
        return list;
    }
}
