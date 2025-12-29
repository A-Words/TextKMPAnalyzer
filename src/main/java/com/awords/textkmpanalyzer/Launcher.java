package com.awords.textkmpanalyzer;

import java.util.Arrays;

import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        // Check for GUI mode flag
        boolean isGuiMode = Arrays.asList(args).contains("--gui");

        if (isGuiMode) {
            Application.launch(TextAnalyzerApplication.class, args);
        } else {
            new TextAnalyzerConsole().run();
        }
    }
}
