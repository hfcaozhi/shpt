package com.bless.ospm.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.sf.json.JSONObject;
import com.bless.common.action.BaseAction;
import com.bless.common.vo.Result;
import com.bless.ospm.dto.HealthIndicatorDto;
import com.bless.ospm.model.base.DiseaseCode;
import com.bless.ospm.model.base.HealthIndicator;
import com.bless.ospm.service.HealthIndicatorService;
/**
 * 疾病代码action
 */
public class HealthIndicatorAction extends BaseAction {
	
	private static final long serialVersionUID = -8035077973837327699L;
	private HealthIndicatorService healthIndicatorService;
	Logger log = LoggerFactory.getLogger(HealthIndicatorAction.class);
	private String id;
	private String name;
	private String maxvalue;
	private String minvalue;
	private String description;
	private String unit;
	
	/**
	 * 进入疾病代码页面
	 * @return
	 */
    public String toHealthIndicator(){
    	return SUCCESS;
    }
	
    /**
	 * 查询疾病代码列表
	 * @throws IOException 
	 */
	public void healthIndicatorJS() throws IOException{
		//打印信息
		log.info("method healthIndicatorJS() ");
		Page<HealthIndicatorDto> page = wrapPage();
		String hql="FROM HealthIndicator  where 1=1 ";//sql语句
		HashMap proMap = new HashMap();
		if (name!=null&&!name.trim().equals("")) {//模糊查询
			proMap.put("name", "%"+name+"%");
			hql+=" and name like :name ";
		}
		if (id!=null&&!id.trim().equals("")) {
			proMap.put("id", id);
			hql+=" and id = :id ";
		}
		hql+=" and status =0";
		//查询记录
		page = healthIndicatorService.findByHqlPage(hql, proMap, page);
		List<HealthIndicatorDto> healthIndicatorDtos = new ArrayList<HealthIndicatorDto>();
		//封装dto
		for (Object c : page.getRs()) {
			HealthIndicator healthIndicator=(HealthIndicator)c;
			HealthIndicatorDto healthIndicatorDto=new HealthIndicatorDto();
			healthIndicatorDto.setId(healthIndicator.getId());
			healthIndicatorDto.setDescription(healthIndicator.getDescription());
			healthIndicatorDto.setName(healthIndicator.getName());
			healthIndicatorDto.setMaxvalue(healthIndicator.getMaxvalue());
			healthIndicatorDto.setMinvalue(healthIndicator.getMinvalue());
			healthIndicatorDto.setUnit(healthIndicator.getUnit());
			healthIndicatorDtos.add(healthIndicatorDto);
			
		}
		page.setRs(healthIndicatorDtos);
		String message = JSONObject.fromObject(getDataGridMessage(page))
		.toString();

          System.err.println("message="+message);
          response.getWriter().print(message);
		
	}
	/**
	 * 添加健康指标
	 * @throws IOException 
	 */
	public void healthIndicatoraddJS() throws IOException{
		
		Result rs = new Result();
		try {
			HealthIndicator healthIndicator=new HealthIndicator();
			healthIndicator.setId(String.valueOf(UUID.randomUUID()));//生成主键
			healthIndicator.setName(name);
			healthIndicator.setDescription(description);
			healthIndicator.setUnit(unit);
			healthIndicator.setMaxvalue(maxvalue);
			healthIndicator.setMinvalue(minvalue);
			healthIndicator.setStatus(Byte.valueOf("0"));
			healthIndicator.setCreateTime(new Date());
			healthIndicator.setModifyTime(new Date());
			log.info("diseaseCodeaddJS() ");
			healthIndicatorService.insert(healthIndicator);
			rs.setMessage("添加成功.");
		} catch (Exception e) {
			e.printStackTrace();
			rs.setStatus(-1);
			rs.setMessage("添加失败,请稍候重试."); 
		}
		
		String message = JSONObject.fromObject(rs).toString();
		log.info(message);
		this.writeMsg(message);
		to_empty();
	}
	 //修改健康指标
	public void healthIndicatorupdateJS() throws IOException{
		log.info("healthIndicatorupdateJS()");
		HealthIndicator healthIndicator=(HealthIndicator) healthIndicatorService.get(HealthIndicator.class, id);
		healthIndicator.setDescription(description);
		healthIndicator.setMaxvalue(maxvalue);
		healthIndicator.setMinvalue(minvalue);
		healthIndicator.setName(name);
		healthIndicator.setUnit(unit);
		healthIndicator.setModifyTime(new Date());
		
		Result rs = new Result();
		try {
			healthIndicatorService.update(healthIndicator);
			rs.setMessage("修改成功.");
		} catch (Exception e) {
			e.printStackTrace();
			
			rs.setStatus(-1);
			rs.setMessage("修改失败,请稍候重试");
		}//打印信息
		String message = JSONObject.fromObject(rs).toString();
		this.writeMsg(message);
		to_empty();
		
	} 
	/**
	 * 删除疾病代码
	 * @return
	 * @throws IOException 
	 */
	public void healthIndicatordeleteJS() throws IOException{
		log.info("healthIndicatordeleteJS()");
		Result rs = new Result();
		try {
			HealthIndicator healthIndicator=(HealthIndicator) healthIndicatorService.get(HealthIndicator.class, id);
			healthIndicator.setStatus((byte) 1);
			this.healthIndicatorService.update(healthIndicator);
			rs.setMessage("删除成功.");
			rs.setStatus(0);
		} catch (RuntimeException e) {
			e.printStackTrace();
			rs.setStatus(-1);
			rs.setMessage("删除失败,请稍候重试.");
		}
		String message = JSONObject.fromObject(rs).toString();
		log.info(message);
		this.writeMsg(message);
		to_empty();
	}
	/**
	 * 查找同名的个数
	 * @throws IOException 
	 */
	public void healthIndicatorcountJS() throws IOException{
		log.info("diseaseCodecountJS()");
		int count = this.healthIndicatorService.isRegItemExist(this.name);
		log.info(count+"");
		response.getWriter().print(count);
		
	}
	/**
	 * 把传入的参数置空
	 */
	public void to_empty(){
		name=null;
		id=null;
		maxvalue=null;
		minvalue=null;
		description=null;
	}
	public HealthIndicatorService getHealthIndicatorService() {
		return healthIndicatorService;
	}
	public void setHealthIndicatorService(
			HealthIndicatorService healthIndicatorService) {
		this.healthIndicatorService = healthIndicatorService;
	}

	public Logger getLog() {
		return log;
	}

	public void setLog(Logger log) {
		this.log = log;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMaxvalue() {
		return maxvalue;
	}

	public void setMaxvalue(String maxvalue) {
		this.maxvalue = maxvalue;
	}

	public String getMinvalue() {
		return minvalue;
	}

	public void setMinvalue(String minvalue) {
		this.minvalue = minvalue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	
	
	
	
}
