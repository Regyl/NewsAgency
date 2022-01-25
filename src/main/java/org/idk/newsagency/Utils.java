package org.idk.newsagency;

import org.idk.newsagency.entity.Authority;
import org.idk.newsagency.entity.User;
import org.idk.newsagency.entity.enumeration.Role;
import org.idk.newsagency.exception.UserHaveNoRightsException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private Utils() {}

    public static User getAuthenticatedUser() {
        return ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
    }

    public static void isUserHaveRights(Role role) {
        User user = getAuthenticatedUser();
        List<Role> roles = user.getAuthorities().stream()
                .map(Authority::getRole)
                .filter((curRole) -> {
            return curRole.equals(role);
        })
                .collect(Collectors.toList());
        if(roles.isEmpty()) {
            throw new UserHaveNoRightsException(role.name());
        }
    }
}
