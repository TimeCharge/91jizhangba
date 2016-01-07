package com.hawkhorse.fantasycharge.spring;

import org.apache.commons.dbcp.BasicDataSource;

import com.hawkhorse.fantasycharge.utils.DESUtils;


/**
 * 连接数据库密码加解密处理。
 *
 * @Created on 2015年3月11日
 * @author kfc
 * @version 1.0
 * @Copyright:杭州安存网络科技有限公司 Copyright (c) 2015
 */
public class BasicDataSourceDES extends BasicDataSource {
    /** 
     * 将jdbc.properties的加密的密码进行解密。
     * 
     * @param password 解密前的密码
     */
	@Override
    public void setPassword(String password) {
		String dectryptPass = DESUtils.decrypt(password, null);
        super.setPassword(dectryptPass);
    }
	
	public static void main(String[] arg){
		System.out.println(DESUtils.encrypt("root", null));
		System.out.println("===" + DESUtils.decrypt("gVLD60WFnMoFIT+pRMmtUA==", null) + "---");
	}
}
