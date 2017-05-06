package service;

import model.Finance;

/**
 * Created by AceCream on 2017/4/11.
 */
public interface FinanceService{

    Finance loadFinance(int theId);

    int addFinance(Finance finance);

    Finance LoginFince(int finId, String finName, String finPassword);

    int updateFinance(Finance finance);

}
