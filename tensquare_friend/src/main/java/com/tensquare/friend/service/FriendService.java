package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author caoqian
 * @ClassName FriendService
 * @Date 2020/2/6 9:05
 * @Version 1.0
 */
@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    /**
     * 添加喜欢的好友
     *
     * @param friendid 好友id
     * @param userid   用户id
     * @return
     */
    public int addFriend(String userid, String friendid) {
        //先判断userid到friendid是否有数据，有就是重复添加，返回0
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if (friend != null) {
            return 0;
        }
        //直接添加好友，让好友表中userid到friend方向的type为0
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriend(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断从friendid到userid是否有数据，如果有，把双方的状态都改为1
        if (friendDao.findByUseridAndFriendid(friendid, userid) != null) {
            //把双方的islike都改成1
            friendDao.updateIslike("1", userid, friendid);
            friendDao.updateIslike("1", friendid, userid);
        }
        return 1;
    }


    /**
     * 添加喜欢的好友
     *
     * @param userid   用户id
     * @param friendid 好友id
     * @return
     */
    public int addNoFriend(String userid, String friendid) {
        //先判断是否已经是非好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userid, friendid);
        if (noFriend != null) {
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriend(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    /**
     * 根据用户id删除好友
     *
     * @param userid   用户id
     * @param friendid 好友id
     */
    public void deleteFriend(String userid, String friendid) {
        //删除表中userid到friendid这条数据
        friendDao.deletefriend(userid, friendid);
        //更新friendid到userid的islike为0
        friendDao.updateIslike("0", friendid, userid);
        //非好友表中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriend(friendid);
        noFriendDao.save(noFriend);
    }
}
