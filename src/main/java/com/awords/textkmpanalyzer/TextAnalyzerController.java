package com.awords.textkmpanalyzer;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.awords.textkmpanalyzer.algorithm.KMPAlgorithm;
import com.awords.textkmpanalyzer.io.FileService;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TextAnalyzerController {

    private final KMPAlgorithm kmpAlgorithm = new KMPAlgorithm();
    private final FileService fileService = new FileService();

    @FXML
    private TextField contentField;
    @FXML
    private TextField filenameField;
    @FXML
    private TextField keywordField;
    @FXML
    private TextArea resultArea;

    @FXML
    protected void onSaveButtonClick() {
        String content = contentField.getText();
        String filename = filenameField.getText();

        if (content == null || content.trim().isEmpty()) {
            log("Error: Content cannot be empty.");
            return;
        }
        // Requirement: Single line string, no spaces.
        if (content.contains(" ")) {
             log("Warning: Content contains spaces. Requirement specifies no spaces.");
        }
        if (filename == null || filename.trim().isEmpty()) {
            log("Error: Filename cannot be empty.");
            return;
        }

        try {
            File file = new File(filename);
            fileService.saveTextToFile(file, content);
            log("Success: Content saved to " + file.getAbsolutePath());
        } catch (IOException e) {
            log("Error saving file: " + e.getMessage());
        }
    }

    @FXML
    protected void onCountButtonClick() {
        String filename = filenameField.getText();
        String keyword = keywordField.getText();

        if (filename == null || filename.trim().isEmpty()) {
            log("Error: Please specify the filename to read from (in the Filename field).");
            return;
        }
        if (keyword == null || keyword.isEmpty()) {
            log("Error: Keyword cannot be empty.");
            return;
        }

        try {
            File file = new File(filename);
            String text = fileService.readTextFromFile(file);
            int count = kmpAlgorithm.countOccurrences(text, keyword);
            log("Result: Keyword '" + keyword + "' appears " + count + " times.");
        } catch (IOException e) {
            log("Error reading file: " + e.getMessage());
        }
    }

    @FXML
    protected void onFindIndicesButtonClick() {
        String filename = filenameField.getText();
        String keyword = keywordField.getText();

        if (filename == null || filename.trim().isEmpty()) {
            log("Error: Please specify the filename to read from (in the Filename field).");
            return;
        }
        if (keyword == null || keyword.isEmpty()) {
            log("Error: Keyword cannot be empty.");
            return;
        }

        try {
            File file = new File(filename);
            String text = fileService.readTextFromFile(file);
            List<Integer> indices = kmpAlgorithm.findIndices(text, keyword);
            
            if (indices.isEmpty()) {
                log("Result: Keyword '" + keyword + "' not found.");
            } else {
                log("Result: Keyword '" + keyword + "' found at indices: " + indices);
            }
        } catch (IOException e) {
            log("Error reading file: " + e.getMessage());
        }
    }

    @FXML
    protected void onExitButtonClick() {
        log("Exiting system...");
        Platform.exit();
    }

    private void log(String message) {
        resultArea.appendText(message + "\n");
    }
}
