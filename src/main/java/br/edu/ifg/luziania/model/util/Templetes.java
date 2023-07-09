package br.edu.ifg.luziania.model.util;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;


@CheckedTemplate

public class Templetes {


    public static native TemplateInstance proibido();

    public static native TemplateInstance login();

    public static native TemplateInstance erro404();

    public static native TemplateInstance Produto();

    public static native TemplateInstance Fornecedor();

    public static native TemplateInstance CadatroDadosEmLote();

    public static native TemplateInstance painel();
    public static native TemplateInstance test();
    public static native TemplateInstance Profile();
    public static native TemplateInstance Cartegoria();


    public static TemplateInstance valores1(String url) {

        return switch (url) {
            case "1" -> login();
            case "2" -> proibido();
            case "3" -> Produto();
            case "4" -> Fornecedor();
            case "5" -> CadatroDadosEmLote();
            case "6" -> painel();
            case "7" -> test();
            case "8" -> Profile();
            case "9" -> Cartegoria();
            default -> erro404();
        };
    }

}

