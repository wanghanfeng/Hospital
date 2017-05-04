package action;

import model.Stuff;
import service.StuffService;
import service.impl.StuffServiceImpl;

import java.util.List;

/**
 * Created by AceCream on 2017/4/16.
 */
public class StuffAction {

    StuffService stuffService = new StuffServiceImpl();

    public int addStuff(Stuff stuff) {
        int i = stuffService.addStuff(stuff);
        return i;
    }

    public Stuff loadStuff(String stuffcode) {
        Stuff stuff = stuffService.loadStuff(stuffcode);
        return stuff;
    }

    public int updateStuff(Stuff tempStuff) {
        int i = stuffService.updateStuff(tempStuff);
        return i;
    }


    public List<Stuff> getAllStuff() {
        List<Stuff> list = stuffService.getAllStuff();
        return list;
    }


    public List<Stuff> getStuffByInf(String theName) {
        List<Stuff> list = stuffService.getStuffByInf(theName);
        return list;
    }
}
