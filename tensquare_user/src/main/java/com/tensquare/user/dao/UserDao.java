package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    /**
     * 根据电话查询用户
     *
     * @param mobile
     * @return
     */
    public User findByMobile(String mobile);


    /**
     * 根据朋友id更新粉丝数
     *
     * @param x        更新数目
     * @param friendid 粉丝id
     */
    @Modifying
    @Query(value = "update tb_user set fanscount=fanscount+? where id =?", nativeQuery = true)
    void updatefanscount(int x, String friendid);


    /**
     * 根据用户id更新关注数
     *
     * @param x      更新数目
     * @param userid 用户id
     */
    @Modifying
    @Query(value = "update tb_user set followcount=followcount+? where id =?", nativeQuery = true)
    void updatefollowcount(int x, String userid);
}
