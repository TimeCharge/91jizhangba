package com.hawkhorse.fantasycharge.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.hawkhorse.fantasycharge.persistence.mapper.TUserLoginStatusMapper;
import com.hawkhorse.fantasycharge.persistence.model.TUserLoginStatus;
import com.hawkhorse.fantasycharge.persistence.model.TUserLoginStatusExample;
import com.hawkhorse.fantasycharge.service.IBaseService;

public class BaseService implements IBaseService {
	
	@Resource
	TUserLoginStatusMapper userLoginStatusMapper;

	@Override
	public boolean validate(String userid, String token) {
		TUserLoginStatusExample sexample = new TUserLoginStatusExample();
		sexample.createCriteria().andUserIdEqualTo(Integer.parseInt(userid)).andTokenEqualTo(token);
		List<TUserLoginStatus> slist = userLoginStatusMapper.selectByExample(sexample);
		if(null == slist||slist.size() == 0){
			return false;
		}else{
			return true;
		}
		
		
	}

}
