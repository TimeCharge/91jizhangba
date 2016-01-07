package com.hawkhorse.fantasycharge.service;

import com.hawkhorse.fantasycharge.domain.response.Resp;
import com.hawkhorse.fantasycharge.persistence.model.TUser;
import com.hawkhorse.fantasycharge.persistence.model.TUserLoginStatus;

public interface IUserService {

	/**用户注册
	 * @param user
	 * @return
	 */
	public Resp<String> register(TUser user);
	
	/**用户预注册
	 * @param user
	 * @return
	 */
	public Resp<TUserLoginStatus> preRegister(TUser user);
	
	/**用户登录
	 * @param user
	 * @return
	 */
	public Resp<TUserLoginStatus> login(TUser user);
	
	/**用户登出
	 * @param loginStatus
	 * @return
	 */
	public Resp<String> logout(TUserLoginStatus loginStatus);
	
	
}
