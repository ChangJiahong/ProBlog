package top.pythong.cqupt.app.account.auth.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import top.pythong.cqupt.app.account.service.impl.UserDetailsServiceImpl;
import top.pythong.cqupt.pojo.doa.User;
import top.pythong.cqupt.pojo.vo.RestResponse;
import top.pythong.cqupt.utils.ResponesUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理
 * @author ChangJiahong
 * Create By Idea
 * @date 2021/1/6
 */
public class JsonLoginSuccessHandler implements AuthenticationSuccessHandler {

    private UserDetailsServiceImpl userDetailsService;

    public JsonLoginSuccessHandler(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //生成token，并把token加密相关信息缓存，具体请看实现类
        User user = (User)authentication.getPrincipal();
        String token = userDetailsService.generateToken(user);
        response.setHeader("Authorization", token);
        // 不返回密码
        user.setPassword("");
        ResponesUtils.print(response, RestResponse.ok(user));
    }

}
