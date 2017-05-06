package dao;

import model.Health;
import org.apache.ibatis.annotations.Param;

/**
 * Created by AceCream on 2017/4/11.
 */
public interface HealthDao {

    Health loadHealth(int theId);

    int addHealth(Health health);

    Health LoginHealth(@Param("heaId") int heaId,@Param("heaName") String heaName,@Param("heaPassword") String heaPassword);

    int updateHealth(Health health);

}
