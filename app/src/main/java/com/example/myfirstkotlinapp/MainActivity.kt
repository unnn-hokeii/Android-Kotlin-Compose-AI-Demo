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
                    // 任务3 AI应用布局入口
                    AiDemoApp()
                }
            }
        }
    }
}

// 任务3：AI识别Demo完整界面
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AiDemoApp() {
    // AI识别结果状态（模拟数据，后续对接模型可直接替换）
    var modelName by remember { mutableStateOf("MobileNet") }
    var recognizeResult by remember { mutableStateOf("待识别") }
    var confidence by remember { mutableStateOf("--") }
    var inferenceTime by remember { mutableStateOf("--") }

    Scaffold(
        // 1. 顶部栏：实验要求的应用标题与操作入口，全版本兼容写法
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
        // 外层Column组织整个页面，完全符合实验实现建议
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // 2. 预览区：Box占位，后续可直接替换为CameraX相机预览
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

            // 3. 结果区：Card + Column展示AI识别信息，官方标准全版本兼容写法
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

            // 4. 按钮区：Row + Column排列功能按钮，符合实验要求
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // 第一行按钮
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        onClick = {
                            // 模拟AI识别结果，后续对接模型替换逻辑
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
                            // 切换模型逻辑
                            modelName = if (modelName == "MobileNet") "ResNet50" else "MobileNet"
                        },
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(text = "切换模型")
                    }
                }

                // 第二行按钮
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
                            // 清空结果逻辑
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

// 预览功能，无需运行模拟器即可查看AI应用完整界面
@Preview(showBackground = true, name = "任务3 AI应用预览", showSystemUi = true)
@Composable
fun AiDemoPreview() {
    MyFirstKotlinAppTheme {
        AiDemoApp()
    }
}