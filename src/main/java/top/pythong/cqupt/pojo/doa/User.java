package top.pythong.cqupt.pojo.doa;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

public class User implements UserDetails {
    /**
     * 自增主键
     */
    private Integer uid;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱账号
     */
    private String email;

    /**
     * 密码（密文存储）最大长度36
     */
    private String password;

    /**
     * 头像地址
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 性别（1男/0女）
     */
    private Integer sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 创建时间
     */
    private Date created;

    private List<String> roles;

    /**
     * 账户是否过期
     */
    private Boolean isAccountNonExpired;
    /**
     * 账户是否锁定
     */
    private Boolean isAccountNonLocked;
    /**
     * 凭证是否有效
     */
    private Boolean isCredentialsNonExpired;
    /**
     * 是否禁用
     */
    private Boolean isEnabled;


    private static final long serialVersionUID = 1L;

    /**
     * 获取自增主键
     *
     * @return uid - 自增主键
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * 设置自增主键
     *
     * @param uid 自增主键
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取用户名
     *
     * @return username - 用户名
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取邮箱账号
     *
     * @return email - 邮箱账号
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱账号
     *
     * @param email 邮箱账号
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取密码（密文存储）最大长度36
     *
     * @return password - 密码（密文存储）最大长度36
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码（密文存储）最大长度36
     *
     * @param password 密码（密文存储）最大长度36
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取头像地址
     *
     * @return img_url - 头像地址
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置头像地址
     *
     * @param imgUrl 头像地址
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    /**
     * 获取性别（1男/0女）
     *
     * @return sex - 性别（1男/0女）
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别（1男/0女）
     *
     * @param sex 性别（1男/0女）
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 角色信息
     * @return
     */
    public List<String> getRoles() {
        return roles;
    }

    /**
     * 设置角色
     * @param roles
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(roles == null || roles.size()<=0){
            return null;
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
        for(String role:roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Boolean getAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}