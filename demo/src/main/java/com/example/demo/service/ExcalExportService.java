package com.example.demo.service;

import com.example.demo.domain.dto.UserQueryDTO;

public interface ExcalExportService {
    //同步
    void export(UserQueryDTO query, String filename);
    //异步
    void asynExport(UserQueryDTO query, String filename);
}
