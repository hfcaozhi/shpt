package com.bless.ospm.util;

import java.util.List;
import java.util.Map;
import com.bless.common.cache.CacheSystem;
import com.bless.ospm.model.base.Organization;

public class FullOrgNameUtil {
	private static CacheSystem cacheSystem;
	public static String getFullOrgName(Long orgId){
		cacheSystem =  (CacheSystem) SpringContextUtil.getBean("cacheSystemBean");
		if(null!=orgId){
			//获取所有的organization，以《id,Organization》的形式存储在Map中
			Map<Long, Organization> orgs =  cacheSystem.getOrganization();
			StringBuffer buffer = new StringBuffer();
			String orgName = getOrgName(orgs,orgId,buffer).toString();
			return orgName;
			
		}else{
			return "";
		}
	
	}
	//递归添加信息
	public static StringBuffer getOrgName(Map<Long, Organization> orgs,Long id,StringBuffer buffer){
		if(null==orgs.get(id).getParentId()){
			buffer.insert(0, orgs.get(id).getName());
			return buffer;
				
		}else{
			 buffer.insert(0,"->"+orgs.get(id).getName());
			 getOrgName(orgs,orgs.get(id).getParentId(),buffer);
				
		}
		return  buffer;
	}
}




