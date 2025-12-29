package com.awords.textkmpanalyzer;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.awords.textkmpanalyzer.algorithm.KMPAlgorithm;
import com.awords.textkmpanalyzer.io.FileService;

public class TextAnalyzerConsole {

    private final KMPAlgorithm kmpAlgorithm = new KMPAlgorithm();
    private final FileService fileService = new FileService();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        while (true) {
            printMenu();
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createAndSaveFile();
                    break;
                case "2":
                    countKeywordOccurrences();
                    break;
                case "3":
                    queryKeywordIndices();
                    break;
                case "4":
                    System.out.println("退出系统。");
                    return;
                default:
                    System.out.println("非法输入，请输入 1-4 之间的数字。");
            }
            System.out.println(); // 空行分隔
        }
    }

    private void printMenu() {
        System.out.println("========== 主控菜单 ==========");
        System.out.println("1. 创建并保存文本文件");
        System.out.println("2. 统计关键词出现次数");
        System.out.println("3. 查询关键词位置索引");
        System.out.println("4. 退出系统");
        System.out.print("请输入选项编号: ");
    }

    private void createAndSaveFile() {
        System.out.println(">>> 创建并保存文本文件");
        System.out.print("请输入文本内容 (单行字符串，不包含空格): ");
        String content = scanner.nextLine();

        if (content.contains(" ")) {
            System.out.println("错误: 文本内容不能包含空格。");
            return;
        }

        System.out.print("请输入文件名 (例如 data.txt): ");
        String filename = scanner.nextLine();

        try {
            File file = new File(filename);
            fileService.saveTextToFile(file, content);
            System.out.println("成功: 内容已保存到 " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("错误: 保存文件失败 - " + e.getMessage());
        }
    }

    private void countKeywordOccurrences() {
        System.out.println(">>> 统计关键词出现次数");
        System.out.print("请输入要读取的文件名: ");
        String filename = scanner.nextLine();
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("错误: 文件不存在。");
            return;
        }

        System.out.print("请输入关键词: ");
        String keyword = scanner.nextLine();

        try {
            String text = fileService.readTextFromFile(file);
            int count = kmpAlgorithm.countOccurrences(text, keyword);
            System.out.println("结果: 关键词 '" + keyword + "' 出现了 " + count + " 次。");
        } catch (IOException e) {
            System.out.println("错误: 读取文件失败 - " + e.getMessage());
        }
    }

    private void queryKeywordIndices() {
        System.out.println(">>> 查询关键词位置索引");
        System.out.print("请输入要读取的文件名: ");
        String filename = scanner.nextLine();
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("错误: 文件不存在。");
            return;
        }

        System.out.print("请输入关键词: ");
        String keyword = scanner.nextLine();

        try {
            String text = fileService.readTextFromFile(file);
            List<Integer> indices = kmpAlgorithm.findIndices(text, keyword);

            if (indices.isEmpty()) {
                System.out.println("提示: 关键词未出现。");
            } else {
                System.out.println("结果: 关键词 '" + keyword + "' 出现的起始索引位置: " + indices);
            }
        } catch (IOException e) {
            System.out.println("错误: 读取文件失败 - " + e.getMessage());
        }
    }
}
