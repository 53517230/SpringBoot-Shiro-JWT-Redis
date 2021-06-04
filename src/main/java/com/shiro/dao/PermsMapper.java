package com.shiro.dao;

import com.shiro.entity.Perms;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 大静
 * @version 1.0
 * @date 2021年04月30日 16:31
 */
@Repository
public interface PermsMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(Perms record);

    Perms selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Perms record);

    List<Perms> queryPerms(Long roleId);
}