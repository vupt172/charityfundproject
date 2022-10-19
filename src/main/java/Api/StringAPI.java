package Api;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class StringAPI {
 public static String generateRandomPassword(int len) {
	 String upperChars="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	 String lowerChars="abcdefghijklmnopqrstuvwxyz";
	 String numbers="0123456789";
	 String combination=upperChars+lowerChars+numbers;
	 char password[]=new char[len];
	 Random  r=new Random();
	 for(int i=0;i<len;i++) {
		 password[i]=combination.charAt(r.nextInt(combination.length()));
	 }
	 return new String(password);
 }
 public static String getRandomNumber(int len) {
	 String numbers="0123456789";
	 Random r=new Random();
	 String result="";
	 for(int i=0;i<len;i++) {
		 result+=numbers.charAt(r.nextInt(numbers.length()));
	 }
	 return result;
 }
 public static String encodePassword(String password) throws NoSuchAlgorithmException {
  return DigestUtils.md5Hex(password);
 }
 public static String getRegisterMailHTML() {
	 String html="";
		html+="<!DOCTYPE html><html><head><meta charset=\"UTF-8\">";
		html+="<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">";
	    html+="<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM\" crossorigin=\"anonymous\"></script>";
		html+="<style >table tr th {padding:5px;}</style></head>";
		html+="<body><div class=\"card\" style=\"width:600px;\">";
	    html+="<div class=\"card-header\">Xin chào bạn đã đăng ký tài khoản thành công trên duantuthien.com</div>";
	    html+="<div class=\"card-body\">";
	    html+="<h3 class=\"card-title text-center\">THÔNG TIN ĐĂNG KÝ TÀI KHOẢN</h3>";
		html+="<table>";
		html+="<tr><th>Username</th><td>Phạm Tuấn Vũ</td></tr>";
		html+="	</div>";
	    html+="</body></html>";
	 // html="<button style=\"background:red\">Hello Vũ</button>";
			
	 return html;
 }
}
