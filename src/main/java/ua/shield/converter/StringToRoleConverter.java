package ua.shield.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ua.shield.entity.Role;
import ua.shield.enum_.RoleEnum;

@Component
public class StringToRoleConverter implements Converter<String,Role> {
    @Override
    public Role convert(String source) {
        return new Role(RoleEnum.valueOf(source));
    }
}
