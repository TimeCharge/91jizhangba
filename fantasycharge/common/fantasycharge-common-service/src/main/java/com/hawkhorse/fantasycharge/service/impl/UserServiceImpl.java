package com.hawkhorse.fantasycharge.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hawkhorse.fantasycharge.domain.response.Resp;
import com.hawkhorse.fantasycharge.exception.EduException;
import com.hawkhorse.fantasycharge.persistence.mapper.TUserLoginStatusMapper;
import com.hawkhorse.fantasycharge.persistence.mapper.TUserMapper;
import com.hawkhorse.fantasycharge.persistence.model.TUser;
import com.hawkhorse.fantasycharge.persistence.model.TUserExample;
import com.hawkhorse.fantasycharge.persistence.model.TUserLoginStatus;
import com.hawkhorse.fantasycharge.persistence.model.TUserLoginStatusExample;
import com.hawkhorse.fantasycharge.service.IUserService;
import com.hawkhorse.fantasycharge.utils.MD5Utils;

@Service
public class UserServiceImpl implements IUserService {
	
	@Resource
	TUserMapper userMapper;
	
	@Resource
	TUserLoginStatusMapper userLoginStatusMapper;

	@Override
	public Resp<String> register(TUser user) {
		
		TUserExample example = new TUserExample();
		example.createCriteria().andUserNameEqualTo(user.getUserName());
		List<TUser> list = userMapper.selectByExample(example);
		if(null != list && list.size() > 0){
			throw new EduException("该用户名已存在！");
		}
		user.setPassWord(MD5Utils.md5(user.getPassWord()));
		
		TUserExample uexample = new TUserExample();
		uexample.createCriteria().andEquipmentIdEqualTo(user.getEquipmentId());
		userMapper.updateByExampleSelective(user, uexample);
		
		Resp<String> req = new Resp<String>();
		req.setCode(200);
		req.setMsg("操作成功！");
		
		return req;
	}

	@Override
    public Resp<TUserLoginStatus> login(TUser user) {
		TUserExample example = new TUserExample();
		example.createCriteria().andUserNameEqualTo(user.getUserName()).andPassWordEqualTo(MD5Utils.md5(user.getPassWord()));
		List<TUser> list = userMapper.selectByExample(example);
		if(null != list && list.size() > 1){//查询结果大于一条
			throw new EduException("系统错误！存在多个用户！");
		}else if(null == list || list.size() == 0){//查询结果为空
			throw new EduException("用户名或密码错误！");
		}else {
			Resp<TUserLoginStatus> req = new Resp<TUserLoginStatus>();
			req.setCode(200);
			req.setMsg("登录成功！");
			req.setData(getUserToken(list.get(0).getId()));
			
			return req;
			
		}
    }

	@Override
    public Resp<String> logout(TUserLoginStatus loginStatus) {
		TUserLoginStatusExample example = new TUserLoginStatusExample();
		example.createCriteria().andUserIdEqualTo(loginStatus.getUserId()).andTokenEqualTo(loginStatus.getToken());
		userLoginStatusMapper.deleteByExample(example);
		Resp<String> req = new Resp<String>();
		req.setCode(200);
		req.setMsg("登录成功！");
		
		return req;
    }

	@Override
    public Resp<TUserLoginStatus> preRegister(TUser user) {
		TUserExample example = new TUserExample();
		example.createCriteria().andUserNameEqualTo(user.getEquipmentId());
		List<TUser> list = userMapper.selectByExample(example);
		int id = 0;
		if(null == list || list.size() == 0){
			id = userMapper.insertSelective(user);
		}else{
			throw new EduException("系统错误，请联系客服！");
		}
		Resp<TUserLoginStatus> req = new Resp<TUserLoginStatus>();
		req.setCode(200);
		req.setMsg("操作成功！");
		req.setData(getUserToken(id));
		return req;
    }
	
	public TUserLoginStatus getUserToken(int userid){
		TUserLoginStatusExample sexample = new TUserLoginStatusExample();
		sexample.createCriteria().andUserIdEqualTo(userid);
		List<TUserLoginStatus> slist = userLoginStatusMapper.selectByExample(sexample);
		if(null == slist || slist.size() == 0){//查询结果为空
			//生成UUID
			UUID uuid = UUID.randomUUID();
		    
		    TUserLoginStatus uls = new TUserLoginStatus();
		    uls.setToken(uuid.toString().replaceAll("-", ""));
		    uls.setUserId(userid);
		    userLoginStatusMapper.insert(uls);
			
			return uls;
		}else if(null != slist && slist.size() == 1){//查询结果为一条
			//生成UUID
			UUID uuid = UUID.randomUUID();
		    
		    TUserLoginStatus uls = slist.get(0);
		    uls.setToken(uuid.toString().replaceAll("-", ""));
		    userLoginStatusMapper.updateByPrimaryKeySelective(uls);
			
			return uls;
		}else{
			throw new EduException("系统错误,请联系客服！");
		}
	}

}
