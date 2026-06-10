# 实验2：构建Kotlin应用并使用Compose布局

**实验名称**：构建Kotlin应用并使用Compose布局  
**实验环境**：M2芯片 MacBook Pro、Android Studio 2024.1+、JDK 11、Jetpack Compose 稳定版  
**实验日期**：2026年5月6日

## 一、实验目的

1. 掌握使用Kotlin语言开发Android的基本流程
2. 掌握Android Compose布局的基本用法
3. 进一步熟悉Kotlin语言的核心特性
4. 完成面向AI应用的Compose界面开发，掌握声明式UI的开发规范

## 二、实验内容与要求

### 核心任务清单

|任务编号|任务内容|核心要求|
|---|---|---|
|任务1|创建首个Kotlin应用|基于Empty Compose Activity模板创建项目，使用Kotlin语言，最小支持API Level 21，完成项目环境配置与基础搭建|
|任务2|Compose布局基础实践|掌握Jetpack Compose核心布局组件（Column/Row/Box/Card）的用法，实现可交互的声明式UI布局|
|任务3|面向AI应用的Compose布局|实现LiteRT AI Demo界面，包含顶部栏、相机预览区、AI结果展示区、功能按钮区，严格遵循Compose布局规范|
|最终提交|代码上传与文档撰写|完整代码上传至GitHub，撰写详细的README文档与实验报告|

## 三、实验环境准备与前置配置

### 3.1 基础环境安装

本次实验基于M2芯片MacBook Pro完成，前置环境配置步骤如下：

1. 安装Android Studio：通过Homebrew一键安装适配Apple Silicon的版本，终端执行命令：

