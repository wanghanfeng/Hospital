package service;

import model.Consumables;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public interface ConsumablesService {

    List<Consumables> getAllConsumables();

    int addConsumables(Consumables consumables);

    List<Consumables> getConsumablesByInf(String theName, String theUnit);
}
