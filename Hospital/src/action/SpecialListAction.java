package action;

import model.SpecialList;
import org.apache.ibatis.annotations.Param;
import service.SpecialListService;
import service.impl.SpecialListServiceImpl;

import java.util.List;

/**
 * Created by lp on 2017/5/8.
 */
public class SpecialListAction {
    private SpecialListService specialListService = new SpecialListServiceImpl();

    public int addSpecialList(SpecialList specialList) {
        return specialListService.addSpecialList(specialList);
    }
    public List<SpecialList> getAllSpecialList() {
        return specialListService.getAllSpecialList();
    }
    public List<SpecialList> getSpecialListByInf(@Param("theName") String theName, @Param("theUnit") String theUnit) {

        return specialListService.getSpecialListByInf(theName , theUnit);
    }
}
