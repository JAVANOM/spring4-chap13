package spring;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {
    
	private MemberDao memberDao;
	
	public ChangePasswordService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Transactional
	public void changePassword(String email, String oldPwd, String newPwd) {
		
		//이메일을 사용하여 암호 변경
		Member member = memberDao.selectByEmail(email);
		if(member == null)
		    throw new MemberNotFoundException();	
		
		member.changePassword(oldPwd, newPwd);
		
		memberDao.update(member);
	}
}
