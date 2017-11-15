package edu.umsl.java.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncrypter {
	private static final String secretKey = "UMSLTEAMBASED";
	
	private static SecretKeySpec createSecretKey(char[] password)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] salt = new String("0123456789").getBytes();
		int iterationCount = 40000;
		int keyLength = 128;

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
		PBEKeySpec keySpec = new PBEKeySpec(password, salt, iterationCount, keyLength);
		SecretKey keyTmp = keyFactory.generateSecret(keySpec);
		return new SecretKeySpec(keyTmp.getEncoded(), "AES");
	}

	public static String encrypt(String property, String secretKey)
			throws GeneralSecurityException, UnsupportedEncodingException {
		SecretKeySpec key = createSecretKey(secretKey.toCharArray());
		Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		pbeCipher.init(Cipher.ENCRYPT_MODE, key);
		AlgorithmParameters parameters = pbeCipher.getParameters();
		IvParameterSpec ivParameterSpec = parameters.getParameterSpec(IvParameterSpec.class);
		byte[] cryptoText = pbeCipher.doFinal(property.getBytes("UTF-8"));
		byte[] iv = ivParameterSpec.getIV();
		return base64Encode(iv) + ":" + base64Encode(cryptoText);
	}

	private static String base64Encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	public static String decrypt(String string, String secretKey) throws GeneralSecurityException, IOException {
		SecretKeySpec key = createSecretKey(secretKey.toCharArray());
		String iv = string.split(":")[0];
		String property = string.split(":")[1];
		Cipher pbeCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		pbeCipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(base64Decode(iv)));
		return new String(pbeCipher.doFinal(base64Decode(property)), "UTF-8");
	}

	private static byte[] base64Decode(String property) throws IOException {
		return Base64.getDecoder().decode(property);
	}
	
	public static void main(String[] args) throws Exception {

		String originalPassword = "passwd";
		System.out.println("Original password: " + originalPassword);
		String encryptedPassword = "cxWZFkSghJANnFcUqmEICg==:ilEjD79wiWOgo4kSeRKdPQ==";//encrypt(originalPassword, secretKey);
		System.out.println("Encrypted password: " + encryptedPassword);
		String decryptedPassword = decrypt(encryptedPassword, secretKey);
		System.out.println("Decrypted password: " + decryptedPassword);
	}
}