package dao;

import model.Conexamne;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lp on 2017/5/4.
 */
public interface ConexamneDao {

    //增加外诊审批
    int addConexamne(Conexamne conexamne);

    //按字段查询外诊审批
    List<Conexamne> getConexamneByInf(@Param("theName") String theName, @Param("theUnit") String theUnit);

    //查询所有外诊审批
    List<Conexamne> getAllConexamne();

}
