package top.pythong.cqupt.app.account.auth.handler;

import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import top.pythong.cqupt.pojo.constant.StatusCode;
import top.pythong.cqupt.pojo.vo.RestResponse;
import top.pythong.cqupt.utils.ResponesUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 登录失败处理
 *
 * @author ChangJiahong
 * Create By Idea
 * @date 2021/1/6
 */
public class HttpStatusLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        StatusCode statusCode = StatusCode.valueOfCN(exception.getMessage());
        if (statusCode !=null) {
            ResponesUtils.print(response, RestResponse.fail(statusCode));
        }else {
            ResponesUtils.print(response, RestResponse.fail(500,exception.getMessage()));
        }
    }
}
