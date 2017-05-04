package dao;

import model.Finance;
import org.apache.ibatis.annotations.Param;

/**
 * Created by AceCream on 2017/4/11.
 */
public interface FinanceDao {

    Finance loadFinance(int theId);

    int addFinance(Finance finance);

    Finance LoginFince(@Param("finId") int finId, @Param("finName") String finName, @Param("finPassword") String finPassword);

    int updateFinance(Finance finance);

}
