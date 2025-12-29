package com.awords.textkmpanalyzer.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * KMP算法实现类
 * 负责核心的字符串匹配逻辑
 */
public class KMPAlgorithm {

    /**
     * 构建部分匹配表 (Next数组)
     * @param pattern 模式串
     * @return next数组
     */
    private int[] buildNext(String pattern) {
        int m = pattern.length();
        int[] next = new int[m + 1];
        next[0] = -1;
        int k = -1;
        int j = 0;
        while (j < m) {
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                j++;
                k++;
                // 优化：如果 pattern[j] == pattern[k]，则 next[j] = next[k]
                // 注意：当 j == m 时，不能访问 pattern.charAt(j)
                if (j < m && pattern.charAt(j) == pattern.charAt(k)) {
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * 统计关键词在文本中出现的总次数
     * @param text 主文本
     * @param pattern 关键词
     * @return 出现次数
     */
    public int countOccurrences(String text, String pattern) {
        if (text == null || pattern == null || pattern.isEmpty() || text.length() < pattern.length()) {
            return 0;
        }
        return findIndices(text, pattern).size();
    }

    /**
     * 获取关键词在文本中所有的起始位置索引
     * @param text 主文本
     * @param pattern 关键词
     * @return 索引列表
     */
    public List<Integer> findIndices(String text, String pattern) {
        List<Integer> indices = new ArrayList<>();
        if (text == null || pattern == null || pattern.isEmpty() || text.length() < pattern.length()) {
            return indices;
        }
        
        int[] next = buildNext(pattern);
        int i = 0; // text index
        int j = 0; // pattern index
        
        while (i < text.length()) {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            
            if (j == pattern.length()) {
                indices.add(i - j);
                j = next[j];
            }
        }
        return indices;
    }
}
