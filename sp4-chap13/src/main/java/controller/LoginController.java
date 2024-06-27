package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.AuthInfo;
import spring.AuthService;
import spring.IdPasswordNotMatchingException;

@Controller
@RequestMapping("/login")
public class LoginController {
     
	private AuthService authService;
	
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	// @CookieValue 애노테이션의 value속성은 쿠키의 이름을 지정
	// REMEMBER 이름의 Cookie 타입으로 전달 받게 된다
	@RequestMapping(method=RequestMethod.GET)
	public String form(LoginCommand loginCommand,
		@CookieValue(value = "REMEMBER",required = false) Cookie rCookie) {
		
		if(rCookie != null) {
			loginCommand.setEmail(rCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "login/loginForm";
	}
	
	
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "login/loginForm";
		}
		
		try {
			AuthInfo authInfo = authService.authenticate(
					loginCommand.getEmail(), 
					loginCommand.getPassword());
			// 인증정보 session에 저장
			session.setAttribute("authInfo", authInfo);
			 
			Cookie rememberCookie 
			    = new Cookie("REMEMBER", loginCommand.getEmail());
			// 웹어플리케이션의 모든 URL 범위에서 전송
			rememberCookie.setPath("/"); 
			
			if(loginCommand.isRememberEmail()){
				rememberCookie.setMaxAge(60*60*24*30);
			} else {
				rememberCookie.setMaxAge(0);
			}
			// response(서버→클라이언트), 클라이언트 PC에 쿠키 저장하기
			response.addCookie(rememberCookie);
			
			return "login/loginSuccess";
			
		} catch (IdPasswordNotMatchingException e) {
			errors.reject("idPasswordNotMatching");
			
			return "login/loginForm";
		}
	}
}
