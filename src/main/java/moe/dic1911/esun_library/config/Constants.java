package moe.dic1911.esun_library.config;

import java.util.HashMap;

public class Constants {

    public final static HashMap<String, String> attributes;

    static {
        attributes = new HashMap<>();
        attributes.put("title", "Esun Library");
        attributes.put("env", "dev");
    }


}
