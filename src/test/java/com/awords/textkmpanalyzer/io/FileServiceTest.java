package com.awords.textkmpanalyzer.io;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * FileService 的单元测试类
 * 测试文件读写操作的各种场景
 */
class FileServiceTest {

    private FileService fileService;

    @BeforeEach
    void setUp() {
        fileService = new FileService();
    }

    /**
     * 测试保存文本到文件 - 正常场景
     */
    @Test
    void testSaveTextToFile_Normal(@TempDir Path tempDir) throws IOException {
        // 准备测试数据
        File testFile = tempDir.resolve("test.txt").toFile();
        String content = "这是测试内容\n第二行内容";

        // 执行保存操作
        fileService.saveTextToFile(testFile, content);

        // 验证文件已创建
        assertTrue(testFile.exists());

        // 验证文件内容正确
        String savedContent = Files.readString(testFile.toPath(), StandardCharsets.UTF_8);
        assertEquals(content, savedContent);
    }

    /**
     * 测试保存空字符串到文件
     */
    @Test
    void testSaveTextToFile_EmptyContent(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("empty.txt").toFile();
        String content = "";

        fileService.saveTextToFile(testFile, content);

        assertTrue(testFile.exists());
        String savedContent = Files.readString(testFile.toPath(), StandardCharsets.UTF_8);
        assertEquals("", savedContent);
    }

    /**
     * 测试保存 null 内容到文件（应该保存为空字符串）
     */
    @Test
    void testSaveTextToFile_NullContent(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("null.txt").toFile();

        fileService.saveTextToFile(testFile, null);

        assertTrue(testFile.exists());
        String savedContent = Files.readString(testFile.toPath(), StandardCharsets.UTF_8);
        assertEquals("", savedContent);
    }

    /**
     * 测试保存文本到 null 文件（应该抛出异常）
     */
    @Test
    void testSaveTextToFile_NullFile() {
        String content = "测试内容";

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> fileService.saveTextToFile(null, content)
        );

        assertEquals("文件不能为空", exception.getMessage());
    }

    /**
     * 测试保存中文内容到文件
     */
    @Test
    void testSaveTextToFile_ChineseContent(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("chinese.txt").toFile();
        String content = "中文测试内容：你好世界！\n第二行：特殊字符 @#$%";

        fileService.saveTextToFile(testFile, content);

        String savedContent = Files.readString(testFile.toPath(), StandardCharsets.UTF_8);
        assertEquals(content, savedContent);
    }

    /**
     * 测试覆盖已存在的文件
     */
    @Test
    void testSaveTextToFile_OverwriteExisting(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("overwrite.txt").toFile();

        // 第一次写入
        fileService.saveTextToFile(testFile, "原始内容");
        // 第二次写入（覆盖）
        String newContent = "新内容";
        fileService.saveTextToFile(testFile, newContent);

        String savedContent = Files.readString(testFile.toPath(), StandardCharsets.UTF_8);
        assertEquals(newContent, savedContent);
    }

    /**
     * 测试从文件读取文本 - 正常场景
     */
    @Test
    void testReadTextFromFile_Normal(@TempDir Path tempDir) throws IOException {
        // 准备测试文件
        File testFile = tempDir.resolve("read.txt").toFile();
        String expectedContent = String.join(System.lineSeparator(),
                "第一行内容",
                "第二行内容",
                "第三行内容");
        Files.writeString(testFile.toPath(), expectedContent, StandardCharsets.UTF_8);

        // 执行读取操作
        String actualContent = fileService.readTextFromFile(testFile);

        // 验证内容正确
        assertEquals(expectedContent, actualContent);
    }

    /**
     * 测试读取空文件
     */
    @Test
    void testReadTextFromFile_EmptyFile(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("empty.txt").toFile();
        Files.writeString(testFile.toPath(), "", StandardCharsets.UTF_8);

        String content = fileService.readTextFromFile(testFile);

        assertEquals("", content);
    }

    /**
     * 测试读取包含中文的文件
     */
    @Test
    void testReadTextFromFile_ChineseContent(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("chinese.txt").toFile();
        String expectedContent = String.join(System.lineSeparator(),
                "中文内容测试",
                "你好，世界！",
                "特殊符号：@#￥%");
        Files.writeString(testFile.toPath(), expectedContent, StandardCharsets.UTF_8);

        String actualContent = fileService.readTextFromFile(testFile);

        assertEquals(expectedContent, actualContent);
    }

    /**
     * 测试读取 null 文件（应该抛出异常）
     */
    @Test
    void testReadTextFromFile_NullFile() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> fileService.readTextFromFile(null)
        );

        assertEquals("文件不能为空", exception.getMessage());
    }

    /**
     * 测试读取不存在的文件（应该抛出异常）
     */
    @Test
    void testReadTextFromFile_NonExistentFile(@TempDir Path tempDir) {
        File nonExistentFile = tempDir.resolve("nonexistent.txt").toFile();

        FileNotFoundException exception = assertThrows(
                FileNotFoundException.class,
                () -> fileService.readTextFromFile(nonExistentFile)
        );

        assertTrue(exception.getMessage().contains("文件不存在"));
    }

    /**
     * 测试读取目录而非文件（应该抛出异常）
     */
    @Test
    void testReadTextFromFile_Directory(@TempDir Path tempDir) {
        File directory = tempDir.toFile();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> fileService.readTextFromFile(directory)
        );

        assertTrue(exception.getMessage().contains("路径不是有效的文件"));
    }

    /**
     * 测试读写流程的完整集成
     */
    @Test
    void testSaveAndReadIntegration(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("integration.txt").toFile();
        String originalContent = String.join(System.lineSeparator(),
                "测试集成",
                "保存后读取",
                "验证内容一致性");

        // 保存内容
        fileService.saveTextToFile(testFile, originalContent);

        // 读取内容
        String readContent = fileService.readTextFromFile(testFile);

        // 验证内容一致
        assertEquals(originalContent, readContent);
    }

    /**
     * 测试保存和读取多行文本
     */
    @Test
    void testSaveAndReadMultilineText(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("multiline.txt").toFile();
        String content = String.join(System.lineSeparator(),
                "第1行",
                "第2行",
                "第3行",
                "第4行",
                "第5行");

        fileService.saveTextToFile(testFile, content);
        String readContent = fileService.readTextFromFile(testFile);

        assertEquals(content, readContent);
    }

    /**
     * 测试保存和读取特殊字符
     */
    @Test
    void testSaveAndReadSpecialCharacters(@TempDir Path tempDir) throws IOException {
        File testFile = tempDir.resolve("special.txt").toFile();
        String content = String.join(System.lineSeparator(),
                "特殊字符测试: !@#$%^&*()_+-={}[]|\\:\";<>?,./",
                "换行\t制表符");

        fileService.saveTextToFile(testFile, content);
        String readContent = fileService.readTextFromFile(testFile);

        assertEquals(content, readContent);
    }
}
