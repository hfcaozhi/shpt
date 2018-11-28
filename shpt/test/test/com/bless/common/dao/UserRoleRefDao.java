package test.com.bless.common.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bless.ospm.model.base.RoleResourceRef;
import com.bless.ospm.model.base.UserRoleRef;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/com/bless/common/spring/springApplicationContext.xml",
		"/com/bless/*/spring/spring-*-*.xml",
		"/com/bless/common/spring/applicationContext-security.xml" })
public class UserRoleRefDao {
	@Autowired
	com.bless.ospm.dao.UserRoleRefDao userRoleRefDao;

	@Test
	public void thisAlwaysPasses() {

	}

	@Test
//	@ExpectedException(LoginServiceException.class)
	public void find(){
		HashMap map = new HashMap();
		map.put("id", 1L);
		List<UserRoleRef> urr = (List<UserRoleRef>) userRoleRefDao.findByHql("FROM UserRoleRef where user.id=:id ", map, 99999, 1);
		for (UserRoleRef ur : urr) {
			System.out.println(ur.getRole().getId()+";"+ur.getRole().getName());
			System.out.println(ur.getRole().getRoleResourceRefs().size());
			Iterator<RoleResourceRef> it = ur.getRole().getRoleResourceRefs().iterator();
			while(it.hasNext()){
				System.out.println("rrrrr:"+it.next().getResource().getName());
			}
			
		}
	}
}
