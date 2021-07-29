package com.example.demo.service.impl;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCodeEnum;
import com.example.demo.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
@Slf4j
@Service("localFileServiceImpl")
public class LocalFileServiceImpl implements FileService {
    /**
     * 存储空间
     */
    private static final String BUCKET = "uploads";
    @Override
    public void upload(InputStream inputStream, String filename) {
        //拼接文件的存储路径
        String storagePath = BUCKET + "/" + filename;

        try(
                //新建一个inputStream的原因是 JDK8 的TWR中不能自动关闭外部资源。
                InputStream innerInputStream = inputStream;

                FileOutputStream outputStream = new FileOutputStream(new File(storagePath))
        ){
            //拷贝缓冲区
            byte[] buffer = new byte[1024];
            //读取文件流长度
            int len;

            //循环读取inputStream中数据写入到outputStream
            while((len = innerInputStream.read(buffer)) > 0){
                outputStream.write(buffer,0,len);
            }

            //冲刷流
            outputStream.flush();


        }catch(Exception e){
            log.error("文件上传失败",e);
            throw new BusinessException(ErrorCodeEnum.FILE_UPLOAD_FAILURE, e);
        }

    }

    @Override
    public void upload(File file) {
        try{
            upload(new FileInputStream(file), file.getName());
        }catch (Exception e){
            log.error("文件上传失败", e);
            throw new BusinessException(
                    ErrorCodeEnum.FILE_UPLOAD_FAILURE,e);
        }

    }
}
