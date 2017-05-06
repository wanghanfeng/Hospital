package service.impl;

import dao.PrescriptionDao;
import model.Prescription;
import org.apache.ibatis.session.SqlSession;
import service.PrescriptionService;
import utils.SqlSessionFactoryUtil;

import java.util.List;

/**
 * Created by AceCream on 2017/4/13.
 */
public class PrescriptionServiceImpl implements PrescriptionService{

    SqlSession sqlSession = null;

    @Override
    public int addPrescription(Prescription prescription) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        PrescriptionDao prescriptionDao = sqlSession.getMapper(PrescriptionDao.class);
        //添加处方
        int i = prescriptionDao.addPrescription(prescription);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public List<Prescription> getAllPrescription() {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        PrescriptionDao prescriptionDao = sqlSession.getMapper(PrescriptionDao.class);
        //查找所有
        List<Prescription> list = prescriptionDao.getAllPrescription();
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public List<Prescription> getPrescriptionByInf(String theName, String theUnit) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        PrescriptionDao prescriptionDao = sqlSession.getMapper(PrescriptionDao.class);
        //查找符合条件的
        List<Prescription> list = prescriptionDao.getPrescriptionByInf(theName,theUnit);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }


}
