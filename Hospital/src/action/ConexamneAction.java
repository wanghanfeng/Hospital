package action;

import model.Conexamne;
import service.ConexamneService;
import service.impl.ConexamneServiceImpl;

import java.util.List;

/**
 * Created by lp on 2017/5/7.
 */
public class ConexamneAction {
    ConexamneService conexamneService = new ConexamneServiceImpl();

    public List<Conexamne> getAllConexamne(){
        List<Conexamne> conexamnes = conexamneService.getAllConexamne();
        return conexamnes;
    }

    public int addConexamne(Conexamne conexamne) {
        int i = conexamneService.addConexamne(conexamne);
        return i;
    }

    public List<Conexamne> getConexamneByInf(String theName, String theUnit) {
        List<Conexamne> list = conexamneService.getConexamneByInf(theName , theUnit);
        return list;
    }
}
