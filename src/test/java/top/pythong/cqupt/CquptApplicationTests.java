package top.pythong.cqupt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class CquptApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void passwordEncoder(){
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String pwd = passwordEncoder.encode("123");
        System.out.printf(pwd);
    }
}
