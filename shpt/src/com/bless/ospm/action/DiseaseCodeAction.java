package com.bless.ospm.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.bless.common.action.BaseAction;
import com.bless.common.vo.Result;
import com.bless.ospm.dto.DiseaseCodeDto;
import com.bless.ospm.model.base.DiseaseCode;
import com.bless.ospm.model.base.DiseaseHealthRef;
import com.bless.ospm.model.base.HealthIndicator;
import com.bless.ospm.service.DiseaseCodeService;
import com.bless.ospm.service.DiseaseHealthRefService;
import com.bless.ospm.service.HealthIndicatorService;

public class DiseaseCodeAction extends BaseAction {

	/**
	 * 疾病代码action
	 */
	private static final long serialVersionUID = -8035077973837327699L;
	private String id;
	private String name;
	private String type;
	private String description;
	private String hislist;// 跟疾病有关的健康指标

	Logger log = LoggerFactory.getLogger(DiseaseCodeAction.class);

	private DiseaseCodeService diseaseCodeService;
	private HealthIndicatorService healthIndicatorService;
	private DiseaseHealthRefService diseaseHealthRefService;

	/**
	 * 查询疾病代码列表
	 * 
	 * @throws IOException
	 */
	public void diseaseCodeJS() throws IOException {
		// 打印信息
		log.info("method diseaseCodeList() ");
		Page<DiseaseCodeDto> page = wrapPage();
		String hql = "FROM DiseaseCode  where 1=1 ";// 生气了语句
		HashMap proMap = new HashMap();
		if (name != null && !name.trim().equals("")) {// 模糊查询
			proMap.put("name", "%" + name + "%");
			hql += " and name like :name ";
		}
		if (type != null && !type.trim().equals("")) {
			proMap.put("type", "%" + type + "%");
			hql += " and type like :type ";

		}
		hql += " and status =0";
		// 查询记录
		page = diseaseCodeService.findByHqlPage(hql, proMap, page);
		List<DiseaseCodeDto> diseaseCodeDtos = new ArrayList<DiseaseCodeDto>();
		// 封装dto
		for (Object c : page.getRs()) {
			DiseaseCode diseaseCode = (DiseaseCode) c;
			DiseaseCodeDto diseaseCodeDto = new DiseaseCodeDto();
			diseaseCodeDto.setId(diseaseCode.getId());
			diseaseCodeDto.setDescription(diseaseCode.getDescription());
			diseaseCodeDto.setName(diseaseCode.getName());
			diseaseCodeDto.setType(diseaseCode.getType());
			diseaseCodeDtos.add(diseaseCodeDto);

		}
		page.setRs(diseaseCodeDtos);
		String message = JSONObject.fromObject(getDataGridMessage(page))
				.toString();

		System.err.println("message=" + message);
		response.getWriter().print(message);
		to_empty();

		// this.writeMsg(getDataGridMessage(rs.getPage()));

	}

	/**
	 * 进入疾病代码页面
	 * 
	 * @return
	 */
	public String toDisaseCodePage() {
		return SUCCESS;
	}

	// 修改疾病代码
	public void diseaseCodeUpdateJS() throws IOException {
		log.info("dieseasecodeUpdateJS()");
		DiseaseCode diseaseCode = (DiseaseCode) diseaseCodeService.get(
				DiseaseCode.class, id);
		diseaseCode.setName(name);
		diseaseCode.setType(type);
		diseaseCode.setDescription(description);
		diseaseCode.setModifyTime(new Date());

		Result rs = new Result();
		try {
			diseaseCodeService.update(diseaseCode);
			rs.setMessage("修改成功.");
		} catch (Exception e) {
			e.printStackTrace();

			rs.setStatus(-1);
			rs.setMessage("修改失败,请稍候重试");
		}// 打印信息
		String message = JSONObject.fromObject(rs).toString();
		this.writeMsg(message);
		to_empty();

	}

