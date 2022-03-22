package org.idk.newsagency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.idk.newsagency.entity.enumeration.Role;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Authority extends AbstractEntity implements GrantedAuthority {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public String getAuthority() {
        return role.name();
    }
}
