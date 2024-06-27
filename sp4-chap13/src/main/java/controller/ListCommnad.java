package controller;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ListCommnad {
    
	// 지정한 형식의 문자열을 Date 타입으로 변환해준다.
	@DateTimeFormat(pattern="yyyyMMddHH")
	private Date from;
	@DateTimeFormat(pattern="yyyyMMddHH")
	private Date to;
	
	public Date getFrom() {
		return from;
	}
	
	public void setFrom(Date from) {
		this.from = from;
	}
	
	public Date getTo() {
		return to;
	}
	
	public void setTo(Date to) {
		this.to = to;
	}
	
}




