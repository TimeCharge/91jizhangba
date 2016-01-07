package com.hawkhorse.fantasycharge.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hawkhorse.fantasycharge.domain.request.Req;
import com.hawkhorse.fantasycharge.exception.EduException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.util.UrlPathHelper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.Map;

/**
 * 解析controller方法中ReqBody参数
 *
 * @Created on 2016年1月6日
 * @author sxj
 * @version 1.0
 * @Copyright:
 */
public class MyApiHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	private Logger log = LoggerFactory.getLogger(getClass());

	/** @see AccessTokenHandler */

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		if (parameter.getParameterType().equals(Req.class)) {
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
	        ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
	        WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		Req<?> req = null;
		try {
			//获取Req的泛型类型
			ParameterizedType type = (ParameterizedType) parameter.getGenericParameterType();
			Type[] types = type.getActualTypeArguments();
			if (null != types) {
				if (types.length > 1) {
					throw new EduException("系统错误，请联系客服！");
				}
				String clazzName = null;
				Class<?> clazz = null;
				for (Type t : types) {
					clazz = (Class<?>) t;
					clazzName = clazz.getName();
					log.debug("打印Req的泛型类型的clazzName:" + clazzName);
					if (null == clazzName) {
						continue;
					}
				}
				//获取请求体
				String content = (String) request.getAttribute("req");

				req = convertToJsonReqBody(content, clazz);
			}
		} catch(EduException e){
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new EduException("系统错误，请联系客服！");
		}

		return req;
	}

	private <T> Req<T> convertToJsonReqBody(String json, Class<T> clazz) {
		@SuppressWarnings("unchecked")
		Req<T> obj = JSON.parseObject(json, Req.class);
		JSONObject jsonObj = (JSONObject) obj.getData();
		T t = JSON.parseObject(jsonObj.toJSONString(), clazz);
		obj.setData(t);
		return obj;
	}


}
