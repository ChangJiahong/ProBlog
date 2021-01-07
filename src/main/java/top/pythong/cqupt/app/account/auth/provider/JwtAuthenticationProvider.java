package top.pythong.cqupt.app.account.auth.provider;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.NonceExpiredException;
import top.pythong.cqupt.app.account.auth.configuration.JwtAuthenticationToken;
import top.pythong.cqupt.app.account.service.impl.UserDetailsServiceImpl;
import top.pythong.cqupt.pojo.doa.User;

import java.util.Calendar;

/**
 * token 验证处理
 * @author ChangJiahong
 * Create By Idea
 * @date 2021/1/7
 */
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsServiceImpl userService;

    public JwtAuthenticationProvider(UserDetailsServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        DecodedJWT jwt = ((JwtAuthenticationToken)authentication).getToken();
        if(jwt.getExpiresAt().before(Calendar.getInstance().getTime()))
            throw new NonceExpiredException("Token expires");
        String username = jwt.getSubject();
        User user = userService.getUserLoginInfo(username);
        if(user == null || user.getPassword()==null)
            throw new NonceExpiredException("Token expires");
        String encryptSalt = user.getPassword();
        try {
            Algorithm algorithm = Algorithm.HMAC256(encryptSalt);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withSubject(username)
                    .build();
            verifier.verify(jwt.getToken());
        } catch (Exception e) {
            throw new BadCredentialsException("JWT token verify fail", e);
        }
        //成功后返回认证信息，filter会将认证信息放入SecurityContext
        JwtAuthenticationToken token = new JwtAuthenticationToken(user, jwt, user.getAuthorities());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
         return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
