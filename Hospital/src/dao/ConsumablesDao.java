package dao;

import model.Consumables;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public interface ConsumablesDao {

    List<Consumables> getAllConsumables();

    int addConsumables(Consumables consumables);

    List<Consumables> getConsumablesByInf(@Param("theName") String theName, @Param("theUnit") String theUnit);
}
