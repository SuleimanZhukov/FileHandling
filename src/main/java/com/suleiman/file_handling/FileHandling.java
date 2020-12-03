package com.suleiman.file_handling;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileHandling {
    public static void main(String[] args) {
        File file = new File("C:/Users/Suleiman/Desktop/Article.txt"); // Put your file's path here.

        readFile(file);

        String text = makeAllLettersCapital(file);
        System.out.println(text);

        int count = countWords(file);
        System.out.println(count);

        String byteCode = changeLettersToBytes(file);
        System.out.println(byteCode);

        zipFile(file, changeFileTypeToZip(file));
    }

    // Reads a file, then prints it
    public static void readFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Makes all letters capital, then returns them as a string
    public static String makeAllLettersCapital(File file) {
        String fileContext = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                fileContext = fileContext.concat(scanner.nextLine().toUpperCase());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileContext;
    }

    // Counts how many words a file has, then return the result as an int
    public static int countWords(File file) {
        int count = 0;
        String fileContext = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                fileContext = fileContext.concat(scanner.nextLine());
                count = fileContext.split("\\s+").length;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return count;
    }

    // Converts to bytes, then returns them as a string
    public static String changeLettersToBytes(File file) {
        String fileContext = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                fileContext = fileContext.concat(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String asByte = Arrays.toString(fileContext.getBytes());
        return asByte;
    }

    // Zips a file
    public static void zipFile(File file, String zipName) {
        byte[] buffer = new byte[1024];
        int len;

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipName))) {
            FileInputStream in = new FileInputStream(file.toPath().toFile());

            ZipEntry zipEntry = new ZipEntry(file.toPath().getFileName().toString());
            zos.putNextEntry(zipEntry);

            len = in.read(buffer);

            if (len > 0) {
                zos.write(buffer,0, len);
            }
            zos.closeEntry();

            System.out.println("Done");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Changes file type to zip
    public static String changeFileTypeToZip(File file) {
        StringBuilder path = new StringBuilder(file.toPath().toString());
        for (int i = path.length() - 1; i >= 0; i--) {
            if (path.charAt(i) == '.') {
                path.append("zip");
                return path.toString();
            }
            path.deleteCharAt(i);
        }
        return path.toString();
    }
}
