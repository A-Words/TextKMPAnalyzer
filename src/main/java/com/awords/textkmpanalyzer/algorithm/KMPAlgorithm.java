package com.awords.textkmpanalyzer.algorithm;

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
        // TODO: 实现Next数组构建逻辑
        return new int[0];
    }

    /**
     * 统计关键词在文本中出现的总次数
     * @param text 主文本
     * @param pattern 关键词
     * @return 出现次数
     */
    public int countOccurrences(String text, String pattern) {
        // TODO: 使用KMP算法统计出现次数
        return 0;
    }

    /**
     * 获取关键词在文本中所有的起始位置索引
     * @param text 主文本
     * @param pattern 关键词
     * @return 索引列表
     */
    public List<Integer> findIndices(String text, String pattern) {
        // TODO: 使用KMP算法查找所有出现位置
        return null;
    }
}
