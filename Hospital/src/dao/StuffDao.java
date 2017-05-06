package dao;

import model.Stuff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AceCream on 2017/4/16.
 */
public interface StuffDao {

    int addStuff(Stuff stuff);

    Stuff loadStuff(String stuffcode);

    int updateStuff(Stuff tempStuff);

    List<Stuff> getAllStuff();

    List<Stuff> getStuffByInf(@Param("theName") String theName);

}
