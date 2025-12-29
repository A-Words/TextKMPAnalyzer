package com.awords.textkmpanalyzer.io;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 文件操作服务类
 * 负责文本文件的读写操作
 */
public class FileService {

    /**
     * 将文本内容保存到指定文件
     * @param file 目标文件
     * @param content 文本内容
     * @throws IOException 如果写入失败
     */
    public void saveTextToFile(File file, String content) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("文件不能为空");
        }
        if (content == null) {
            content = "";
        }
        
        // 使用 BufferedWriter 写入文件，指定 UTF-8 编码
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write(content);
            writer.flush();
        }
    }

    /**
     * 从指定文件中读取文本内容
     * @param file 源文件
     * @return 文件内容字符串
     * @throws IOException 如果读取失败
     */
    public String readTextFromFile(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("文件不能为空");
        }
        if (!file.exists()) {
            throw new FileNotFoundException("文件不存在: " + file.getAbsolutePath());
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException("路径不是有效的文件: " + file.getAbsolutePath());
        }
        
        // 使用 BufferedReader 读取文件，指定 UTF-8 编码
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append(System.lineSeparator()); // 保留换行符
            }
        }
        
        // 移除最后一个多余的换行符（如果有内容）
        if (content.length() > 0) {
            int separatorLength = System.lineSeparator().length();
            content.setLength(content.length() - separatorLength);
        }
        
        return content.toString();
    }
}
