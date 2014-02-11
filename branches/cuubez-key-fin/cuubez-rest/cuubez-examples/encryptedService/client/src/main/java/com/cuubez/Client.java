package com.cuubez;

import com.cuubez.client.ClientRequest;
import com.cuubez.client.exception.CuubezException;
import com.cuubez.client.param.MediaType;
import com.cuubez.example.Account;
import com.cuubez.key.Utils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * Hello world!
 */
public class Client {

    private final static String HOST_NAME = "10.30.1.99";
    private final static String HOST_PORT = "8080";
    private final static String CONTEXT_ROOT = "encryptedService.server-1.0-beta-1";

    public static void main(String a[]) throws CuubezException, NoSuchAlgorithmException, NoSuchProviderException {
        System.out.println("-----------------------------------------------------------------------------------");
        System.out.println("---------------------------- CLIENT-CODE ----------------------------------");
        System.out.println("-----------------------------------------------------------------------------------");
        run();
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public static void run() throws CuubezException, NoSuchAlgorithmException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());

        String serviceUrl = "http://" + HOST_NAME + ":" + HOST_PORT + "/" + CONTEXT_ROOT
                + "/account";
        ClientRequest request = new ClientRequest(serviceUrl, MediaType.XML);

        request.setPrincipal("userId1");
        String algoName = "ECMQVALGO";
        //String algoName = "ECDHALGO";

        request.exchangeKey(algoName);


        Account account = (Account)request.get(Account.class);
        System.out.println("Name =" +account.getName());
        System.out.println("Description =" +account.getDescription());

        MessageDigest hash = MessageDigest.getInstance("SHA1", "BC");

                   byte[] originatorShared = hash.digest(request.getKeyContext().getSharedSecretKey());

                   System.out.println("Agreed shared secret  (Hash value of actual key on Client side) with "+algoName + "::" + Utils.toHex(originatorShared));

        System.out.println("-----------------------------------------------------------------------------------");
    }


}
