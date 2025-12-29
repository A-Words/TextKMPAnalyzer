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

    @Test
    void testNullOrEmptyInputs() {
        // Null text
        assertEquals(0, kmpAlgorithm.countOccurrences(null, "abc"));
        assertTrue(kmpAlgorithm.findIndices(null, "abc").isEmpty());

        // Null pattern
        assertEquals(0, kmpAlgorithm.countOccurrences("abc", null));
        assertTrue(kmpAlgorithm.findIndices("abc", null).isEmpty());

        // Empty text
        assertEquals(0, kmpAlgorithm.countOccurrences("", "abc"));
        assertTrue(kmpAlgorithm.findIndices("", "abc").isEmpty());

        // Empty pattern
        assertEquals(0, kmpAlgorithm.countOccurrences("abc", ""));
        assertTrue(kmpAlgorithm.findIndices("abc", "").isEmpty());
    }

    @Test
    void testPatternLongerThanText() {
        String text = "short";
        String pattern = "longerPattern";
        
        assertEquals(0, kmpAlgorithm.countOccurrences(text, pattern));
        assertTrue(kmpAlgorithm.findIndices(text, pattern).isEmpty());
    }

    @Test
    void testNoMatch() {
        String text = "abcdefg";
        String pattern = "xyz";
        
        assertEquals(0, kmpAlgorithm.countOccurrences(text, pattern));
        assertTrue(kmpAlgorithm.findIndices(text, pattern).isEmpty());
    }

    @Test
    void testBoundaryMatches() {
        String text = "prefixContentSuffix";
        
        // Match at start
        String startPattern = "prefix";
        List<Integer> startIndices = kmpAlgorithm.findIndices(text, startPattern);
        assertEquals(1, startIndices.size());
        assertEquals(0, startIndices.get(0));

        // Match at end
        String endPattern = "Suffix";
        List<Integer> endIndices = kmpAlgorithm.findIndices(text, endPattern);
        assertEquals(1, endIndices.size());
        assertEquals(text.length() - endPattern.length(), endIndices.get(0));
    }

    @Test
    void testOverlappingMatches() {
        String text = "AAAAA";
        String pattern = "AA";
        
        // Indices should be 0, 1, 2, 3
        // A A A A A
        // 0 1 2 3 4
        // AA (0)
        //  AA (1)
        //   AA (2)
        //    AA (3)
        
        List<Integer> indices = kmpAlgorithm.findIndices(text, pattern);
        assertEquals(4, indices.size());
        assertIterableEquals(List.of(0, 1, 2, 3), indices);
        assertEquals(4, kmpAlgorithm.countOccurrences(text, pattern));
    }

    @Test
    void testSpecialCharacters() {
        String text = "Hello, World! @2025 #Java";
        String pattern = "@2025";
        
        List<Integer> indices = kmpAlgorithm.findIndices(text, pattern);
        assertEquals(1, indices.size());
        assertEquals(14, indices.get(0));
    }

    @Test
    void testSingleCharacter() {
        String text = "A";
        String pattern = "A";
        
        List<Integer> indices = kmpAlgorithm.findIndices(text, pattern);
        assertEquals(1, indices.size());
        assertEquals(0, indices.get(0));
        
        pattern = "B";
        indices = kmpAlgorithm.findIndices(text, pattern);
        assertTrue(indices.isEmpty());
    }


}
