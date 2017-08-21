package com.fofancy.auth.security;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Component
public class TokenAuthenticationRequestMatcher implements RequestMatcher {
    private List<AntPathRequestMatcher> antUrls = new ArrayList<>();

    public TokenAuthenticationRequestMatcher() {
        antUrls.add(new AntPathRequestMatcher("/account/personal-page/content**"));
        antUrls.add(new AntPathRequestMatcher("/account/admin/content**"));
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        for (AntPathRequestMatcher antUrl: antUrls) {
            if(antUrl.matches(request))
                return true;
        }

        return false;
    }
}
