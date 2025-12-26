# 文本关键词匹配分析与索引统计系统

本项目是一个基于 JavaFX 的文本分析工具，用于实现 KMP 算法对文本关键词的匹配与统计。

## 项目分工

### 1. 算法开发
**目标**: 实现 KMP 字符串匹配算法的核心逻辑。

**文件**:
- `src/main/java/com/awords/textkmpanalyzer/algorithm/KMPAlgorithm.java`

**任务**:
- 实现 `buildNext(String pattern)`: 构建 Next 数组。
- 实现 `countOccurrences(String text, String pattern)`: 统计关键词出现次数。
- 实现 `findIndices(String text, String pattern)`: 返回所有出现位置的索引列表。
- **要求**: 必须使用 KMP 算法，不得直接调用 `indexOf`。

### 2. 文件 IO 开发
**目标**: 实现文件读写功能。

**文件**: 
- `src/main/java/com/awords/textkmpanalyzer/io/FileService.java`

**任务**:
- **文件操作**: 在 `FileService.java` 中实现 `saveTextToFile` 和 `readTextFromFile`。

### 3. 界面开发
**目标**: 实现 JavaFX 界面交互。

**文件**: 
- `src/main/java/com/awords/textkmpanalyzer/TextAnalyzerController.java`
- `src/main/resources/com/awords/textkmpanalyzer/text-analyzer-view.fxml`

**任务**:
- **界面设计**: 修改 `text-analyzer-view.fxml`，设计包含以下功能的界面：
    - 文本输入框 (用于输入待保存的文本)。
    - 保存按钮 (保存文本到文件)。
    - 关键词输入框。
    - 统计与搜索按钮。
    - 结果显示区域 (显示次数和索引)。
- **逻辑控制**: 在 `TextAnalyzerController.java` 中处理按钮点击事件，调用 `FileService` 和 `KMPAlgorithm`。

## 快速开始

1. **算法人员**: 专注于 `algorithm` 包下的代码实现。
2. **文件 IO 人员**: 专注于 `io` 包下的代码实现。
3. **界面人员**: 专注于 `TextAnalyzerController` 和 `fxml` 的实现。
4. **集成**: 界面人员在 Controller 中实例化 `KMPAlgorithm` 和 `FileService` 并调用其方法显示结果。
