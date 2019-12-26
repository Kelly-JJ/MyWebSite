package com.gjj.website.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gjj.website.facaded.service.MyInfomationService;

/**
 * @author :
 * @since 2019/12/26 0:33
 */
@Service(interfaceClass = MyInfomationService.class)
public class MyInfomationServiceImpl implements MyInfomationService {
    @Override
    public String hello(String str) {

        return str;
    }
}
