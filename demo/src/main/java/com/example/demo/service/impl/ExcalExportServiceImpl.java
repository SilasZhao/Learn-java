package com.example.demo.service.impl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.demo.domain.common.PageQuery;
import com.example.demo.domain.common.PageResult;
import com.example.demo.domain.dto.UserDTO;
import com.example.demo.domain.dto.UserExportDTO;
import com.example.demo.domain.dto.UserQueryDTO;
import com.example.demo.service.ExcalExportService;
import com.example.demo.service.FileService;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ExcalExportServiceImpl implements ExcalExportService {

    @Resource(name = "localFileServiceImpl")
    private FileService fileService;
    @Autowired
    private UserService userService;

    private void export(OutputStream outputStream, UserQueryDTO query){
        //step1 需要创建一个EasyExcel导出对象
        ExcelWriter excelWriter = EasyExcelFactory.write(
                outputStream,
                UserExportDTO.class)
                .build();

        //step2 分批加载数据
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();
        pageQuery.setQuery(query);
        pageQuery.setPageSize(2);

        int pageNo=0;
        PageResult<List<UserDTO>> pageResult;

        do{
            //是++pageNo因为初始化值为0
            pageQuery.setPaveNo(++pageNo);
            log.info("开始导出第[{}] 页数据！",pageNo);
            pageResult = userService.query(pageQuery);

            //数据转换：UserDTO转换成UserExportDTO
            final List<UserExportDTO> userExportDTOList = Optional.ofNullable(pageResult.getData())
                    .map(List::stream)
                    .orElseGet(Stream::empty)
                    .map(userDTO -> {
                        UserExportDTO userExportDTO = new UserExportDTO();
                        BeanUtils.copyProperties(userDTO, userExportDTO);
                        return userExportDTO;
                    }).collect(Collectors.toList());
            //step3 导出分批加载的数据
            //将数据写入到不同的sheet页中
            WriteSheet writeSheet = EasyExcelFactory.writerSheet(pageNo, "第" + pageNo + "页").build();

            log.info("结束导出第[{}] 页数据！",pageNo);
            excelWriter.write(userExportDTOList,writeSheet);
            //如果总页数大于当前页，则继续循环
        }while(pageResult.getPageNum()> pageNo);

        //step4 收尾执行finish，才会关闭excel文件流
        excelWriter.finish();
        log.info("完成导出");
    }
    @Override
    public void export(UserQueryDTO query, String filename) {
        //输入流
        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();

        //step1 实现数据导出到excel中
        export(outputStream,query);

        //输入流
        ByteArrayInputStream inputStream =
                new ByteArrayInputStream(outputStream.toByteArray());

        //step2 实现文件上传
        fileService.upload(inputStream,filename);


    }
    @Async("exportServiceExecutor")
    @Override
    public void asynExport(UserQueryDTO query, String filename) {
        export(query,filename);
    }

}
