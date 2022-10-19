package Api;

import java.io.File;

import Model.User;

public class UserOperator {
 public static boolean sendRegisterMail(User u,String newPassword,File registerForm) {
	 MailOperator mailOperator=new MailOperator(MailOperator.GMAIL_USERNAME, MailOperator.GMAIL_APP_PASSWORD);
	 try {
		 User user=u;
		 String content=FileOperator.readFile(registerForm);
		 content=content.replaceAll("@username" , u.getUsername());
		 content=content.replaceAll("@password", newPassword);
		 content=content.replaceAll("@sdt", u.getSdt());
		 content=content.replaceAll("@address", u.getDiachi());
		 content=content.replaceAll("@fullname", u.getFullName());
		 content=content.replaceAll("@email", u.getEmail());
		 content=content.replaceAll("@key", String.valueOf(u.getId()));
		 mailOperator.sendMessage(MailOperator.GMAIL_USERNAME,u.getEmail(), "Thư xem thông tin đăng ký và kích hoạt tài khoản duantuthien.com", content);
	}
	 catch(Exception e) {
		 e.printStackTrace();
	 }
	 return true;
 }
public static boolean sendPasswordResetMail(User u,String newPassword) {
	 MailOperator mailOperator=new MailOperator(MailOperator.GMAIL_USERNAME, MailOperator.GMAIL_APP_PASSWORD);
	 String content="Xin chào ,mật khẩu mới cho tài khoản "+u.getUsername()+" là :"+newPassword;
	 mailOperator.sendMessage(MailOperator.GMAIL_USERNAME,u.getEmail(),"Reset Passord On Website DuAnTuThien.com", content);
	return true;
}
}
