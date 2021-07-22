package com.resource;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JDK7 之后
 */
public class NewCopyTest {
    @Test
    public void copyFile(){
        String originalUrl = "lib/FileCopyTest.java";
        String targetUrl = "targetTest/target.txt";
        try(//用的是小括号
            FileInputStream originalFileInnputStream =
                    new FileInputStream(originalUrl);
            FileOutputStream targetFileOutputStream =
                    new FileOutputStream(targetUrl);
            ){
            int content;
            //迭代，copy数据
            while((content = originalFileInnputStream.read()) != -1 ){
                targetFileOutputStream.write(content);
            }

        }catch(
                FileNotFoundException e){
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
