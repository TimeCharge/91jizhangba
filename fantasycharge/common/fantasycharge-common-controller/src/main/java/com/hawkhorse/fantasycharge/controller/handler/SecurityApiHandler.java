package com.hawkhorse.fantasycharge.controller.handler;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hawkhorse.fantasycharge.domain.request.Req;
import com.hawkhorse.fantasycharge.exception.EduException;
import com.hawkhorse.fantasycharge.persistence.mapper.TUserLoginStatusMapper;
import com.hawkhorse.fantasycharge.persistence.model.TUserLoginStatus;
import com.hawkhorse.fantasycharge.persistence.model.TUserLoginStatusExample;
import com.hawkhorse.fantasycharge.service.IBaseService;
import com.hawkhorse.fantasycharge.service.IUserService;

//import com.ancun.utils.StringUtil;

/**
 * 
 *
 * @Created on 2016年1月5日
 * @author sxj
 * @version 1.0
 * @Copyright:
 */
public class SecurityApiHandler extends HandlerInterceptorAdapter{
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Resource
	IBaseService baseService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		String url = request.getRequestURL().toString();  
		log.debug("**********************preHandle start*************************");
		//获取请求体
		String content = IOUtils.toString(request.getInputStream(), "UTF-8");
		if(!url.endsWith("login")&&!url.endsWith("register")&&!url.endsWith("preRegister")){
			JSONObject jsonObj = JSON.parseObject(content);
			boolean validate = baseService.validate(jsonObj.getString("userId"), jsonObj.getString("token"));
			if(!validate){
				log.debug("请求验证失败,userid={},token={}",jsonObj.getString("userId"),jsonObj.getString("token"));
				throw new EduException("请求userid和token验证失败！");
			}
		}
		request.setAttribute("req", content);
		log.debug("**********************preHandle end*************************");
		return true;
	}
	
}