/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vorpex.crypto;

import java.math.BigInteger;
import java.util.Random;
/**
 *
 * @author M
 * @param <BeLocoCrypto>
 */

public class Crypto
{
    private BigInteger prime;
    private BigInteger publicKey;
    private BigInteger privateKey;
    public BeLocoCrypto RC4Decode;
    
    public void InitRc4(byte[] tKey)
    {
    	RC4Decode = new BeLocoCrypto();
    	RC4Decode.init(tKey);
    }
    
    public void InitDH(BigInteger p, BigInteger generator, String bigrand)
    {
        prime = p;
        privateKey = new BigInteger(bigrand, 16);
        publicKey = generator.modPow(privateKey, prime);
    }
    
    public String generateSharedKey(BigInteger ClientKey)
    {
        return (ClientKey.modPow(privateKey, prime).toString(16)).toUpperCase();
    }
    
    public String getPublicKey()
    {
        return publicKey.toString(10);
    }
        
    public String generateRandomHexString(int len)
    {
        int rand = 0;
        String result = "";

        Random rnd = new Random();

        for (int i = 0; i < len; i++)
        {
            rand =  1 + (int)(rnd.nextDouble() * 254); // 1 - 255
            result += Integer.toString(rand, 16);
        }
        return result;
    }

    public byte[] HextoBytes(String hexString)
    {
        if ((hexString.length() % 2) != 0)
        {
            hexString = ("0" + hexString);
        }
        byte[] bytes = new byte[hexString.length() / 2];
        int j = 0;
        for (int i = 0; i < bytes.length; i++)
        {
            bytes[i] = (byte)Integer.parseInt(hexString.substring(j, j+2), 16);
            j+=2;
        }
        return bytes;
    }
}