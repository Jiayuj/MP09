package com.company;

import javax.crypto.BadPaddingException;
import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    //autor Jiayu Jiang
    public static void main(String[] args) throws Exception {
        SecretKey secretKey;
        byte[] dataxifrat;
        byte[] datadesxifrat;
        String data;
        String pas;

        System.out.println("Ejercicio 1.1");
        data = "QQQQ";
        secretKey = Claus.keygenKeyGeneration(128);//genera un clau de 128 mida.
        dataxifrat = Claus.encryptData(secretKey, data.getBytes());//xifrar data con clau que ha generat y queda guardar a Array de bayte.
        System.out.println("Data xifrat; "+new String(dataxifrat));//mostrau data xifrat a pantalla.
        datadesxifrat = Claus.dencryptData(secretKey, dataxifrat);//desxifrar data con clau.
        System.out.println("Data desxifrat: "+new String(datadesxifrat));//traspàs byte a string y mostrau a pantalla.

        System.out.println("Ejercicio 1.2");
        data = "AAAA";
        secretKey = Claus.passwordKeyGeneration("qq",128);//genera un clau de 128 mida amb un contrasenya de text.
        dataxifrat = Claus.encryptData(secretKey, data.getBytes());//cifrar datas con clau que generat amb contrasenya.
        System.out.println("Data xifrat; "+new String(dataxifrat));
        System.out.print("Escriu contrasenya: ");
        pas = new Scanner(System.in).nextLine();//pedir escriu contrasenya.
        secretKey = Claus.passwordKeyGeneration(pas,128);   //volver generat clau amb contrasenya que has introducido.
        try {
            datadesxifrat = Claus.dencryptData(secretKey, dataxifrat);//desxifrar amb clau, si no falla contrasenya se monstra a pantalla dadas desxifrat.
            System.out.println("Data desxifrat: "+new String(datadesxifrat));
        } catch (BadPaddingException passerror) {// si falla contrasenya mosntra error.
            System.out.println("contrasenya falla");
        }

        System.out.println("Ejercicio 2");
        Path path = Paths.get("textamagat");//se agafa dados de fiche.
        dataxifrat = Files.readAllBytes(path);//passar datos a dataxifrat.
        path = Paths.get("clausA4.txt");//llegin fiche de contrasenya.
        String content = Files.readString(path);//guardar totas contrasenyes. //readString solo aparti de JDK 11.

        Scanner srkey = new Scanner(content);//llegin contrasenya una en una.
        secretKey = Claus.passwordKeyGeneration( srkey.nextLine(),128);//genera clau amb primer contreasenya.
        boolean salir =false;
        int i = 0;
        while (!salir) {
            try {
                datadesxifrat = Claus.dencryptData(secretKey, dataxifrat);//si contrasenya es correcto salir de bucle.
                salir=true;
            } catch (BadPaddingException passerror) {
                secretKey = Claus.passwordKeyGeneration(srkey.nextLine(),128);// si es incorrecto genere clau con següent contrasenya
                i++;
            }
        }
        System.out.println("Data desxifrat: "+new String(dataxifrat));
        System.out.println("contrasenya: "+content.split("\n")[i]);
        System.out.println("Data desxifrat: "+new String(datadesxifrat));
    }
}
