package com.janosgyerik.codereview.kalaj;

import org.junit.Test;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.Assert.assertEquals;

public class EncryptTest {

    private static final byte[] key = "MyDifficultPassw".getBytes();
//    private static final String transformation = "AES/ECB/PKCS5Padding";
    private static final String transformation = "AES";

    public static void encrypt(Serializable object, OutputStream ostream) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        try {
            // Length is 16 byte
            SecretKeySpec sks = new SecretKeySpec(key, transformation);

            // Create cipher
            Cipher cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.ENCRYPT_MODE, sks);
            SealedObject sealedObject = new SealedObject(object, cipher);

            // Wrap the output stream
            CipherOutputStream cos = new CipherOutputStream(ostream, cipher);
            ObjectOutputStream outputStream = new ObjectOutputStream(cos);
            outputStream.writeObject(sealedObject);
            outputStream.close();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
    }

    public static Object decrypt(InputStream istream) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        SecretKeySpec sks = new SecretKeySpec(key, transformation);
        Cipher cipher = Cipher.getInstance(transformation);
        cipher.init(Cipher.DECRYPT_MODE, sks);

        CipherInputStream cipherInputStream = new CipherInputStream(istream, cipher);
        ObjectInputStream inputStream = new ObjectInputStream(cipherInputStream);
        SealedObject sealedObject;
        try {
            sealedObject = (SealedObject) inputStream.readObject();
            return sealedObject.getObject(cipher);
        } catch (ClassNotFoundException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void testEncryptDecryptString() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        // new BufferedOutputStream(new FileOutputStream(path))
        //new BufferedInputStream(new FileInputStream(path))
        String orig = "hello";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        encrypt(orig, baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        assertEquals(orig, decrypt(bais));
    }

    @Test
    public void testEncryptDecryptPerson() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException {
        Person orig = new Person("Jack", 21);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        encrypt(orig, baos);
        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        assertEquals(orig, decrypt(bais));
    }

    static class Person implements Serializable {

        private static final long serialVersionUID = 0;
        private final String name;

        private final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Person person = (Person) o;

            if (age != person.age) {
                return false;
            }
            if (!name.equals(person.name)) {
                return false;
            }

            return true;
        }
        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + age;
            return result;
        }

    }
}
