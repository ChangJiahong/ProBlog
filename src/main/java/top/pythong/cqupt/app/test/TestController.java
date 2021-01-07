package top.pythong.cqupt.app.test;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /**
     * 不需要登录
     */
    @GetMapping("/hello")
    String hello(){
        return "Hello";
    }

    /**
     * 需要登录，验证用户权限为ROLE_ADMIN
     */
    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    String admin(){
        return "admin";
    }

    /**
     * 需要登录，验证用户权限为ROLE_USER
     */
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    String user(){
        return "user";
    }

    /**
     * 需要登录，不验证用户权限
     */
    @GetMapping("/test")
    String test(){
        return "test";
    }
}