	/**
	 * 查找同名的个数
	 * 
	 * @throws IOException
	 */
	public void diseaseCodecountJS() throws IOException {
		log.info("diseaseCodecountJS()");
		int count = this.diseaseCodeService.isRegItemExist(this.name);
		log.info(count + "");
		response.getWriter().print(count);
		to_empty();
	}

	/**
	 * 添加疾病代码
	 * 
	 * @return
	 * @throws IOException
	 */
	public void diseaseCodeaddJS() throws IOException {
		Result rs = new Result();
		try {
			DiseaseCode diseaseCode = new DiseaseCode();
			diseaseCode.setId(String.valueOf(UUID.randomUUID()));// 生成主键
			diseaseCode.setName(name);
			diseaseCode.setType(type);
			diseaseCode.setDescription(description);
			diseaseCode.setStatus(Byte.valueOf("0"));
			diseaseCode.setCreateTime(new Date());
			diseaseCode.setModifyTime(new Date());
			log.info("diseaseCodeaddJS() ");
			diseaseCodeService.saveOrUpdate(diseaseCode);
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

	/**
	 * 删除疾病代码
	 * 
	 * @return
	 * @throws IOException
	 */
	public void diseaseCodedeleteJS() throws IOException {
		log.info("diseaseCodedeleteJS()");
		Result rs = new Result();
		try {
			DiseaseCode diseaseCode = (DiseaseCode) diseaseCodeService.get(
					DiseaseCode.class, id);
			// 如果疾病下面有对应的指标则不删除
			if (diseaseCode.getDiseaseHealthRef().size() > 0) {
				rs.setStatus(-1);
				rs.setMessage("该疾病下面有指标，不能删除.");

			} else {
				diseaseCode.setStatus((byte) 1);
				this.diseaseCodeService.update(diseaseCode);
				rs.setMessage("删除成功.");
				rs.setStatus(0);
			}

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
	 * 查询所有的指标
	 * 
	 * @throws Exception
	 */
	public void diseaseCodehisallJS() throws Exception {
		log.info("diseaseCodehisJS()");

		List<HealthIndicator> allHis = null;
		List<HealthIndicator> his = null;
		try {
			// 全部健康指标
			allHis = (List<HealthIndicator>) healthIndicatorService.findlist();
			DiseaseCode diseaseCode = (DiseaseCode) diseaseCodeService.get(DiseaseCode.class, id);//获得疾病
					
			List<DiseaseHealthRef> diseaseHealthRefs = diseaseHealthRefService.getlistByDisId(id);//获得跟疾病有关的指标
					
			his = new ArrayList<HealthIndicator>();
			for (int i = 0; i < diseaseHealthRefs.size(); i++) {
				his.add(diseaseHealthRefs.get(i).getHealthIndicator());
			}

			JSONArray jsonArray = new JSONArray();

			for (int i = 0; i < allHis.size(); i++) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.accumulate("Value", allHis.get(i).getId());
				jsonObject.accumulate("Text", allHis.get(i).getName());
				jsonArray.add(jsonObject);

			}
			for (int i = 0; i < his.size(); i++) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.accumulate("id", his.get(i).getId());
				jsonObject.accumulate("test", his.get(i).getName());
				jsonArray.add(jsonObject);

			}
			response.getWriter().print(jsonArray.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		to_empty();
	}

	/**
	 * 保存疾病跟健康指标的关联
	 * 
	 * @throws Exception
	 */
	public void diseaseCodehisEditJS() throws Exception {
		log.info("diseaseCodehisEditJS()");
		Result rs = new Result();
		HashSet<DiseaseHealthRef> healthIndicators = new HashSet<DiseaseHealthRef>();
		String[] hisids = hislist.split(",");
		try {
			DiseaseCode diseaseCode = (DiseaseCode) diseaseCodeService.get(
					DiseaseCode.class, id);
			for (int i = 0; i < hisids.length; i++) {
				DiseaseHealthRef diseaseHealthRef = diseaseHealthRefService
						.getByhisIdDiseId(hisids[i], id);
				if (diseaseHealthRef == null) {
					diseaseHealthRef = new DiseaseHealthRef();
					diseaseHealthRef.setCreateTime(new Date());
					diseaseHealthRef.setDeseaseCode(new DiseaseCode(id));
					diseaseHealthRef.setHealthIndicator(new HealthIndicator(
							hisids[i]));
					diseaseHealthRef.setId(String.valueOf(UUID.randomUUID()));
					diseaseHealthRef.setModifyTime(new Date());
					diseaseHealthRef.setStatus((byte) 0);
					diseaseHealthRefService.insert(diseaseHealthRef);
				}
				healthIndicators.add(diseaseHealthRef);
			}
			diseaseCode.setDiseaseHealthRef(healthIndicators);
			diseaseCodeService.update(diseaseCode);
			rs.setMessage("保存成功.");
			rs.setStatus(0);
		} catch (Exception e) {
			e.printStackTrace();
			rs.setMessage("保存失败.");
			rs.setStatus(-1);

		}// 打印信息
		String message = JSONObject.fromObject(rs).toString();
		log.info(message);
		this.writeMsg(message);
		to_empty();

	}

	/**
	 * 所有指标模糊查询
	 * 
	 * @throws IOException
	 */
	public void diseaseCodehisallsearchJS() throws IOException {
		log.info("diseaseCodehisallsearchJS()");
		List<HealthIndicator> allHis = null;
		try {
			allHis = (List<HealthIndicator>) healthIndicatorService
					.getbyname_like(name);
			tool_list_string(allHis);
		} catch (Exception e) {
			e.printStackTrace();

		}// 打印信息
		to_empty();

	}

	/**
	 * 疾病关联指标模糊查询
	 * 
	 * @throws IOException
	 */
	public void diseaseCodehissearchJS() throws IOException {
		log.info("diseaseCodehissearchJS()");
		List<DiseaseHealthRef> diseaseHealthRefs = null;
		List<HealthIndicator> list = new ArrayList<HealthIndicator>();
		try {
			diseaseHealthRefs = diseaseHealthRefService
					.getbyname_like(id, name);
			for (int i = 0; i < diseaseHealthRefs.size(); i++) {
				list.add(diseaseHealthRefs.get(i).getHealthIndicator());
			}
			tool_list_string(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		to_empty();

	}

	/**
	 * 把查询出来的list 封装成String
	 * 
	 * @param allHis
	 * @return
	 * @throws IOException
	 */
	public void tool_list_string(List<HealthIndicator> allHis)
			throws IOException {
		JSONArray jsonArray = new JSONArray();

		for (int i = 0; i < allHis.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.accumulate("Value", allHis.get(i).getId());
			jsonObject.accumulate("Test", allHis.get(i).getName());
			jsonArray.add(jsonObject);

		}

		response.getWriter().print(jsonArray.toString());
	}

	/**
	 * 把传入的参数置空
	 */
	public void to_empty() {
		name = null;
		id = null;
		type = null;
		description = null;
	}

	public DiseaseCodeService getDiseaseCodeService() {
		return diseaseCodeService;
	}

	public void setDiseaseCodeService(DiseaseCodeService diseaseCodeService) {
		this.diseaseCodeService = diseaseCodeService;
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

	public void setName(String name) throws UnsupportedEncodingException {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public HealthIndicatorService getHealthIndicatorService() {
		return healthIndicatorService;
	}

	public void setHealthIndicatorService(
			HealthIndicatorService healthIndicatorService) {
		this.healthIndicatorService = healthIndicatorService;
	}

	public DiseaseHealthRefService getDiseaseHealthRefService() {
		return diseaseHealthRefService;
	}

	public void setDiseaseHealthRefService(
			DiseaseHealthRefService diseaseHealthRefService) {
		this.diseaseHealthRefService = diseaseHealthRefService;
	}

	public String getHislist() {
		return hislist;
	}

	public void setHislist(String hislist) {
		this.hislist = hislist;
	}

}
