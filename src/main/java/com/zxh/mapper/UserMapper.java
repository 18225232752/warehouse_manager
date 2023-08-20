package com.zxh.mapper;

import com.zxh.entity.User;
import com.zxh.page.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author taehyang
 * @date 2023/8/12 17:55
 * <p>
 * user_info表的mapper接口
 */
@Mapper
public interface UserMapper {

    // 根据账号查询用户信息
    User findUserByCode(String userCode);

    // 查询用户行数
    Integer findUserRowCount(User user);

    // 分页查询用户
    List<User> findUserByPage(@Param("page") Page page, @Param("user") User user);

    // 添加用户
    int insertUser(User user);

    // 根据用户id修改用户状态
    int setStateByUid(@Param("userId") Integer userId, @Param("userState") String userState);

    // 根据用户ids修改用户删除状态
    int setIsDeleteByUids(List<Integer> userIdList);

    // 修改用户
    int setUserNameByUid(User user);

    // 重置密码
    int setPwdByUid(@Param("userId") Integer userId, @Param("password") String password);
}
