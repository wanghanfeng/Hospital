package service;

import model.StoreKeeper;

/**
 * Created by AceCream on 2017/4/11.
 */
public interface StoreKeeperService {

    StoreKeeper loadStoreKeeper(int theId);

    int addStoreKeeper(StoreKeeper storeKeeper);

    StoreKeeper LoginStoreKeeper(int stoId, String stoName, String stoPassword);

    int updateStoreKeeper(StoreKeeper storeKeeper);
}
