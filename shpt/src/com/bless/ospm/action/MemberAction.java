package com.bless.ospm.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bless.common.action.BaseAction;
import com.bless.ospm.dto.MemberDto;
import com.bless.ospm.model.base.Member;
import com.bless.ospm.model.base.User;
import com.bless.ospm.service.MemberService;
import com.bless.ospm.service.UserService;


public class MemberAction extends BaseAction {

	/**
	 * 人员action
	 */
	private static final long serialVersionUID = -8035077973837327699L;
	Logger log = LoggerFactory.getLogger(MemberAction.class);
	private MemberService memberService;
	private UserService userService;
	
	
	private String medicalId;
	private String name;
	/**
	 * 进入人员列表页面
	 * 
	 * @return
	 */
	public String toMember() {
		return SUCCESS;
	} 
	/**
	 * 查询人员列表
	 * 
	 * @throws IOException
	 */
	public void memberJS() throws IOException {
		// 打印信息
		
		log.info("method diseaseCodeList() ");
		Page<MemberDto> page = wrapPage();
		super.setUserService(userService);
		User user=getUserFromSession();//获取当前登录的对象
		String rolename=userService.getRoleName(user.getId().toString());
		String hql="";
		if (rolename.trim().equals("体检医生")) {
			hql = "FROM Member  where 1=1 ";// hql语句
		}
		HashMap proMap = new HashMap();
		if (name!=null&&!name.trim().equals("")) {
			hql+=" and name like :name ";
			proMap.put("name", "%"+name+"%");
		}
		if (medicalId!=null&&!medicalId.trim().equals("")) {
			hql+=" and medicalId =:medicalId";
			proMap.put("medicalId", medicalId);
		}
		
		hql += " and delFlg =0";//删除标记
		// 查询记录
		page = memberService.findByHqlPage(hql, proMap, page);
		List<MemberDto> memberDtos = new ArrayList<MemberDto>();
		// 封装dto
		for (Object c : page.getRs()) {
			Member member=(Member)c;
			MemberDto memberDto=new MemberDto();
			memberDto.setAge(member.getAge());
			memberDto.setId(member.getId());
			memberDto.setMarriage(member.getMarriage());
			memberDto.setMedicalId(member.getMedicalId());
			memberDto.setMobPhone(member.getMobPhone());
			memberDto.setName(member.getName());
			memberDto.setNationality(member.getNationality());
			memberDto.setPatid(member.getPatid());
			memberDto.setSex(member.getSex());
			memberDto.setUnit(member.getUnit());
			memberDtos.add(memberDto);

		}
		page.setRs(memberDtos);
		String message = JSONObject.fromObject(getDataGridMessage(page))
				.toString();

		System.err.println("message=" + message);
		response.getWriter().print(message);
		to_empty();
	}
	
	
	/**
	 * 把传入的参数置空
	 */
	public void to_empty(){
		name=null;
		medicalId=null;
		
	}
	public MemberService getMemberService() {
		return memberService;
	}
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getMedicalId() {
		return medicalId;
	}
	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
