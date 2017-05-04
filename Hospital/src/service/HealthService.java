package service;

import model.Health;

/**
 * Created by AceCream on 2017/4/11.
 */
public interface HealthService {

    Health loadHealth(int theId);

    int addHealth(Health health);

    Health LoginHealth(int heaId, String heaName, String heaPassword);

    int updateHealth(Health health);
}
