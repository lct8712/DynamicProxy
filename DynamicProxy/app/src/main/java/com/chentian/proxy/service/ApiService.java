package com.chentian.proxy.service;

import com.chentian.proxy.annotation.MyGet;
import com.chentian.proxy.annotation.MyPost;
import com.chentian.proxy.annotation.MyQuery;

/**
 * @author chentian
 */
public interface ApiService {

    @MyGet("http://api.gotokeep.com/get")
    void getRunningLog(@MyQuery("id") String id, @MyQuery("type") Integer type);

    @MyPost("http://api.gotokeep.com/post")
    void saveRunningLog(@MyQuery("date") String date, @MyQuery("type") Integer type);

}
