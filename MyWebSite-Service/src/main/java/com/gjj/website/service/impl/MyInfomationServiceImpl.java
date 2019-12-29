package com.gjj.website.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gjj.website.facaded.model.entity.MyInfomation;
import com.gjj.website.facaded.service.MyInfomationService;
import com.gjj.website.service.mapper.MyInfomationMapper;

import javax.annotation.Resource;

/**
 * @author :
 * @since 2019/12/26 0:33
 */
@Service(interfaceClass = MyInfomationService.class)
public class MyInfomationServiceImpl implements MyInfomationService {


    @Resource
    private MyInfomationMapper infomationMapper;

    @Override
    public MyInfomation selectOne(Integer userId) {

        return infomationMapper.selectOneById(userId);
    }

    @Override
    public void updateName(MyInfomation myInfomation) {
        infomationMapper.updateName(myInfomation);

    }
}
