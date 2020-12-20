package com.hyh.filemerge;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File dir = new File("/Users/huiyh/Downloads/迅雷下载/亲戚弟弟");
        String nameFormat = "1384f91ac6d00{*}.ts";
        int numLength = 4;
        int startNum = 0;
        int endNum = 1058;

        nameFormat = nameFormat.replace("{*}", "%0" + numLength + "d");

        List<File> files = new ArrayList<>();
        for (int i = startNum; i <= endNum; i++){
            String fileName = String.format(nameFormat, i);
            files.add(new File(dir, fileName));
        }

        File destFile = new File(dir, "all.ts");
        mergeFile(files, destFile);
    }

    /**
     * 合并文件List中的多个源文件为一个文件.
     *
     * @param sourceFiles 存放源文件的路径名称字符串的List集合.
     * @param destFile    目标文件路径名称字符串.
     */
    public static void mergeFile(List<File> sourceFiles, File destFile) {
        if (sourceFiles == null || sourceFiles.size()  == 0) {
            return;
        }

        BufferedInputStream in;
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destFile))) {
            System.out.println("源文件列表:");
            byte[] buffer = new byte[1024 * 8];
            for (File sourceFile : sourceFiles) {
                System.out.println(sourceFile.getName());
                in = new BufferedInputStream(new FileInputStream(sourceFile));
                int inSize = -1;
                while ((inSize = in.read(buffer)) != -1) {
                    out.write(buffer, 0, inSize);
                }
                close(in);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(Closeable closeable){
        try{
            closeable.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}