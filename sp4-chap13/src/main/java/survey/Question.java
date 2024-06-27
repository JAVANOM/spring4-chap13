package survey;

import java.util.Collections;
import java.util.List;

public class Question {
    
	private String title;
	private List<String> options;
	
	public Question(String title, List<String> options) {
		this.title = title;
		this.options = options;
	}

	public String getTitle() {
		return title;
	}

	public List<String> getOptions() {
		return options;
	}
	
	// Collections.<String>emptyList() - static CPU와 메모리에 추가 비뵵이 발생하지 않는다, 리턴 final 불변 객체
	public Question(String title) {
		this(title, Collections.<String>emptyList());
	}
	
	public boolean isChoice() {
		return options != null && !options.isEmpty();
	}
	
}
