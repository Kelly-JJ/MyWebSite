package com.gjj.website.facaded.service;

import com.gjj.website.facaded.model.MyInfomation;

/**
 * @author :
 * @since 2019/12/26 0:31
 */
public interface MyInfomationService {


    /**
     * 查询详情
     *
     * @param userId
     * @return
     */
    MyInfomation selectOne(Integer userId);

    void updateName(MyInfomation myInfomation);

}
