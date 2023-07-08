package br.edu.ifg.luziania.model.util;



import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
@Dependent
public class Redirecinar {
    @Inject
    UserSession userSession;

    public String validar(String url) {
        if (userSession.isSessionActive())
            return url;

        return "1";
    }
}
