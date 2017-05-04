package action;

import model.Finance;
import service.FinanceService;
import service.impl.FinanceServiceImpl;

/**
 * Created by AceCream on 2017/4/11.
 */
public class FinanceAction {

    FinanceService financeService = new FinanceServiceImpl();

    public Finance loadFinance(int theId) {
        Finance finance = financeService.loadFinance(theId);
        return finance;
    }

    public int addFinance(Finance finance) {
        int i = financeService.addFinance(finance);
        return i;
    }


    public Finance LoginFince(int finId, String finName, String finPassword) {
        Finance finance = financeService.LoginFince(finId,finName,finPassword);
        return finance;
    }

    public int updateFinance(Finance finance) {
        int i = financeService.updateFinance(finance);
        return i;
    }
}
