package com.file;

import org.junit.Test;

import java.io.IOException;

public class FileServiceTest {
    @Test
    public void fileHandle() throws IOException {
        FileService fileService = new FileService();
        fileService.fileHandler("/Users/admin/Desktop/ShoppingCart/src/main/java/com/file/FileService.java",fileContent ->
            System.out.println(fileContent));


    }
}
