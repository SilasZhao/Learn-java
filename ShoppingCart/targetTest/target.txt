package com.resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * JDK7之前的文件拷贝功能
 */
public class FileCopyTest {
    public void copyFile(){
        String originalUrl = "2333";
        String targetUrl = "";

        FileInputStream originalFileInnputStream = null;
        FileOutputStream targetFileOutputStream = null;
        try{
            originalFileInnputStream = new FileInputStream(originalUrl);
            targetFileOutputStream = new FileOutputStream(targetUrl);
            int content;
            while((content = originalFileInnputStream.read()) != 1 ){
                targetFileOutputStream.write(content);
            }

        }catch(FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(targetFileOutputStream != null){
                try{
                    targetFileOutputStream.close();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
            if(originalFileInnputStream != null){
                try{
                    originalFileInnputStream.close();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }


    }
}
