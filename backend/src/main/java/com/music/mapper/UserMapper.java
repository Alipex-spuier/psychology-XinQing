package com.music.mapper;

import com.music.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 关注官网：同学邦
 * @since 2023-07-21
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User searchByUsername(String username);
}
