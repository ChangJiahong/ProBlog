package top.pythong.cqupt.app.account.auth.provider;

import jdk.net.SocketFlow;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;
import top.pythong.cqupt.pojo.constant.StatusCode;

/**
 * 自定义密码验证
 * @author ChangJiahong
 * Create By Idea
 * @date 2021/1/6
 */
public class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

    public MyDaoAuthenticationProvider(){
        super();
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            this.logger.debug("Failed to authenticate since no credentials provided");
            throw new BadCredentialsException(this.messages
                    .getMessage("MyDaoAuthenticationProvider.badCredentials", StatusCode.PasswordIsEmpty.msgCN()));
        }
        String presentedPassword = authentication.getCredentials().toString();
        if (!getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
            this.logger.debug("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException(this.messages
                    .getMessage("MyDaoAuthenticationProvider.badCredentials", StatusCode.PasswordMistake.msgCN()));
        }
    }


}
