package com.company;

import javax.crypto.SecretKey;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Ejercicio 1.1");
        String text = "QQQQ";
        SecretKey secretKey = Claus.keygenKeyGeneration(128);
        byte[] bytes = Claus.encryptData(secretKey, text.getBytes());
        System.out.println(new String(bytes));

        bytes = Claus.dencryptData(secretKey, bytes);
        System.out.println(new String(bytes));

        System.out.println("Ejercicio 1.2");
        String text1 = "AAAA";
        secretKey = Claus.passwordKeyGeneration("qq",128);
        byte[] bytes1 = Claus.encryptData(secretKey,text1.getBytes());
        System.out.println(new String(bytes1));

        String pas = new Scanner(System.in).nextLine();
        secretKey = Claus.passwordKeyGeneration(pas,128);
        bytes1 = Claus.dencryptData(secretKey, bytes1);
        System.out.println(new String(bytes1));

        System.out.println("Ejercicio 2");

        Path path = Paths.get("/home/dam2a/Baixades/textamagat");
        byte[] textenbytes = Files.readAllBytes(path);
        path = Paths.get("/home/dam2a/Baixades/clausA4.txt");
        String[] strings = new String[10];
        strings = Files.readAllLines(path).toArray(new String[0]);
        System.out.println(strings);



    }
}
