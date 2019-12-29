package com.gjj.website.service.mapper;

import com.gjj.website.facaded.model.entity.MyInfomation;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author :
 * @since 2019/12/28 0:19
 */

public interface MyInfomationMapper extends Mapper<MyInfomation> {
    MyInfomation selectOneById(@Param("userId") Integer userId);

    void updateName(MyInfomation myInfomation);

}
