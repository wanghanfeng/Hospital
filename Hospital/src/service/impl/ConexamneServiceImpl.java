package service.impl;

import dao.ConexamneDao;
import model.Conexamne;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import service.ConexamneService;
import utils.SqlSessionFactoryUtil;

import java.util.List;

/**
 * Created by lp on 2017/5/4.
 */
public class ConexamneServiceImpl implements ConexamneService {

    SqlSession sqlSession = null;

    @Override
    public List<Conexamne> getAllConexamne() {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        ConexamneDao conexamneDao = sqlSession.getMapper(ConexamneDao.class);
        //查询所有财务记录
        List<Conexamne> list = conexamneDao.getAllConexamne();
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public int addConexamne(Conexamne conexamne) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        ConexamneDao conexamneDao = sqlSession.getMapper(ConexamneDao.class);
        //查询所有财务记录
        int i = conexamneDao.addConexamne(conexamne);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public List<Conexamne> getConexamneByInf(@Param("theName") String theName, @Param("theUnit") String theUnit) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        ConexamneDao conexamneDao = sqlSession.getMapper(ConexamneDao.class);
        //查询所有财务记录
        List<Conexamne> list = conexamneDao.getConexamneByInf(theName , theUnit);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
}
