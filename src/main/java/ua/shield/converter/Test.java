package ua.shield.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.shield.entity.Role;

/**
 * Created by sa on 10.12.17.
 */
//@Component
public class Test implements Converter<Role,String>{

    @Override
    public String convert(Role role) {
        return ""+role.getRole();
    }
}
