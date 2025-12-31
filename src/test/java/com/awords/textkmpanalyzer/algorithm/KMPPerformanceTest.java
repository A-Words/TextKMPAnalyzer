package com.awords.textkmpanalyzer.algorithm;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class KMPPerformanceTest {

    @Test
    void testPerformance() {
        KMPAlgorithm kmpAlgorithm = new KMPAlgorithm();
        int length = 100000; // 10^5
        StringBuilder sb = new StringBuilder(length);
        
        // Generate 100,000 characters mainly "AB"
        // "ABABABAB..."
        for (int i = 0; i < length / 2; i++) {
            sb.append("AB");
        }
        
        // Insert special keyword. 
        // The report says "insert special keywords".
        // Let's insert "ABCDE" near the end.
        String keyword = "ABCDE";
        int insertPos = length - 10;
        sb.replace(insertPos, insertPos + keyword.length(), keyword);
        
        String text = sb.toString();

        // Warm up (optional, but good for stability)
        kmpAlgorithm.findIndices("dummy text", "dummy");

        long startTime = System.nanoTime();
        List<Integer> indices = kmpAlgorithm.findIndices(text, keyword);
        long endTime = System.nanoTime();
        
        long durationMs = (endTime - startTime) / 1_000_000;
        
        System.out.println("Performance Test Results:");
        System.out.println("- Text Length: " + text.length());
        System.out.println("- Keyword: " + keyword);
        System.out.println("- Time taken: " + durationMs + " ms");
        
        assertFalse(indices.isEmpty(), "Should find the keyword");
        assertTrue(indices.contains(insertPos), "Should find keyword at inserted position");
        
        // Report requirement: < 1s
        assertTrue(durationMs < 1000, "Performance should be under 1 second");
    }
}
