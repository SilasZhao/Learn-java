package com.example.demo.controller;

import com.example.demo.domain.common.ResponseResult;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCodeEnum;
import com.example.demo.service.ExcalExportService;
import com.example.demo.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/files")
@Slf4j
public class FileController  {
    @Resource(name = "localFileServiceImpl")
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult<String> upload(@NotNull MultipartFile file){
          //文件上传
        try{
            fileService.upload(file.getInputStream(), file.getOriginalFilename());
        }catch (Exception e){
            log.error("文件上传失败", e);
            throw new BusinessException(
                    ErrorCodeEnum.FILE_UPLOAD_FAILURE,e);
        }

        return ResponseResult.success(file.getOriginalFilename());
    }
}
