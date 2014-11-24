package cn.gxp;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author wchun
 * 
 * AES128 算法，加密模式为ECB，填充模式为 pkcs7（实际就是pkcs5）
 * 
 *
 */
public class AES2 {

	static final String algorithmStr="AES/ECB/NoPadding";

	static private KeyGenerator keyGen;

	static private Cipher cipher;

	static boolean isInited=false;

	//初始化
	static private void init()
	{

		//初始化keyGen
		try {
			keyGen=KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		keyGen.init(128);

		//初始化cipher
		try {
			cipher=Cipher.getInstance(algorithmStr);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		isInited=true;
	}

	public static byte[] encrypt(byte[] content,byte[] keyBytes)
	{
		byte[] encryptedText=null;

		if(!isInited)//为初始化
		{
			init();
		}

		Key key=new SecretKeySpec(keyBytes,"AES");

		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			encryptedText=cipher.doFinal(content);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encryptedText;
	}

	//解密为byte[]
	public static byte[] decryptToBytes(byte[] content,byte[] keyBytes){
		byte[] originBytes=null;
		if(!isInited){
			init();
		}
		Key key=new SecretKeySpec(keyBytes,"AES");
		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//解密
		try {
			originBytes=cipher.doFinal(content);
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return originBytes;
	}

	/** 
	 * 将16进制转换为二进制 
	 * 
	 * @param hexStr 
	 * @return byte[] 
	 */ 
	public static byte[] parseHexStr2Byte(String hexStr) { 
		if (hexStr.length() < 1) 
			return null; 
		byte[] result = new byte[hexStr.length() / 2]; 
		for (int i = 0; i < hexStr.length() / 2; i++) { 
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16); 
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16); 
			result[i] = (byte) (high * 16 + low); 
		} 
		return result; 
	} 

	/** 
	 * 将二进制转换成16进制 
	 * 
	 * @param buf 
	 * @return String 
	 */ 
	public static String parseByte2HexStr(byte buf[]) { 
		StringBuffer sb = new StringBuffer(); 
		for (int i = 0; i < buf.length; i++) { 
			String hex = Integer.toHexString(buf[i] & 0xFF); 
			if (hex.length() == 1) { 
				hex = '0' + hex; 
			} 
			sb.append(hex.toUpperCase()); 
		} 
		return sb.toString(); 
	} 

	public static byte[] hexStringToBytes(String hexString) {  
		if (hexString == null || hexString.equals("")) {  
			return null;  
		}  
		hexString = hexString.toUpperCase();  
		int length = hexString.length() / 2;  
		char[] hexChars = hexString.toCharArray();  
		byte[] d = new byte[length];  
		for (int i = 0; i < length; i++) {  
			int pos = i * 2;  
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));  
		}  
		return d;  
	}  
	private static byte charToByte(char c) {  
		return (byte) "0123456789ABCDEF".indexOf(c);  
	} 
	public static void main(String[] args){
		try {
			String constr = "F817131A6EEC90F95213226341CEA17A";
			String key = "02060F080B0802040E0F0409090A050C";

			constr = "E4A164FC17F2378089350D038DFD97C7";
			key = "0F0801050D0D030E0701040206050402";

			constr = "E48F17E847992B98A001596ABFB9FAE93E536613B2ECFA7EFB28B9FA53D9E632993020F99AF3E6DC89779C70EB1E1A65";
			key = "02060F080B0802040E0F0409090A050C";

			byte[] midbytes = hexStringToBytes(key);
			byte[] con = hexStringToBytes(constr);
			byte[] aa = decryptToBytes(con,midbytes);
			//String ss = parseByte2HexStr(aa);
			System.out.println("constr1:"+constr);
			String dec=new String(aa,"gbk");
			System.out.println("dec["+dec+"]"+dec.getBytes("gbk").length);

			byte[] bb=encrypt(dec.getBytes("gbk"),midbytes);
			System.out.println("constr2:"+parseByte2HexStr(bb)+"");
			decryptToBytes(bb,midbytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
