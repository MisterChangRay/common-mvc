package com.github.misterchangray.controller;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.dao.entity.Constant;
import com.github.misterchangray.dao.entity.ConstantQuery;
import com.github.misterchangray.dao.mapper.ConstantMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 页面常量控制类
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on  3/23/2018.
 */
@Api(tags ="页面常量获取", description = "ConstantController")
@Controller
@RequestMapping("/v1/constant")
public class ConstantController {
    @Autowired
    private ConstantMapper constantMapper;


    @ApiOperation(value = "获取所有常量枚举", notes = "返回所有页面常量枚举，前端做缓存")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse constant(@RequestParam(required = false) Integer pid, @RequestParam(required = false) String shortcut) {
        NormalResponse res = NormalResponse.newInstance();

        ConstantQuery constantQuery = new ConstantQuery();
        ConstantQuery.Criteria criteria = constantQuery.createCriteria();
        criteria.andEnabledEqualTo(DBEnum.TRUE.getCode());
        criteria.andDeletedEqualTo(DBEnum.FALSE.getCode());

        if(null == pid) criteria.andIdEqualTo(pid);
        if(null == shortcut) criteria.andShortcutEqualTo(shortcut);

        List<Constant> constantList = constantMapper.selectByQuery(constantQuery);

        return res;
    }



}
