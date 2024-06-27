package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class ChangePwdCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// 특정 Class가 어떤 클래스/인터페이스를 상속/구현했는지 체크
		// 내가 검증해야 하는 인스턴스의 클래스가 이 밸리데어터가 지원하는지 확인하는 메서드(검증할 수 있는 클래스인지 확인하는 메서드)
	 	return ChangePwdCommand.class.isAssignableFrom(clazz);
	}
    
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required","default message");
		ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
		
	}
    
	
}
