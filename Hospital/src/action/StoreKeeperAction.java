package action;

import model.StoreKeeper;
import service.StoreKeeperService;
import service.impl.StoreKeeperServiceImpl;

/**
 * Created by AceCream on 2017/4/11.
 * 仓库管理员
 */
public class StoreKeeperAction {

    StoreKeeperService storeKeeperService = new StoreKeeperServiceImpl();

    public StoreKeeper loadStoreKeeper(int theId) {
        StoreKeeper storeKeeper = storeKeeperService.loadStoreKeeper(theId);
        return storeKeeper;
    }


    public int addStoreKeeper(StoreKeeper storeKeeper) {
        int i = storeKeeperService.addStoreKeeper(storeKeeper);
        return i;
    }

    public StoreKeeper LoginStoreKeeper(int stoId, String stoName, String stoPassword) {
        StoreKeeper storeKeeper = storeKeeperService.LoginStoreKeeper(stoId,stoName,stoPassword);
        return storeKeeper;
    }

    public int updateStoreKeeper(StoreKeeper storeKeeper) {
        int i = storeKeeperService.updateStoreKeeper(storeKeeper);
        return i;
    }
}
