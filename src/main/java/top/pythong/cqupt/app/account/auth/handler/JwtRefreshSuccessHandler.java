package top.pythong.cqupt.app.account.auth.handler;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import top.pythong.cqupt.app.account.auth.configuration.JwtAuthenticationToken;
import top.pythong.cqupt.app.account.service.impl.UserDetailsServiceImpl;
import top.pythong.cqupt.pojo.doa.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * token登录成功 自动刷新
 * @author ChangJiahong
 * Create By Idea
 * @date 2021/1/7
 */
public class JwtRefreshSuccessHandler implements AuthenticationSuccessHandler {
    private static final int tokenRefreshInterval = 300;  //刷新间隔5分钟

    private UserDetailsServiceImpl jwtUserService;

    public JwtRefreshSuccessHandler(UserDetailsServiceImpl jwtUserService) {
        this.jwtUserService = jwtUserService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        DecodedJWT jwt = ((JwtAuthenticationToken)authentication).getToken();
        boolean shouldRefresh = shouldTokenRefresh(jwt.getIssuedAt());
        if(shouldRefresh) {
            String newToken = jwtUserService.generateToken((User)authentication.getPrincipal());
            response.setHeader("Authorization", newToken);
        }
    }

    /**
     * // 不是很懂这部分代码
     * @param issueAt Token发行的时间
     * @return 是否需要刷新token
     */
    protected boolean shouldTokenRefresh(Date issueAt){
        LocalDateTime issueTime = LocalDateTime.ofInstant(issueAt.toInstant(), ZoneId.systemDefault());
        return LocalDateTime.now().minusSeconds(tokenRefreshInterval).isAfter(issueTime);
    }
}
