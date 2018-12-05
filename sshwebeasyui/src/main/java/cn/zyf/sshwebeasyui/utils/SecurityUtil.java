package cn.zyf.sshwebeasyui.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 讲明文的字符串加密层密文字符串的工具类
 */
public class SecurityUtil {
	
	public static void main(String[] args) {
		//顶级权限账号
		System.out.println(md5("admin", "admin123"));
	}
	
	//项目中使用的加密的算法，比较流行的算法：MDX(MD2,MD5),SHA-X(SHA-1(40位),SHA-256,384,512)
	public static String md5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");//这个参数就是加密的算法
			md.update(str.getBytes()); // 明文字符串更新到md对象里
			byte[] rs = md.digest(); //做加密计算，计算结果就是密文
			//byte[]转16进制的字符串，BigInteger大整数
			return new BigInteger(1, rs).toString(16);
		} catch (NoSuchAlgorithmException e) {
			//若输入的加密算法不存在，则报错
			//本项目加密算法由代码写死，不存在报错
			e.printStackTrace();
		}
		return null;
	}
	
	//加密深
	public static String md5(String str1,String str2) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");//这个参数就是加密的算法
			md.update(str1.getBytes()); // 明文字符串更新到md对象里
			md.update(str2.getBytes());// 私钥 盐值
			byte[] rs = md.digest(); //做加密计算，计算结果就是密文
			//byte[]转16进制的字符串，BigInteger大整数
			return new BigInteger(1, rs).toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//SHA-1加密算法
	public static String sha1(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(str.getBytes());
			byte[] rs = md.digest();
			StringBuffer sb = new StringBuffer();
			for(byte b:rs) {
				//可以将byte类型转换16进制格式是数字符串
				sb.append(String.format("%02x", b));  //%02x,一个byte类型整数----> 2位十六进制字符来表示
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
}	
