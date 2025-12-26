package com.awords.textkmpanalyzer;

import com.awords.textkmpanalyzer.algorithm.KMPAlgorithm;
import com.awords.textkmpanalyzer.io.FileService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TextAnalyzerController {

    // 依赖注入或直接实例化
    private final KMPAlgorithm kmpAlgorithm = new KMPAlgorithm();
    private final FileService fileService = new FileService();

    @FXML
    private TextArea inputTextArea; // 对应FXML中的文本输入区域

    @FXML
    private TextField keywordField; // 对应FXML中的关键词输入框

    @FXML
    private Label resultLabel; // 用于显示结果

    @FXML
    protected void onSaveButtonClick() {
        // TODO: Partner - 调用 fileService.saveTextToFile()
        // 1. 获取 inputTextArea 的内容
        // 2. 弹出文件选择器或指定文件名
        // 3. 保存文件
    }

    @FXML
    protected void onSearchButtonClick() {
        // TODO: Partner - 调用 kmpAlgorithm 进行搜索
        // 1. 获取 keywordField 的内容
        // 2. 读取文件内容 (或者直接使用 inputTextArea 的内容，根据需求)
        // 3. 调用 kmpAlgorithm.countOccurrences() 和 findIndices()
        // 4. 更新 resultLabel 显示结果
    }
}
