package com.bless.ospm.service.impl;

import com.bless.common.dao.BaseDao;
import com.bless.common.service.impl.BaseServiceImpl;
import com.bless.ospm.dao.MemberDao;
import com.bless.ospm.service.MemberService;
/**
 * 人员数据访问实现类
 * @author admin
 *
 */

public class MemberServiceImpl extends BaseServiceImpl implements MemberService{
	private MemberDao memberDao;
	
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		setBaseDao(memberDao);
	}

	@Override
	public void setBaseDao(BaseDao baseDao) {
		this.dao = baseDao;
	}
	
	
	
}
