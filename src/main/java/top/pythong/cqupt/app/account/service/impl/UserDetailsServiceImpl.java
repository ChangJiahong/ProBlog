package top.pythong.cqupt.app.account.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.pythong.cqupt.mapper.UserMapper;
import top.pythong.cqupt.pojo.doa.User;

import java.util.Date;

/**
 * 登录验证服务
 * @author ChangJiahong
 * Create By Idea
 * @date 2021/1/6
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();  //默认使用 bcrypt， strength=10
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

    /**
     * 生成Token
     * @param user User
     * @return token
     */
    public String generateToken(User user) {

        String salt = "123456ef"; //BCrypt.gensalt();  // 正式开发时可以调用该方法实时生成加密的salt
        LOGGER.debug("SALT: "+salt);
        /**
         * @todo 将salt保存到数据库或者缓存中
         * redisTemplate.opsForValue().set("token:"+username, salt, 3600, TimeUnit.SECONDS);
         */
        Algorithm algorithm = Algorithm.HMAC256(salt);
        Date date = new Date(System.currentTimeMillis()+3600*1000);  //设置1小时后过期
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public User getUserLoginInfo(String username) {
        String salt = "123456ef";
        /**
         * @todo 从数据库或者缓存中取出jwt token生成时用的salt
         * salt = redisTemplate.opsForValue().get("token:"+username);
         */
        User user = (User) loadUserByUsername(username);
        //将salt放到password字段返回
        user.setPassword(salt);
        return user;
    }


    public void createUser(String username, String password) {
        String encryptPwd = passwordEncoder.encode(password);
        /**
         * @todo 保存用户名和加密后密码到数据库
         */
    }

    /**
     * 登出用户
     * @param username
     */
    public void logout(String username) {
        /**
         * @todo 清除数据库或者缓存中登录salt
         */
    }
}
