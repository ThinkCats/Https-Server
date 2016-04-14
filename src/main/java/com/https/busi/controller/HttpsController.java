package com.https.busi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wanglei25 on 2016/4/14.
 */
@RequestMapping("/doLogic")
@Controller
public class HttpsController {

    @RequestMapping(value = "/todo",method = RequestMethod.GET)
    @ResponseBody
    public String todo(){
        return "hello https";
    }
}
