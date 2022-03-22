package org.idk.newsagency;

import org.idk.newsagency.entity.Authority;
import org.idk.newsagency.entity.enumeration.Role;
import org.idk.newsagency.service.AuthorityService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Component
public class Init {

    private final AuthorityService authorityService;

    public Init(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @PostConstruct
    private void init() {
        List<Authority> authorities = authorityService.findAll();

        if(authorities.size() == 0) {
            for(Role role : Role.values()) {
                Authority authority = new Authority(role);
                authorities.add(authority);
            }
            authorityService.saveAll(authorities);
        }
    }
}
