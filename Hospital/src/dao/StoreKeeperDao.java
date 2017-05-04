package dao;

import model.StoreKeeper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by AceCream on 2017/4/11.
 */
public interface StoreKeeperDao {

    StoreKeeper loadStoreKeeper(int theId);

    int addStoreKeeper(StoreKeeper storeKeeper);

    StoreKeeper LoginStoreKeeper(@Param("stoId") int stoId, @Param("stoName") String stoName, @Param("stoPassword") String stoPassword);

    int updateStoreKeeper(StoreKeeper storeKeeper);

}
