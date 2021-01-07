package top.pythong.cqupt.mapper;

import org.apache.ibatis.annotations.Param;
import top.pythong.cqupt.pojo.doa.User;
import top.pythong.cqupt.utils.MyMapper;

public interface UserMapper extends MyMapper<User> {

    /**
     * 查询用户 by username
     * @param username
     * @return 用户
     */
    User selectUserByUsername(@Param(value = "username")String username);
}