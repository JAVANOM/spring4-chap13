package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.RegisterRequest;

public class RegisterRequestValidator implements Validator {

	
	private static final String emailRegExP = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
					"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private Pattern pattern;
	
	public RegisterRequestValidator() {
		System.out.println("RegisterRequestValidator#new(): " + this);
		pattern = Pattern.compile(emailRegExP);
		
	}
    
	//직접 실행x, 스프링MVC가 자동으로 검증기능을 수행하도록 설정하는 경우 올바르게 구현 필요
	@Override
	public boolean supports(Class<?> clazz) {
		
		return RegisterRequest.class.isAssignableFrom(clazz);
	}
    
	//target 객체는 검사 대상 객체이고, error 갹체는 검사 결과 에러 코드를 설정하기 위한 객체
	//검사 대상 객체의 특정 프로퍼티나 상태가 올바른지 검사하고, 올바르지 않다면 Errros의 rejectValue() 메서드를 이용해서 에러코드를 저장
	@Override
	public void validate(Object target, Errors errors) {
		RegisterRequest regReq = (RegisterRequest)target;
		if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required"); //에러코드 "required" 추가 - JSP에서 에러 코드 출력
		} else {
			Matcher matcher = pattern.matcher(regReq.getEmail());
			if(!matcher.matches()) {
				errors.rejectValue("email", "bad"); //에러코드 "bad" 추가 - JSP에서 에러 코드 출력
			}
		}
		
		//ValidationUtils 객체의 값 검증 코드를 간결하게 작성 할 수 있도록 도와준다.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		if (!regReq.getPassword().isEmpty()) {
			if (!regReq.isPasswordEqualToConfirmPassowrd()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
		
	}
    
	
	
}
