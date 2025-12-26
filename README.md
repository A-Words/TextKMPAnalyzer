# 文本关键词匹配分析与索引统计系统 (Text KMP Analyzer)

本项目是一个基于 JavaFX 的文本分析工具，旨在演示和应用 KMP (Knuth-Morris-Pratt) 字符串匹配算法。系统允许用户输入或加载文本，指定关键词，并快速统计关键词出现的次数及其在文本中的所有位置索引。

## 🛠 技术栈

- **编程语言**: Java 25
- **构建工具**: Maven
- **UI 框架**: JavaFX 21.0.6
- **单元测试**: JUnit 5

## 📋 环境要求

在运行本项目之前，请确保您的开发环境满足以下要求：

- **JDK**: OpenJDK 25 或更高版本
- **Maven**: 3.8.0 或更高版本
- **IDE**: IntelliJ IDEA (推荐) 或 Eclipse

## 🚀 快速开始

### 1. 克隆项目

```bash
git clone <repository-url>
cd TextKMPAnalyzer
```

### 2. 构建项目

使用 Maven 下载依赖并构建项目：

```bash
mvn clean install
```

### 3. 运行应用

使用 JavaFX Maven 插件启动应用程序：

```bash
mvn javafx:run
```

## 📂 项目结构

```
src/
├── main/
│   ├── java/com/awords/textkmpanalyzer/
│   │   ├── algorithm/          # KMP 算法核心实现
│   │   ├── io/                 # 文件读写服务
│   │   ├── TextAnalyzerApplication.java  # JavaFX 应用入口
│   │   └── TextAnalyzerController.java   # 界面控制器
│   └── resources/com/awords/textkmpanalyzer/
│       └── text-analyzer-view.fxml       # 界面布局文件
└── test/                       # 单元测试
```

## 📝 开发任务分工

本项目分为三个主要模块进行开发，请根据分工完成相应任务。

### 1. 算法开发 (Algorithm)
**目标**: 实现 KMP 字符串匹配算法的核心逻辑。
- **文件**: `src/main/java/com/awords/textkmpanalyzer/algorithm/KMPAlgorithm.java`
- **任务**:
    - 实现 `buildNext(String pattern)`: 构建 Next 数组。
    - 实现 `countOccurrences(String text, String pattern)`: 统计关键词出现次数。
    - 实现 `findIndices(String text, String pattern)`: 返回所有出现位置的索引列表。
    - **要求**: 必须严格实现 KMP 算法，禁止直接调用 `String` 的 `indexOf` 或 `contains` 方法。

### 2. 文件 IO 开发 (File I/O)
**目标**: 实现文本文件的读取与保存功能。
- **文件**: `src/main/java/com/awords/textkmpanalyzer/io/FileService.java`
- **任务**:
    - 实现 `saveTextToFile(File file, String content)`: 将界面输入的文本保存到本地文件。
    - 实现 `readTextFromFile(File file)`: 从本地文件读取文本内容到界面。

### 3. 界面开发 (UI/UX)
**目标**: 实现 JavaFX 界面交互与逻辑绑定。
- **文件**: 
    - `src/main/java/com/awords/textkmpanalyzer/TextAnalyzerController.java`
    - `src/main/resources/com/awords/textkmpanalyzer/text-analyzer-view.fxml`
- **任务**:
    - **界面设计**: 完善 `FXML`，包含文本输入区、关键词输入框、"保存"按钮、"搜索"按钮及结果显示区。
    - **逻辑控制**: 在 `Controller` 中处理按钮点击事件，协调 `FileService` 进行文件操作，调用 `KMPAlgorithm` 进行计算，并更新 UI 结果。

## 🤝 贡献指南

1. Fork 本仓库
2. 创建您的特性分支 (git checkout -b feature/AmazingFeature)
3. 提交您的更改 (git commit -m 'Add some AmazingFeature')
4. 推送到分支 (git push origin feature/AmazingFeature)
5. 开启一个 Pull Request
