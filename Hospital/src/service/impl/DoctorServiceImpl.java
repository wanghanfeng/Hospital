package service.impl;

import dao.DoctorDao;
import model.Doctor;
import org.apache.ibatis.session.SqlSession;
import service.DoctorService;
import utils.SqlSessionFactoryUtil;

import java.util.List;

/**
 * Created by AceCream on 2017/4/11.
 */
public class DoctorServiceImpl implements DoctorService{

    SqlSession sqlSession = null;

    @Override
    public int addDoctor(Doctor doctor) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DoctorDao doctorDao = sqlSession.getMapper(DoctorDao.class);
        //添加医生，如果成功i为1，如果失败i为0
        int i = doctorDao.addDoctor(doctor);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return i;
    }

    @Override
    public Doctor loadDoctor(int theId) {
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DoctorDao doctorDao = sqlSession.getMapper(DoctorDao.class);
        Doctor doctor = doctorDao.loadDoctor(theId);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return doctor;
    }

    @Override
    public Doctor LoginDoctor(int docId, String docName, String docPassword) {
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DoctorDao doctorDao = sqlSession.getMapper(DoctorDao.class);
        Doctor doctor = doctorDao.LoginDoctor(docId,docName,docPassword);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return doctor;
    }

    @Override
    public int updateDoctor(Doctor doctor) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DoctorDao doctorDao = sqlSession.getMapper(DoctorDao.class);
        //添加医生，如果成功i为1，如果失败i为0
        int i = doctorDao.updateDoctor(doctor);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return i;
    }

    @Override
    public List<Doctor> selectAllDoctor() {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        DoctorDao doctorDao = sqlSession.getMapper(DoctorDao.class);
        List<Doctor> doctors = doctorDao.selectAllDoctor();
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return doctors;
    }

}
