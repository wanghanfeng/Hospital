package action;

import model.Consumables;
import service.ConsumablesService;
import service.impl.ConsumablesServiceImpl;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public class ConsumablesAction {

    ConsumablesService consumablesService = new ConsumablesServiceImpl();

    public List<Consumables> getAllConsumables() {
        List<Consumables> list = consumablesService.getAllConsumables();
        return list;
    }


    public int addConsumables(Consumables consumables) {
        int i = consumablesService.addConsumables(consumables);
        return i;
    }

    public List<Consumables> getConsumablesByInf(String theName, String theUnit) {
        List<Consumables> list = consumablesService.getConsumablesByInf(theName,theUnit);
        return list;
    }
}
