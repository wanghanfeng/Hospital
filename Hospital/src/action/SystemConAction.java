package action;

import model.SystemCon;
import service.SystemConService;
import service.impl.SystemConServiceImpl;

/**
 * Created by AceCream on 2017/4/11.
 */
public class SystemConAction {

    SystemConService systemConService = new SystemConServiceImpl();

    //管理员登录
    public SystemCon LoginSystemCon(int sysId, String sysName, String sysPassword) {
        SystemCon systemCon = systemConService.LoginSystemCon(sysId,sysName,sysPassword);
        return systemCon;
    }

    //查重
    public SystemCon loadSystemCon(int theId) {
        SystemCon systemCon = systemConService.loadSystemCon(theId);
        return systemCon;
    }

    //添加
    public int addSystemCon(SystemCon systemCon) {
        int i = systemConService.addSystemCon(systemCon);
        return i;
    }

    public int updateSystemCon(SystemCon systemCon) {
        int i = systemConService.updateSystemCon(systemCon);
        return i;
    }
}
