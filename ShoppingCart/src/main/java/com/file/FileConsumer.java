package com.file;

/**
 * 文件处理式函数接口
 */
@FunctionalInterface
public interface FileConsumer {
    public void fileHandler(String fileContent);
}
