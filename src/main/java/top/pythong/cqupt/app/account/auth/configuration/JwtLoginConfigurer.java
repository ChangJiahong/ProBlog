package top.pythong.cqupt.app.account.auth.configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import top.pythong.cqupt.app.account.auth.filter.JwtAuthenticationFilter;
import top.pythong.cqupt.app.account.auth.handler.HttpStatusLoginFailureHandler;

/**
 * token登录验证配置
 * @author ChangJiahong
 * Create By Idea
 * @date 2021/1/7
 */
public class JwtLoginConfigurer<T extends JwtLoginConfigurer<T, B>, B extends HttpSecurityBuilder<B>> extends AbstractHttpConfigurer<T, B> {

    private JwtAuthenticationFilter authFilter;

    public JwtLoginConfigurer() {
        this.authFilter = new JwtAuthenticationFilter();
    }

    @Override
    public void configure(B http) throws Exception {
        authFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        authFilter.setAuthenticationFailureHandler(new HttpStatusLoginFailureHandler());

        JwtAuthenticationFilter filter = postProcess(authFilter);
        http.addFilterBefore(filter, LogoutFilter.class);
    }

    public JwtLoginConfigurer<T, B> permissiveRequestUrls(String ... urls){
        authFilter.setPermissiveUrl(urls);
        return this;
    }

    public JwtLoginConfigurer<T, B> tokenValidSuccessHandler(AuthenticationSuccessHandler successHandler){
        authFilter.setAuthenticationSuccessHandler(successHandler);
        return this;
    }
}
