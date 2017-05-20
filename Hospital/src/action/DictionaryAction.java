package action;

import model.Dictionary;
import service.DictionaryService;
import service.impl.DictionaryServiceImpl;

import java.util.List;

/**
 * Created by lp on 2017/5/8.
 */
public class DictionaryAction {

    private DictionaryService dictionaryService = new DictionaryServiceImpl();

    public int addDictionary(Dictionary dictionary) {
        return dictionaryService.addDictionary(dictionary);
    }

    public List<Dictionary> getDictionaryByInf(String theType) {
        return dictionaryService.getDictionaryByInf(theType);
    }
}
