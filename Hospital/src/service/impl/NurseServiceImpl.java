package service.impl;

import dao.NurseDao;
import model.Nurse;
import org.apache.ibatis.session.SqlSession;
import service.NurseService;
import utils.SqlSessionFactoryUtil;

import java.util.List;

/**
 * Created by AceCream on 2017/4/11.
 */
public class NurseServiceImpl implements NurseService{

    SqlSession sqlSession = null;

    public Nurse loadNurse(int theId) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        NurseDao nurseDao = sqlSession.getMapper(NurseDao.class);
        //添加医生，如果成功i为1，如果失败i为0
        Nurse nurse = nurseDao.loadNurse(theId);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return nurse;
    }

    public int addNurse(Nurse nurse) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        NurseDao nurseDao = sqlSession.getMapper(NurseDao.class);
        //添加，如果成功i为1，如果失败i为0
        int i = nurseDao.addNurse(nurse);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return i;
    }

    @Override
    public Nurse LoginNurse(int nurId, String nurName, String nurPassword) {
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        NurseDao nurseDao = sqlSession.getMapper(NurseDao.class);
        //添加医生，如果成功i为1，如果失败i为0
        Nurse nurse = nurseDao.LoginNurse(nurId,nurName,nurPassword);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return nurse;
    }

    @Override
    public int updateNurse(Nurse nurse) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        NurseDao nurseDao = sqlSession.getMapper(NurseDao.class);
        //添加，如果成功i为1，如果失败i为0
        int i = nurseDao.updateNurse(nurse);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return i;
    }

    @Override
    public List<Nurse> selectAllNurse() {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        NurseDao nurseDao = sqlSession.getMapper(NurseDao.class);
        //查询所有护士
        List<Nurse> nurses = nurseDao.selectAllNurse();
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
        return nurses;
    }
}
