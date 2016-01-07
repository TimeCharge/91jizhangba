package com.hawkhorse.fantasycharge.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hawkhorse.fantasycharge.controller.base.BaseController;
import com.hawkhorse.fantasycharge.domain.request.Req;
import com.hawkhorse.fantasycharge.domain.response.Resp;
import com.hawkhorse.fantasycharge.persistence.model.TUser;
import com.hawkhorse.fantasycharge.persistence.model.TUserLoginStatus;
import com.hawkhorse.fantasycharge.service.IUserService;


/**
 * 
 *
 * @Created on 2016年1月4日
 * @author sxj
 * @version 1.0
 * @Copyright:
 */
@RestController
public class UserController extends BaseController{
	
	@Resource
	IUserService userservice;
	
	@ResponseBody
	@RequestMapping(value = "/register" , method = RequestMethod.POST)
	public Resp<String> register(Req<TUser> req) {
		
		return userservice.register(req.getData());

	}
	
	@ResponseBody
	@RequestMapping(value = "/preRegister" , method = RequestMethod.POST)
	public Resp<TUserLoginStatus> preRegister(Req<TUser> req) {
		
		return userservice.preRegister(req.getData());

	}
	
	@ResponseBody
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	public Resp<TUserLoginStatus> login(Req<TUser> req) {
		
		return userservice.login(req.getData());

	}
	
	@ResponseBody
	@RequestMapping(value = "/logout" , method = RequestMethod.POST)
	public Resp<String> logout(Req<TUserLoginStatus> req) {
		
		return userservice.logout(req.getData());

	}
	
}
