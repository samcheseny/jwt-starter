package com.samcheseny.security.dto;

import com.samcheseny.security.enums.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDto extends AuthenticationDto {

    @Size(min = 1, message = "Provide at least one role for the user")
    private Set<Role> roles;

}
