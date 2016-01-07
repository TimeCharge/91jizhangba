package com.hawkhorse.fantasycharge.service;

public interface IBaseService {
	
	/**请求验证
	 * @param userid
	 * @param token
	 * @return
	 */
	public boolean validate(String userid,String token);
}
