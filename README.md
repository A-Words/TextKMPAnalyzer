# 文本关键词匹配分析与索引统计系统 (Text KMP Analyzer)

本项目是一个基于 Java 的文本分析工具，集成了 **控制台 (Console)** 和 **图形界面 (JavaFX GUI)** 两种交互模式。系统旨在演示和应用 KMP (Knuth-Morris-Pratt) 字符串匹配算法，允许用户输入或加载文本，指定关键词，并快速统计关键词出现的次数及其在文本中的所有位置索引。

## 🛠 技术栈

- **编程语言**: Java 25
- **构建工具**: Maven
- **UI 框架**: JavaFX 21.0.6
- **单元测试**: JUnit 5

## ✨ 功能特性

1.  **文本文件创建与保存**
    *   支持用户输入单行文本内容（不包含空格）。
    *   支持将输入内容保存到指定文件名的文本文件中。
2.  **关键词出现次数统计**
    *   读取指定文本文件。
    *   使用 KMP 算法统计关键词在文本中出现的总次数。
3.  **关键词位置索引查询**
    *   读取指定文本文件。
    *   使用 KMP 算法查找关键词在文本中每一次出现的起始索引位置（0 为起始下标）。
4.  **双模式交互**
    *   **控制台模式 (默认)**: 提供菜单式命令行交互界面。
    *   **GUI 模式**: 提供直观的图形化操作界面。

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

本项目支持两种运行模式，请根据需求选择。

#### 方式 A: 运行控制台模式 (默认)

直接运行程序即可进入命令行菜单交互模式：

```bash
mvn exec:java -Dexec.mainClass="com.awords.textkmpanalyzer.Launcher"
```

#### 方式 B: 运行图形界面模式 (GUI)

添加 `--gui` 参数启动 JavaFX 图形界面：

**Windows (PowerShell):**
```powershell
mvn exec:java "-Dexec.mainClass=com.awords.textkmpanalyzer.Launcher" "-Dexec.args=--gui"
```

**Linux / macOS (Bash):**
```bash
mvn exec:java -Dexec.mainClass="com.awords.textkmpanalyzer.Launcher" -Dexec.args="--gui"
```

## 📂 项目结构

```
src/
├── main/
│   ├── java/com/awords/textkmpanalyzer/
│   │   ├── algorithm/            # KMP 算法核心实现
│   │   ├── io/                   # 文件读写服务
│   │   ├── Launcher.java         # 程序启动入口 (处理 CLI/GUI 切换)
│   │   ├── TextAnalyzerConsole.java # 控制台交互逻辑
│   │   ├── TextAnalyzerApplication.java  # JavaFX 应用入口
│   │   └── TextAnalyzerController.java   # GUI 界面控制器
│   └── resources/com/awords/textkmpanalyzer/
│       └── text-analyzer-view.fxml       # GUI 界面布局文件
└── test/                         # 单元测试
```

## 📝 开发任务完成情况

### 1. 算法开发 (Algorithm) ✅
- [x] 实现 `buildNext(String pattern)`
- [x] 实现 `countOccurrences(String text, String pattern)`
- [x] 实现 `findIndices(String text, String pattern)`

### 2. 文件 IO 开发 (File I/O) ✅
- [x] 实现 `saveTextToFile(File file, String content)`
- [x] 实现 `readTextFromFile(File file)`

### 3. 界面开发 (UI/UX) ✅
- [x] **GUI**: 完成 JavaFX 界面设计与控制器逻辑。
- [x] **Console**: 完成命令行菜单设计与交互逻辑。
- [x] **Launcher**: 实现启动参数解析与模式切换。

## 🤝 贡献指南

1. Fork 本仓库
2. 创建您的特性分支 (git checkout -b feature/AmazingFeature)
3. 提交您的更改 (git commit -m 'Add some AmazingFeature')
4. 推送到分支 (git push origin feature/AmazingFeature)
5. 开启一个 Pull Request
