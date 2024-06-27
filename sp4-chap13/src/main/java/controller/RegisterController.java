package controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spring.AlreadyExistingMemberException;
import spring.MemberRegisterService;
import spring.RegisterRequest;

@Controller
public class RegisterController {
    
	private MemberRegisterService memberRegisterService;
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	

	@RequestMapping("/register/step1")
	public String handleStep1() {
		
		return "register/step1";
		
	}
	// 요청 파라미터 접근
	/*@RequestMapping(value="/register/step2", method= RequestMethod.POST)
	public String handleStep2(HttpServletRequest request, Model model) {
		String agreeParam = request.getParameter("agree");
		if(agreeParam == null || !agreeParam.equals("true")) {
			return "register/step1";
		}
		
		model.addAttribute("registerRequest", new RegisterRequest());
		
		
		return "register/step2";
	}*/
	
	// 요청 파라미터 접근
	@RequestMapping(value="/register/step2")
	public String handleStep2(
			@RequestParam(value="agree", defaultValue="false") Boolean agreeVal, Model model) {
		if(!agreeVal) {
			return "register/step1";
		}
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register/step2";
	}
	
	// redirect: "/" 시작한다면 웹 어플리케이션 기준으로 이동 경로를 생성
	@RequestMapping(value="/register/step2", method= RequestMethod.GET)
	public String handleStep2get() {
		
		return "redirect:/register/step1";
	}
	
	// 해당 코드는 요청하는 파라미터 개수가 증가 할 수록 코드가 길어 질수 있다.
	/*@RequestMapping(value="/register/step3", method = RequestMethod.POST )
	public String handleStep3(HttpServletRequest request) {
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		//..... 
		
		
		return "register/step3";
	}*/
	
	
	
	// 스프링 MVC가 메서드 호출 시 Errors 객체를 생성해서 파라미터로 전달 - getFieldValue() 메서드를 제공 -> 커맨드 객체의 특정 프로퍼티 값을 가져올 수 있다.
	// ValidationUtils.rejectIfEmptyOrWhitespace() 메서드는 커맨드 객체를 전달받지 않아도 Errors 객체를 이용해서 지정한 값을 구할 수 있다.
	// @ModelAttribute("formData")
	@RequestMapping(value="/register/step3")
	public String handleStep3(RegisterRequest regReq, Errors errors) {
		System.out.println("===============진입1==============");
		new RegisterRequestValidator().validate(regReq, errors);
		if(errors.hasErrors()) {
			return "register/step2";
		}
		try {
			System.out.println("===============진입2==============");
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch (AlreadyExistingMemberException ex) {
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}
	}
	
	/*@RequestMapping(value="/register/step3", method=RequestMethod.POST)
	public String handleStep3(@ModelAttribute("formData") RegisterRequest regReq) {
		
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";
		} catch (Exception e) {
			return "register/step2";
		}
	}
	*/
	
	
}
