package org.idk.newsagency;

import org.idk.newsagency.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class Utils {

    private Utils() {}

    public static User getAuthenticatedUser() {
        return ((User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
    }
}
