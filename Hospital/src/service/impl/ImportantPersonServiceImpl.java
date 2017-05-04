package service.impl;

import dao.ImportantPersonDao;
import model.ImportantPerson;
import org.apache.ibatis.session.SqlSession;
import service.ImportantPersonService;
import utils.SqlSessionFactoryUtil;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public class ImportantPersonServiceImpl implements ImportantPersonService {

    SqlSession sqlSession = null;

    @Override
    public List<ImportantPerson> getAllImportantPerson() {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        ImportantPersonDao importantPersonDao = sqlSession.getMapper(ImportantPersonDao.class);
        //查询所有记录
        List<ImportantPerson> list = importantPersonDao.getAllImportantPerson();
        sqlSession.commit();
        sqlSession.close();
        return list;
    }

    @Override
    public int addImportantPerson(ImportantPerson importantPerson) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        ImportantPersonDao importantPersonDao = sqlSession.getMapper(ImportantPersonDao.class);
        //查询所有记录
        int i = importantPersonDao.addImportantPerson(importantPerson);
        sqlSession.commit();
        sqlSession.close();
        return i;
    }

    @Override
    public List<ImportantPerson> getImportantPersonByInf(String theName, String theUnit) {
        //开启SqlSession
        sqlSession = SqlSessionFactoryUtil.openSqlSession();
        ImportantPersonDao importantPersonDao = sqlSession.getMapper(ImportantPersonDao.class);
        //查询所有记录
        List<ImportantPerson> list = importantPersonDao.getImportantPersonByInf(theName,theUnit);
        sqlSession.commit();
        sqlSession.close();
        return list;
    }
}
