package com.awords.textkmpanalyzer;

import com.awords.textkmpanalyzer.algorithm.KMPAlgorithm;
import com.awords.textkmpanalyzer.io.FileService;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

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
        String content = inputTextArea != null ? inputTextArea.getText() : "";
        // 文件选择器
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("保存文本");
        Window owner = inputTextArea != null && inputTextArea.getScene() != null ? inputTextArea.getScene().getWindow() : null;
        File file = fileChooser.showSaveDialog(owner);
        if (file == null) {
            setResultMessage("已取消保存操作");
            return;
        }
        try {
            fileService.saveTextToFile(file, content);
            String name = file.getName();
            int dot = name.lastIndexOf('.');
            if (dot > 0) {
                name = name.substring(0, dot);
            }
            setResultMessage("保存成功: " + name);
        } catch (IOException e) {
            setResultMessage("保存失败: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            setResultMessage("参数错误: " + e.getMessage());
        }
    }

    @FXML
    protected void onSearchButtonClick() {
        String text = inputTextArea != null ? inputTextArea.getText() : "";
        String keyword = keywordField != null ? keywordField.getText() : "";

        if (keyword == null || keyword.isBlank()) {
            setResultMessage("请输入关键词后再搜索");
            return;
        }
        if (text == null || text.isBlank()) {
            setResultMessage("请输入或粘贴要分析的文本");
            return;
        }

        int count = kmpAlgorithm.countOccurrences(text, keyword);
        var indices = kmpAlgorithm.findIndices(text, keyword);
        setResultMessage("出现次数: " + count + "，索引: " + indices);
    }

    private void setResultMessage(String message) {
        if (resultLabel != null) {
            resultLabel.setText(message);
        }
    }
}
