package com.hawkhorse.fantasycharge.controller.base;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hawkhorse.fantasycharge.domain.response.Resp;
import com.hawkhorse.fantasycharge.exception.EduException;

public abstract class BaseController {
	@ExceptionHandler
    public Resp<String> exception(HttpServletRequest request, Exception e) {  
          
		Resp<String>  resp = new Resp<String>();
        // 根据不同的异常类型进行不同处理
        if(e instanceof EduException){ 
        	resp.setCode(((EduException) e).getCode()==0?500:((EduException) e).getCode());
        	resp.setMsg(e.getMessage());
    		resp.setData("");
            return resp;   
        }else{
        	resp.setCode(500);
        	resp.setMsg("系统异常，请联系客服处理！");
        	resp.setData("");
    		e.printStackTrace();
            return resp;
        }
    }  
}
