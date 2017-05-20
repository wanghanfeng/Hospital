package action;

import model.Quarantine;
import service.QuarantineService;
import service.impl.QuarantineServiceImpl;

import java.util.List;

/**
 * Created by lp on 2017/5/7.
 */
public class QuarantineAction {
    private QuarantineService quarantineService = new QuarantineServiceImpl();

    //增加外诊审批
    public int addQuarantine(Quarantine quarantine) {
        return quarantineService.addQuarantine(quarantine);
    }

    //按字段查询外诊审批
    public List<Quarantine> getQuarantineByInf(String theName,  String theUnit){
        return quarantineService.getQuarantineByInf(theName , theUnit);
    }

    //查询所有外诊审批
    public List<Quarantine> getAllQuarantine() {
        return quarantineService.getAllQuarantine();
    }
}
