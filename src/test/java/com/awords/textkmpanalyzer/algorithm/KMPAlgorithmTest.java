package com.awords.textkmpanalyzer.algorithm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class KMPAlgorithmTest {

    private KMPAlgorithm kmpAlgorithm;

    @BeforeEach
    void setUp() {
        kmpAlgorithm = new KMPAlgorithm();
    }

    @Test
    void testExample1() {
        String text = "DogCatDogFishDogBird";
        String pattern = "Dog";

        // Test countOccurrences
        int count = kmpAlgorithm.countOccurrences(text, pattern);
        assertEquals(3, count, "Example 1: Count should be 3");

        // Test findIndices
        List<Integer> indices = kmpAlgorithm.findIndices(text, pattern);
        assertNotNull(indices, "Indices list should not be null");
        assertEquals(3, indices.size(), "Example 1: Should find 3 occurrences");
        assertIterableEquals(List.of(0, 6, 13), indices, "Example 1: Indices should match");
    }

    @Test
    void testExample2() {
        String text = "DataStructDataAlgoStruct";
        String pattern = "Struct";

        // Test countOccurrences
        int count = kmpAlgorithm.countOccurrences(text, pattern);
        assertEquals(2, count, "Example 2: Count should be 2");

        // Test findIndices
        List<Integer> indices = kmpAlgorithm.findIndices(text, pattern);
        assertNotNull(indices, "Indices list should not be null");
        assertEquals(2, indices.size(), "Example 2: Should find 2 occurrences");
        assertIterableEquals(List.of(4, 18), indices, "Example 2: Indices should match");
    }
}
