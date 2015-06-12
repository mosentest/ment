package hemu.ment.core.utility;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.util.UUID;

/**
 * Created by muu on 2015/5/27.
 */
public class EncryptionUtil {

	private static final String SALT = "CALABASH_FOX";
	private static final int ITERATION = 2;
	private static final String ALGORITHM = "AES";
	private static final int LENGTH = 128;

	public static final String SESSION_CIPHER = "c41wVyVYTQ/yeYpfJXrIoEh0lvNpCLGITK9CdFwzGvHfvQALNIOkBDJ1pJh7hBM3js+2rIf1yJNS\n" +
			"biEaLolmxQ==";


	public static void main(String[] args) throws Exception {
		System.out.println(generateHexString());
		String password = "123456";
		byte[] key = createKey();
		String encoded = new BASE64Encoder().encode(key);
		String encrypted = encrypt(key, password);
		byte[] decoded = new BASE64Decoder().decodeBuffer(encoded);
		String decrypted = decrypt(decoded, encrypted);
		System.out.println(encoded + "\n");
		System.out.println(encrypted + "\n");
		System.out.println(decrypted + "\n");
	}

	public static String generateAuthToken() {
		return generateHexString();
	}

	public static String generateHexString() {
		UUID uuid = UUID.randomUUID();
		ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
		buffer.putLong(uuid.getMostSignificantBits());
		buffer.putLong(uuid.getLeastSignificantBits());
		return new BASE64Encoder().encode(buffer.array());
	}

	public static boolean unchanged(String originalPassword, String encodedHash, String newPassword) {
		try {
			boolean unchanged = decrypt(encodedHash, newPassword).equals(decrypt(encodedHash, originalPassword));
			return unchanged;
		} catch (Exception e) {
			return false;
		}
	}

	public static String encodeKey(byte[] key) {
		return new BASE64Encoder().encode(key);
	}

	public static byte[] decodeKey(String encodedKey) {
		try {
			byte[] key = new BASE64Decoder().decodeBuffer(encodedKey);
			return key;
		} catch (Exception e) {
			return new byte[0];
		}
	}

	public static String getEncodedKey() {
		return new BASE64Encoder().encode(createKey());
	}

	public static String encrypt(String encodedKey, String data) throws Exception {
		return encrypt(new BASE64Decoder().decodeBuffer(encodedKey), data);
	}

	public static String decrypt(String encodedKey, String encryptedData) throws Exception {
		return decrypt(new BASE64Decoder().decodeBuffer(encodedKey), encryptedData);
	}

	public static byte[] createKey() {
		SecretKey secretKey = null;
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
			keyGenerator.init(LENGTH);
			secretKey = keyGenerator.generateKey();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return secretKey.getEncoded();
	}

	public static String encrypt(byte[] key, String data) {
		if (data == null) {
			return null;
		}
		Cipher cipher = null;
		try {
			SecretKey secretKey = new SecretKeySpec(key, 0, key.length, ALGORITHM);
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String encryptedString = data;
			for (int i = 0; i < ITERATION; i++) {
				String encryptionData = SALT + encryptedString;
				byte[] encryptedData = cipher.doFinal(encryptionData.getBytes());
				encryptedString = new BASE64Encoder().encode(encryptedData);
			}
			return encryptedString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String decrypt(byte[] encodedKey, String encryptedData) {
		if (encryptedData == null) {
			return null;
		}
		Cipher cipher = null;
		try {
			SecretKey secretKey = new SecretKeySpec(encodedKey, 0, encodedKey.length, ALGORITHM);
			cipher = Cipher.getInstance(ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not create cipher");
		}
		try {
			String decryptedString = null;
			String decryptionData = encryptedData;
			for (int i = 0; i < ITERATION; i++) {
				byte[] decodedData = new BASE64Decoder().decodeBuffer(decryptionData);
				byte[] decryptedData = cipher.doFinal(decodedData);
				decryptedString = new String(decryptedData).substring(SALT.length());
				decryptionData = decryptedString;
			}
			return decryptedString;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
