package survey;

import java.util.List;

// 설문 항목에 대한 답변과 응답자 정보를 함께 담기위해 사용
public class AnsweredData {
    
	private List<String> responses;
	private Respondent res;
	
	public List<String> getResponses() {
		return responses;
	}
	public void setResponses(List<String> responses) {
		this.responses = responses;
	}
	public Respondent getRes() {
		return res;
	}
	public void setRes(Respondent res) {
		this.res = res;
	}
	
	
	
}
