package action;

import model.Drugs;
import service.DrugsService;
import service.impl.DrugsServiceImpl;

import java.util.List;

/**
 * Created by AceCream on 2017/4/15.
 */
public class DrugsAction {

    DrugsService drugsService = new DrugsServiceImpl();

    public List<Drugs> getAllDrugs() {
        List<Drugs> list = drugsService.getAllDrugs();
        return list;
    }


    public Drugs loadDrug(String drugcode) {
        Drugs drugs = drugsService.loadDrug(drugcode);
        return drugs;
    }


    public int addDrugs(Drugs drugs) {
        int i = drugsService.addDrugs(drugs);
        return i;
    }

    public List<Drugs> getDrugsByInf(String theName) {
        List<Drugs> list = drugsService.getDrugsByInf(theName);
        return list;
    }

    public int updateDrugs(Drugs drugs) {
        int i = drugsService.updateDrugs(drugs);
        return i;
    }

    public Drugs getDrugByName(String drug) {
        Drugs drugs = drugsService.getDrugByName(drug);
        return drugs;
    }

    public List<Drugs> getDrugsByTime(String startTime , String endTime) {
        return drugsService.getDrugsByTime(startTime , endTime);
    }

    public int updateALLDrugs(Drugs drugs) {
        return drugsService.updateALLDrugs(drugs);
    }

    public int deleteDrugs(Drugs drugs) {
        return drugsService.deleteDrugs(drugs);
    }


}
