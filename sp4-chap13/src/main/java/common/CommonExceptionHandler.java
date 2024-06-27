package common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("chap13.spring")
public class CommonExceptionHandler {
    
	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException() {
		return "error/commonException";
	}
	
	//ControllerAdvice 적용 시키려면 해당 클래스를 스프링에 빈으로 등록
	//<bean class="chap13.common.CommonExceptionHandler"/>
}
