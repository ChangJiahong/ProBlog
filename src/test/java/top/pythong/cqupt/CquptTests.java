package top.pythong.cqupt;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ChangJiahong
 * Create By Idea
 * @date 2021/1/7
 */

public class CquptTests {
    @Test
    void passwordEncoder(){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String pwd = passwordEncoder.encode("123");
        System.out.printf("----"+pwd+"---");
    }
}
