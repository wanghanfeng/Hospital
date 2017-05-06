package service;

import model.Stuff;

import java.util.List;

/**
 * Created by AceCream on 2017/4/16.
 */
public interface StuffService {

    int addStuff(Stuff stuff);

    Stuff loadStuff(String stuffcode);

    int updateStuff(Stuff tempStuff);

    List<Stuff> getAllStuff();

    List<Stuff> getStuffByInf(String theName);

}
