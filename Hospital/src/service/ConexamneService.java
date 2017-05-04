package service;

import model.Conexamne;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by lp on 2017/5/4.
 */
public interface ConexamneService {
    int addConexamne(Conexamne conexamne);

    List<Conexamne> getConexamneByInf(@Param("theName") String theName, @Param("theUnit") String theUnit);

    List<Conexamne> getAllConexamne();
}
