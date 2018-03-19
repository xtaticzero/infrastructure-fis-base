package mx.gob.sat.siat.base.filter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component("springBeanHttpSession")
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS, value="session")
public class SpringBeanHttpSession {
	
	@Autowired
	private HttpSession httpSession;

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

}
