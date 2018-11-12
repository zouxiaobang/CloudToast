# CloudToast

## 特点
1、可选择项目中默认拥有的两种背景

2、可自定义背景

3、可在子线程中直接使用

4、可在没有通知栏权限的情况下使用

## 使用
### 在项目gradle文件中：
```
allprojects {
 	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
  
### 在模块gradle文件中：
```
dependencies {
	implementation 'com.github.zouxiaobang:CloudToast:1.0'
}
```
