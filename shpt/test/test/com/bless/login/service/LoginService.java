package test.com.bless.login.service;

import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bless.common.util.MD5;
import com.bless.ospm.model.base.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/com/bless/common/spring/springApplicationContext.xml","/com/bless/*/spring/spring-*-*.xml","/com/bless/common/spring/applicationContext-security.xml"})
public class LoginService {
	@Autowired
	com.bless.login.service.LoginService service;
	@Test
	public void thisAlwaysPasses() {
		
	}
	@Test
//	@ExpectedException(LoginServiceException.class)
	public void doLogin(){
		User user = new User();
		user.setUserName("admin");
		user.setPassword("admin");
		HttpSession session = new MockHttpSession();
		service.doLogin(user, session);
	}
	@Test
	public void updateLoginInfoStatus(){
		service.updateLoginInfoStatus("1",new Byte("0"));
	}
	
	
}
