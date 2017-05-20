package action;

import model.LeaderPay;
import service.LeaderPayService;
import service.impl.LeaderPayServiceImpl;

import java.util.List;

/**
 * Created by lp on 2017/5/8.
 */
public class LeaderPayAction {
    private LeaderPayService leaderPayService = new LeaderPayServiceImpl();
    public int addLeaderPay(LeaderPay leaderPay) {
        return leaderPayService.addLeaderPay(leaderPay);
    }

    public List<LeaderPay> getAllLeaderPay() {
        return leaderPayService.getAllLeaderPay();
    }

    public List<LeaderPay> getLeaderPayByInf(String theName,  String theSex, String theUnit) {
        return leaderPayService.getLeaderPayByInf(theName , theSex , theUnit);
    }
}
