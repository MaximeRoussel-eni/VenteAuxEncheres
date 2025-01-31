package application.controller.converter;

import application.bo.Retrait;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToRetraitConverter  implements Converter<String[], Retrait> {

//    @Override
//    public Retrait convert(String rue, String codePostal, String ville) {
//        return null;
//    }



    @Override
    public Retrait convert(String[] source) {
        return null;
    }
}
