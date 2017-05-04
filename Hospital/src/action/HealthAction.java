package action;

import model.Health;
import service.HealthService;
import service.impl.HealthServiceImpl;

/**
 * Created by AceCream on 2017/4/11.
 */
public class HealthAction {

    HealthService healthService = new HealthServiceImpl();

    //查医疗卫生
    public Health loadHealth(int theId) {
        Health health = healthService.loadHealth(theId);
        return health;
    }

    //添加卫生
    public int addHealth(Health health) {
        int i = healthService.addHealth(health);
        return i;
    }

    //登录
    public Health LoginHealth(int heaId, String heaName, String heaPassword) {
        Health health = healthService.LoginHealth(heaId,heaName,heaPassword);
        return health;
    }

    public int updateHealth(Health health) {
        int i = healthService.updateHealth(health);
        return i;
    }
}
