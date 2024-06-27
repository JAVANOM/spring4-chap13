package spring;

public class AlreadyExistingMemberException extends RuntimeException{

	public AlreadyExistingMemberException(String message) {
		
		System.out.println("alreadyExistingMemberException : " + message);
		
	}

}