```bash
brew install --cask android-studio
首次启动 Android Studio，完成 Android SDK 下载，默认安装路径为 ~/Library/Android/sdk
配置 SDK 环境变量，终端执行以下命令，确保 adb 等命令可正常使用：
bash


运行



echo 'export ANDROID_HOME=$HOME/Library/Android/sdk' >> ~/.zshrc
echo 'export PATH=$PATH:$ANDROID_HOME/platform-tools' >> ~/.zshrc
echo 'export PATH=$PATH:$ANDROID_HOME/tools' >> ~/.zshrc
echo 'export PATH=$PATH:$ANDROID_HOME/emulator' >> ~/.zshrc
source ~/.zshrc
验证环境：终端执行 adb --version，输出版本信息即配置成功。
3.2 国内镜像配置（解决网络超时问题）
由于国内网络环境限制，需配置阿里云镜像解决 Gradle 下载超时、依赖无法拉取的问题，核心配置如下：
项目根目录 settings.gradle.kts 配置，替换原有仓库配置：
kotlin




pluginManagement {
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/jcenter") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven { url = uri("https://maven.aliyun.com/repository/google") }
        maven { url = uri("https://maven.aliyun.com/repository/jcenter") }
        maven { url = uri("https://maven.aliyun.com/repository/public") }
        google()
        mavenCentral()
    }
}

rootProject.name = "MyFirstKotlinApp"
include(":app")
Gradle 发行版镜像配置：修改 gradle/wrapper/gradle-wrapper.properties，将 distributionUrl 替换为国内腾讯云镜像：
properties




distributionUrl=https\://mirrors.cloud.tencent.com/gradle/gradle-8.7-bin.zip
3.3 项目依赖配置
模块级 app/build.gradle.kts 核心配置，确保 Compose 版本兼容、功能完整：
kotlin




plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.myfirstkotlinapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.myfirstkotlinapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3:1.2.1")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}
四、详细实验步骤与实现
任务 1：创建首个 Kotlin 应用
1.1 项目创建
打开 Android Studio，点击 New Project，选择 Empty Compose Activity 模板，点击 Next
项目配置：
Name：MyFirstKotlinApp
Language：Kotlin
Minimum SDK：API 24（Android 7.0，兼容 Compose 最低要求）
Build configuration language：Kotlin DSL
点击 Finish 完成项目创建，等待 Gradle 同步完成。
1.2 基础项目验证
打开 app/src/main/java/com/example/myfirstkotlinapp/MainActivity.kt，查看自动生成的基础 Compose 代码
创建 Android 模拟器：选择 ARM64 架构的系统镜像（适配 M2 芯片），完成模拟器创建
点击运行按钮，项目成功编译并在模拟器中运行，显示默认的 Hello Android! 界面，任务 1 完成。
1.3 截图节点
项目创建完成后的 Android Studio 项目结构界面
模拟器运行默认 Hello World 界面的截图
Gradle 同步成功的日志界面
任务 2：Compose 布局基础实践
2.1 核心目标
掌握 Jetpack Compose 核心布局组件的用法，实现可交互的可展开列表布局，包含 Column 垂直布局、Card 容器、Text 文本展示、点击展开 / 收起的状态管理。
2.2 代码实现
替换 MainActivity.kt 全量代码为以下内容，实现任务 2 核心功能：
kotlin




package com.example.myfirstkotlinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myfirstkotlinapp.ui.theme.MyFirstKotlinAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstKotlinAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeLayoutDemo()
                }
            }
        }
    }
}

@Composable
fun ComposeLayoutDemo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Compose布局实践",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        ExpandableCard(
            title = "Hello World",
            content = "这是Jetpack Compose的基础布局实践，我们使用Column作为外层垂直布局，Card包裹内容，Text展示文本，通过clickable实现点击展开/收起的交互，完全覆盖Compose基础布局的核心用法。"
        )

        ExpandableCard(
            title = "Hello Compose",
            content = "Jetpack Compose是Android官方推荐的声明式UI框架，相比传统的XML布局，代码更简洁、逻辑更直观，支持实时预览，能大幅提升Android界面开发效率。这里我们使用了remember和mutableStateOf实现状态管理，这是Compose的核心特性之一。"
        )
    }
}

@Composable
fun ExpandableCard(title: String, content: String) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = if (isExpanded) "Show less" else "Show more",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true, name = "任务2布局预览")
@Composable
fun LayoutDemoPreview() {
    MyFirstKotlinAppTheme {
        ComposeLayoutDemo()
    }
}
2.3 功能验证
代码同步无报错，右侧预览面板可正常显示布局效果
运行到模拟器，界面显示双条目卡片，点击 Show more 可展开完整文本，点击 Show less 可收起文本，交互功能正常
界面支持垂直滚动，适配不同屏幕尺寸，任务 2 完成。
2.4 截图节点
Android Studio 代码编辑页 + 右侧预览面板截图
模拟器运行初始界面截图
点击展开后的完整界面截图
任务 3：面向 AI 应用的 Compose 布局
3.1 核心要求
严格按照实验文档规范，实现 LiteRT AI Demo 界面，包含四大核心模块：
顶部栏：显示应用标题与操作入口
预览区：Box 占位，预留 CameraX 相机预览接入能力
结果区：Card + Column 展示模型名称、识别结果、置信度、推理时间
按钮区：Row/Column 排列按钮，实现拍照识别、切换模型、相册导入、清空结果功能
3.2 代码实现
替换 MainActivity.kt 全量代码为以下最终修复版内容，全版本兼容无报错：
kotlin




package com.example.myfirstkotlinapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myfirstkotlinapp.ui.theme.MyFirstKotlinAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyFirstKotlinAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AiDemoApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiDemoApp() {
    var modelName by remember { mutableStateOf("MobileNet") }
    var recognizeResult by remember { mutableStateOf("待识别") }
    var confidence by remember { mutableStateOf("--") }
    var inferenceTime by remember { mutableStateOf("--") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "LiteRT AI Demo",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // 相机预览区
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxSize(),
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Camera Preview",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // AI识别结果区
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "识别结果",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(text = "Model: $modelName", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Result: $recognizeResult", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Confidence: $confidence", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Time: $inferenceTime", style = MaterialTheme.typography.bodyLarge)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // 功能按钮区
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            recognizeResult = "Cat"
                            confidence = "96.2%"
                            inferenceTime = "28 ms"
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "拍照识别")
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = {
                            modelName = if (modelName == "MobileNet") "ResNet50" else "MobileNet"
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "切换模型")
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = { /* 相册导入逻辑预留 */ },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "相册导入")
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = {
                            recognizeResult = "待识别"
                            confidence = "--"
                            inferenceTime = "--"
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "清空结果")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "任务3 AI应用预览", showSystemUi = true)
@Composable
fun AiDemoPreview() {
    MyFirstKotlinAppTheme {
        AiDemoApp()
    }
}
3.3 功能验证
代码同步无报错，右侧预览面板可完整显示 AI Demo 界面
运行到模拟器，界面完全符合实验要求的布局规范，四大模块完整呈现
功能交互验证：
点击拍照识别：结果区自动填充模拟的识别结果、置信度、推理时间
点击切换模型：模型名称在 MobileNet 和 ResNet50 之间切换
点击清空结果：识别结果恢复为初始状态
点击相册导入：预留接口，可后续扩展功能
界面适配正常，无布局错乱，任务 3 完成。
3.4 截图节点
Android Studio 代码编辑页 + 完整预览面板截图
模拟器运行 AI Demo 主界面截图
点击拍照识别后的结果界面截图
切换模型、清空结果的功能验证截图
