package org.idk.newsagency.entity;

import lombok.Data;
import org.idk.newsagency.entity.enumeration.Role;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Authority extends AbstractEntity implements GrantedAuthority {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
