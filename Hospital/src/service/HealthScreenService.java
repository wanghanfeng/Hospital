package service;

import model.HealthScreen;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public interface HealthScreenService {

    List<HealthScreen> getAllhealthScreens();

    int addHealthScreen(HealthScreen healthScreen);

    List<HealthScreen> getHealthScreenByInf(String theDate);
    int updateHealthScreen(HealthScreen healthScreen);
    List<HealthScreen> getHealthScreenByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);

    int deleteHealthScreen(HealthScreen healthScreen);
}
