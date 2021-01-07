package top.pythong.cqupt.app.account.auth.handler;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import top.pythong.cqupt.app.account.service.impl.UserDetailsServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ChangJiahong
 * Create By Idea
 * @date 2021/1/7
 */
public class TokenClearLogoutHandler implements LogoutHandler {

    private UserDetailsServiceImpl jwtUserService;

    public TokenClearLogoutHandler(UserDetailsServiceImpl jwtUserService) {
        this.jwtUserService = jwtUserService;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        clearToken(authentication);
    }

    protected void clearToken(Authentication authentication) {
        if(authentication == null)
            return;
        UserDetails user = (UserDetails)authentication.getPrincipal();
        if(user!=null && user.getUsername()!=null)
            jwtUserService.logout(user.getUsername());
    }
}
