# 实验2：构建Kotlin应用并使用Compose布局

实验名称：构建Kotlin应用并使用Compose布局

实验环境：M2芯片 MacBook Pro、Android Studio 2024.1+、JDK 11、Jetpack Compose 稳定版

实验日期：2026年5月6日

---

**一、实验目的**

1. 掌握使用Kotlin语言开发Android的基本流程
2. 掌握Android Compose布局的基本用法
3. 进一步熟悉Kotlin语言的核心特性
4. 完成面向AI应用的Compose界面开发，掌握声明式UI的开发规范

---

**二、实验内容与要求**

核心任务清单

| 任务编号 | 任务内容             | 核心要求                                                                     |
| ---- | ---------------- | ------------------------------------------------------------------------ |
| 任务1  | 创建首个Kotlin应用     | 基于Empty Compose Activity模板创建项目，使用Kotlin语言，最小支持API Level 21，完成项目环境配置与基础搭建 |
| 任务2  | Compose布局基础实践    | 掌握Jetpack Compose核心布局组件（Column/Row/Box/Card）的用法，实现可交互的声明式UI布局            |
| 任务3  | 面向AI应用的Compose布局 | 实现LiteRT AI Demo界面，包含顶部栏、相机预览区、AI结果展示区、功能按钮区，严格遵循Compose布局规范             |
| 最终提交 | 代码上传与文档撰写        | 完整代码上传至GitHub，撰写详细的README文档与实验报告                                         |

---

**三、实验环境准备与前置配置**

***3.1 基础环境安装***

本次实验基于M2芯片MacBook Pro完成，前置环境配置步骤如下：

1. 安装Android Studio：通过Homebrew一键安装适配Apple Silicon的版本，终端执行命令：

```bash
brew install --cask android-studio
```

***3.2 国内镜像配置（解决网络超时问题）***

```kotlin
pluginManagement {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        ...
    }
}
```

***3.3 项目依赖配置***

```kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}
```

---

**四、详细实验步骤与实现**

***任务1：创建首个Kotlin应用***

***1.1 项目创建***

...

***1.2 基础项目验证***

...

***1.3 截图节点***

* 项目创建完成后的Android Studio项目结构界面
* 模拟器运行默认Hello World界面的截图
* Gradle同步成功的日志界面

---

***任务2：Compose布局基础实践***

***2.1 核心目标***

...

***2.2 代码实现***

```kotlin
package com.example.myfirstkotlinapp
...
```

***2.3 功能验证***

...

***2.4 截图节点***

...

---

***任务3：面向AI应用的Compose布局***

***3.1 核心要求***

...

***3.2 代码实现***

```kotlin
package com.example.myfirstkotlinapp
...
```

***3.3 功能验证***

...

***3.4 截图节点***

* Android Studio代码编辑页+完整预览面板截图
* 模拟器运行AI Demo主界面截图
* 点击拍照识别后的结果界面截图
* 切换模型、清空结果的功能验证截图

---

**五、代码上传GitHub与README文档**

***5.1 代码上传步骤***

```bash
git init
git add .
git commit -m "完成实验2：Kotlin应用构建+Compose布局实践，包含任务1/2/3全量代码"
git remote add origin https://github.com/xxx/Android-Kotlin-Compose-AI-Demo.git
git branch -M main
git push -u origin main
```

---

**六、实验遇到的问题与解决方案**

| 序号 | 问题描述                                                                     | 根本原因                                        | 解决方案                                                                                           |
| -- | ------------------------------------------------------------------------ | ------------------------------------------- | ---------------------------------------------------------------------------------------------- |
| 1  | Gradle下载超时，提示java.net.SocketTimeoutException                             | 国内网络无法访问Gradle官方仓库                          | 将Gradle发行版地址替换为腾讯云国内镜像，配置阿里云Maven仓库加速依赖下载                                                      |
| 2  | 同步失败，提示Failed to resolve androidx相关依赖                                    | 依赖包无法从国外仓库拉取                                | 在settings.gradle.kts中配置阿里云镜像，优先从国内仓库拉取依赖                                                       |
| 3  | 代码爆红，提示Unresolved reference 'Arrangement'                                | 导入路径错误，Arrangement属于layout子包                | 将导入语句从androidx.compose.foundation.Arrangement修正为androidx.compose.foundation.layout.Arrangement |
| 4  | Card组件报错，提示No parameter with name 'backgroundColor/containerColor' found | Material3不同版本的参数命名不兼容                       | 使用官方标准写法colors = CardDefaults.cardColors(containerColor = xxx)，全版本兼容                           |
| 5  | TopAppBar颜色配置警告                                                          | 低版本Material3不支持centerAlignedTopAppBarColors | 替换为全版本兼容的topAppBarColors方法                                                                     |
