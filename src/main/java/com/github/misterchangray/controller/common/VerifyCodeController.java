package com.github.misterchangray.controller.common;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.utils.MapBuilder;
import com.github.misterchangray.common.utils.VerifyCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 简易的验证码控制器
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 6/1/2018.
 */
@Api(tags ="验证码控制", description = "VerifyCodeController")
@Controller
@RequestMapping("/v1/verifyCode")
public class VerifyCodeController {
    @Autowired
    private HttpSession httpSession;


    /**
     * 将生成的图片验证码设置到 httpSession 的 verifyCode 属性中
     * @param response
     * @return
     */
    @ApiOperation(value = "获取图片验证码", notes = "返回图片验证码的Base64编码")
    @RequestMapping(value = "/img", method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse verifyCodeForImg(HttpServletResponse response) {
        int w = 200, h = 80; //图片宽高
        int limit = 4; //验证码长度

        MapBuilder mapBuilder = null;
        try {
            mapBuilder = VerifyCodeUtils.outputVerifyImage(w, h, limit);
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpSession.setAttribute("verifyCode", mapBuilder.get("verifyCode"));

        StringBuilder imgBase64 = new StringBuilder("data:image/jpg;base64,");
        imgBase64.append(mapBuilder.get("imgData"));
        return NormalResponse.build().setData(imgBase64.toString());
    }



}
