package com.awords.textkmpanalyzer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TextAnalyzerConsoleTest {

    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    void setUp() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    @Test
    void testFullApplicationFlow_Example1(@TempDir Path tempDir) {
        // Example 1
        // Content: DogCatDogFishDogBird
        // Keyword: Dog
        
        String filename = tempDir.resolve("testdata1.txt").toString();
        String content = "DogCatDogFishDogBird";
        String keyword = "Dog";

        String input = "1" + System.lineSeparator() + // Select Create File
                       content + System.lineSeparator() + // Content
                       filename + System.lineSeparator() + // Filename
                       "2" + System.lineSeparator() + // Select Count
                       filename + System.lineSeparator() + // Filename
                       keyword + System.lineSeparator() + // Keyword
                       "3" + System.lineSeparator() + // Select Query Indices
                       filename + System.lineSeparator() + // Filename
                       keyword + System.lineSeparator() + // Keyword
                       "4" + System.lineSeparator(); // Exit

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the application
        TextAnalyzerConsole console = new TextAnalyzerConsole();
        console.run();

        // Verify output
        String output = testOut.toString();

        // Check for menu display
        assertTrue(output.contains("========== 主控菜单 =========="));

        // Check for file creation success
        assertTrue(output.contains("成功: 内容已保存到"));

        // Check for count result
        // Expected: 结果: 关键词 'Dog' 出现了 3 次。
        assertTrue(output.contains("结果: 关键词 '" + keyword + "' 出现了 3 次。"));

        // Check for indices result
        // Expected: 结果: 关键词 'Dog' 出现的起始索引位置: [0, 6, 13]
        assertTrue(output.contains("结果: 关键词 '" + keyword + "' 出现的起始索引位置: [0, 6, 13]"));
    }

    @Test
    void testFullApplicationFlow_Example2(@TempDir Path tempDir) {
        // Example 2
        // Content: DataStructDataAlgoStruct
        // Keyword: Struct
        
        String filename = tempDir.resolve("testdata2.txt").toString();
        String content = "DataStructDataAlgoStruct";
        String keyword = "Struct";

        String input = "1" + System.lineSeparator() + // Select Create File
                       content + System.lineSeparator() + // Content
                       filename + System.lineSeparator() + // Filename
                       "2" + System.lineSeparator() + // Select Count
                       filename + System.lineSeparator() + // Filename
                       keyword + System.lineSeparator() + // Keyword
                       "3" + System.lineSeparator() + // Select Query Indices
                       filename + System.lineSeparator() + // Filename
                       keyword + System.lineSeparator() + // Keyword
                       "4" + System.lineSeparator(); // Exit

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Run the application
        TextAnalyzerConsole console = new TextAnalyzerConsole();
        console.run();

        // Verify output
        String output = testOut.toString();

        // Check for count result
        // Expected: 结果: 关键词 'Struct' 出现了 2 次。
        assertTrue(output.contains("结果: 关键词 '" + keyword + "' 出现了 2 次。"));

        // Check for indices result
        // Expected: 结果: 关键词 'Struct' 出现的起始索引位置: [4, 18]
        assertTrue(output.contains("结果: 关键词 '" + keyword + "' 出现的起始索引位置: [4, 18]"));
    }
}
