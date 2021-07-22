package com.guava;

import com.google.common.io.CharSink;
import com.google.common.io.CharSource;
import com.google.common.io.Files;
import kotlin.text.Charsets;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 演示如何运用Source和Sink来对文件进行操作
 */
public class IOTest {
    @Test
    public void copyFile() throws IOException {
        /**
         * 创建对应的Source和Sink
         */
        CharSource charSource = Files.asCharSource(
                new File("lib/FileCopyTest.java"),
                Charsets.UTF_8);
        CharSink charSink = Files.asCharSink(
                new File("targetTest/target.txt"),
                Charsets.UTF_8
        );
        /**
         * copy
         */
        //charSource.copyTo(charSink);

    }
}
