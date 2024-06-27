package interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter{
    
	
	//HttpSession에 "authInfo" 속성이 존재할 경우 true를 리턴하고, 그렇지 않으면 리다이렉트 응답을 생성 후 false를 리턴 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		HttpSession session = request.getSession(false);
		if(session != null) {
			Object authInfo = session.getAttribute("authInfo");
			if(authInfo != null) {
				return true;
			}
			
		}
		try {
			response.sendRedirect(request.getContextPath()+"/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	     
	}
}
