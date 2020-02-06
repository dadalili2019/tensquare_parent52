package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author caoqian
 * @ClassName FriendDao
 * @Date 2020/2/6 9:59
 * @Version 1.0
 */
public interface FriendDao extends JpaRepository<Friend, String> {

    public Friend findByUseridAndFriendid(String userid, String friendid);


    @Modifying
    @Query(value = "update tb_friend set islike=? where userid=? and friendid =?", nativeQuery = true)
    public void updateIslike(String islike, String userid, String friendid);

    /**
     * 删除表中userid到friendid这条数据
     * @param userid
     * @param friendid
     */
    @Modifying
    @Query(value = "delete from tb_friend where userid=? and friendid =?", nativeQuery = true)
    void deletefriend(String userid, String friendid);
}
