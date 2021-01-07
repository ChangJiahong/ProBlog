package top.pythong.cqupt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import top.pythong.cqupt.app.account.auth.configuration.JsonLoginConfigurer;
import top.pythong.cqupt.app.account.auth.configuration.JwtLoginConfigurer;
import top.pythong.cqupt.app.account.auth.filter.OptionsRequestFilter;
import top.pythong.cqupt.app.account.auth.handler.JsonLoginSuccessHandler;
import top.pythong.cqupt.app.account.auth.handler.JwtRefreshSuccessHandler;
import top.pythong.cqupt.app.account.auth.handler.TokenClearLogoutHandler;
import top.pythong.cqupt.app.account.auth.provider.JwtAuthenticationProvider;
import top.pythong.cqupt.app.account.auth.provider.MyDaoAuthenticationProvider;
import top.pythong.cqupt.app.account.service.impl.UserDetailsServiceImpl;
import top.pythong.cqupt.mapper.UserMapper;

import java.util.Arrays;

/**
 * @author ChangJiahong
 * Create By Idea
 * @date 2021/1/6
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserMapper userMapper;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/hello/**").permitAll()
                // 设置路由过滤参数
                .anyRequest().authenticated()
                .and()
                .csrf().disable() //跨站请求伪造 CRSF禁用，因为不使用session
                .sessionManagement().disable()  //禁用session
                .formLogin().disable() //禁用form登录
                .cors()  //支持跨域
                .and()   //添加header设置，支持跨域和ajax请求
                .headers().addHeaderWriter(new StaticHeadersWriter(Arrays.asList(
                //支持所有源的访问
                new Header("Access-control-Allow-Origin","*"),
                //使ajax请求能够取到header中的jwt token信息
                new Header("Access-Control-Expose-Headers","Authorization"))))
                .and() //拦截OPTIONS请求，直接返回header
                .addFilterAfter(new OptionsRequestFilter(), CorsFilter.class)
                //添加登录filter
                .apply(new JsonLoginConfigurer<>()).loginSuccessHandler(jsonLoginSuccessHandler())
                .and()
                //添加token的filter
                .apply(new JwtLoginConfigurer<>()).tokenValidSuccessHandler(jwtRefreshSuccessHandler()).permissiveRequestUrls("/logout")
                .and()
                //使用默认的logoutFilter
                .logout()
                    .logoutUrl("/logout")   //默认就是"/logout"
                    .addLogoutHandler(tokenClearLogoutHandler())  //logout时清除token
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()) //logout成功后返回200
                .and()
                .sessionManagement().disable();
    }

    //配置provider
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider()) // 设置密码登录
                .authenticationProvider(jwtAuthenticationProvider()); // 设置支持token登录
    }

    /**
     *
     */
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    /**
     * token验证处理程序
     */
    @Bean("jwtAuthenticationProvider")
    protected AuthenticationProvider jwtAuthenticationProvider() {
        return new JwtAuthenticationProvider(jwtUserService());
    }

    /**
     * 密码登录处理程序
     */
    @Bean("daoAuthenticationProvider")
    protected AuthenticationProvider daoAuthenticationProvider() throws Exception{
        //这里会默认使用BCryptPasswordEncoder比对加密后的密码，注意要跟createUser时保持一致
        MyDaoAuthenticationProvider daoProvider = new MyDaoAuthenticationProvider();
        daoProvider.setUserDetailsService(userDetailsService());
        daoProvider.setPasswordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder());
        return daoProvider;
    }

    /**
     * 用户
     * @return
     */
    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(userMapper);
    }

    /**
     * 用户服务
     * @return
     */
    @Bean("userService")
    protected UserDetailsServiceImpl jwtUserService() {
        return new UserDetailsServiceImpl(userMapper);
    }

    /**
     * json登录成功处理实例
     * @return
     */
    @Bean
    protected JsonLoginSuccessHandler jsonLoginSuccessHandler() {
        return new JsonLoginSuccessHandler(jwtUserService());
    }

    /**
     * token登录成功加自动刷新处理
     * @return
     */
    @Bean
    protected JwtRefreshSuccessHandler jwtRefreshSuccessHandler() {
        return new JwtRefreshSuccessHandler(jwtUserService());
    }

    @Bean
    protected TokenClearLogoutHandler tokenClearLogoutHandler() {
        return new TokenClearLogoutHandler(jwtUserService());
    }

    /**
     * 跨域配置
     * @return
     */
    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","HEAD", "OPTION"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.addExposedHeader("Authorization");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
