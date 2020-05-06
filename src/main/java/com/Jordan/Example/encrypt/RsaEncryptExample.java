package com.Jordan.Example.encrypt;

import org.apache.commons.net.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RsaEncryptExample {
    public static void main(String[] argv) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 2);
        System.out.println("time: " + new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        String src = String.valueOf(calendar.getTime().getTime());
        System.out.println(src);
        String privateKeyString = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAlVTtysisPq9zw8KF8EENy616muwX\n" +
                "1AalN+xVX3xaqtDsqi68DDz9CX9krKFycmriPs5PTgSjVntdCzQYSTRjJwIDAQABAkACU5Fk0F2G\n" +
                "mHsoOJaXmJvbaXd4hGcWoYffaCooU6qh8Jsq+KviurdGnrzK6UeFYULJQ4jm8e2/qa+05qBOrXmp\n" +
                "AiEA3iHCeTG9HC6C5jeiLNFeI6JJULYRmjbAImm5E2S5jpUCIQCsGaN1ZsKSir92RVbrs2h4xo/8\n" +
                "N3V+kfUOWrCfxTFHywIgOlTvNthC6vWj1kThx5cre3wQeKt3L+IUznSo71jSCYkCIQCWoUYZTpzE\n" +
                "iF411VDTsc6hzyFdHuleo796FdR7squEawIgU5uPZrlKpzBhRNQeoKt2ZIT7r3yP8cxbKN7d5Wlv\n" +
                "CGs=";
        System.out.println(privateKeyString);
        String publicKeyString = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJVU7crIrD6vc8PChfBBDcuteprsF9QGpTfsVV98WqrQ\n" +
                "7KouvAw8/Ql/ZKyhcnJq4j7OT04Eo1Z7XQs0GEk0YycCAwEAAQ==\n" +
                "CGs=";

        //私鑰加密、公鑰解密——加密

        KeyFactory keyFactory = null;
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("私鑰加密、公鑰解密——加密 : " + Base64.encodeBase64String(result));
            //私鑰加密、公鑰解密——解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(result);
            System.out.println("私鑰加密、公鑰解密——解密：" + new String(result));
            //公鑰加密、私鑰解密——加密
            x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKeyString));
            keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result = cipher.doFinal(src.getBytes());
            System.out.println("公鑰加密、私鑰解密——加密 : " + Base64.encodeBase64String(result));
            //公鑰加密、私鑰解密——解密
            pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKeyString));
            keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(result);
            System.out.println("公鑰加密、私鑰解密——解密：" + new String(result));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }
}
