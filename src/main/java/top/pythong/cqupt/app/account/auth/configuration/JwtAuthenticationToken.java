package top.pythong.cqupt.app.account.auth.configuration;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.security.auth.Subject;
import java.util.Collection;
import java.util.Collections;

/**
 * token 认证方式
 * @author ChangJiahong
 * Create By Idea
 * @date 2021/1/7
 */
public class JwtAuthenticationToken extends AbstractAuthenticationToken {

    private UserDetails principal;
    private String credentials;
    private DecodedJWT token;

    public JwtAuthenticationToken(DecodedJWT token) {
        super(Collections.emptyList());
        this.token = token;
    }

    public JwtAuthenticationToken(UserDetails principal, DecodedJWT token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.token = token;
    }

    @Override
    public void setDetails(Object details) {
        super.setDetails(details);
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public DecodedJWT getToken() {
        return token;
    }

}
