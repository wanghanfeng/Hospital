package dao;

import model.HealthScreen;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public interface HealthScreenDao {

    List<HealthScreen> getAllhealthScreens();

    int addHealthScreen(HealthScreen healthScreen);

    List<HealthScreen> getHealthScreenByInf(@Param("theDate") String theDate);

}
