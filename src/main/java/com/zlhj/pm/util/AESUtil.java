package com.zlhj.pm.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author tzm
 * @since 2020/4/25 13:57
 * @version 1.0
 */
@Slf4j
public class AESUtil {
    private static final String defaultCharset = "UTF-8";
    private static final String KEY_AES = "AES";
    private static final String KEY_MD5 = "MD5";
    private static MessageDigest md5Digest;
    static {
        try {
            md5Digest = MessageDigest.getInstance(KEY_MD5);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage());
        }
    }
    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key 加密密码
     * @return 加密返回内容
     */
    public static String encrypt(String data, String key) {
        return doAES(data, key, Cipher.ENCRYPT_MODE);
    }
    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key 解密密钥
     * @return 解密返回内容
     */
    public static String decrypt(String data, String key) {
        return doAES(data, key, Cipher.DECRYPT_MODE);
    }
    /**
     * 加解密
     *
     * @param data 加解密内容
     * @param key 加解密秘钥
     * @param mode 加解密判断模式
     * @return 加解密后返回内容
     */
    private static String doAES(String data, String key, int mode) {
        try {
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            if (encrypt) {
                content = data.getBytes(defaultCharset);
            } else {
                content = Base64.decodeBase64(data.getBytes());
            }
            SecretKeySpec keySpec = new SecretKeySpec(md5Digest.digest(key.getBytes(defaultCharset))
                    , KEY_AES);
            Cipher cipher = Cipher.getInstance(KEY_AES);// 创建密码器
            cipher.init(mode, keySpec);// 初始化
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                return new String(Base64.encodeBase64(result));
            } else {
                return new String(result, defaultCharset);
            }
        } catch (Exception e) {
            log.error("AESUtil加解密错误：原因{}",e.getMessage());
        }
        return null;
    }
    //加密方法使用示例：
    public static void main(String[] args) throws Exception {
        String key = "A1B2C3D4E5F6G7H8I9G10";
        String data = "{\"name\": \"\",\"valid\": \"\",\"audioNum\": \"\",\"buyNum\": \"\",\"usedNum\": \"\",\"startDate\": \"\",\"endDate\": \"\",\"mid\": \"1\",\"rid\": \"1\",\"code\": \"1\"}";
        String encrypted = AESUtil.encrypt(data, key);
        String decrypted = AESUtil.decrypt(encrypted, key);
        System.out.println("加密后的密文\n" + encrypted);
        String a = URLEncoder.encode(encrypted,"UTF-8");
        String b = URLDecoder.decode(a,"UTF-8");
        System.out.println("加密后的密文转义\n" + a);
        System.out.println("加密后的密文还原转义\n" + b);
        System.out.println("解密后的报文:\n" + decrypted);
    }
}
