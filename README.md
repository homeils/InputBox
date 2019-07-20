# InputBox - 自定义输入框
>> 使用安卓原生控件组合制作的输入框。支持设置图标及图标大小，设置标题、标题字体大小、标题字体颜色，设置输入框提示文本，设置输入框字体大小及字体颜色，
设置清空功能及密码是否可见功能。目前标题和输入框的字体格式默认（个人觉得这样看着比较舒服，就懒得抛出设置，有兴趣可参考源码自行修改）。布局用的是安卓
Constraintlayout约束布局，特别好用的布局，极力推荐，没有引入约束布局依赖的记得加依赖。欢迎大家使用以及提出优化意见或Bug！
## 向项目中引入依赖
>> 在Project下的gradle中添加jitpack仓库依赖：
```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
>> 在app下的gradle中添加如下依赖：
```
dependencies {
    ...
    implementation 'com.github.renoside:InputBox:v1.1.4'
    ...
}
```
## 如何使用
>> 布局文件示例：
```
<com.renoside.inputbox.InputBox
    android:id="@+id/username"
    android:layout_width="250dp"
    android:layout_height="60dp"
    app:ico_background="@drawable/username"
    app:ico_size="30"
    app:input_size="15"
    app:input_color="#C0C0C0"
    app:input_hint="请输入用户名"
    app:is_clear="true"
    app:is_password="false"
    app:layout_constraintBottom_toTopOf="@id/password"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent"
    app:layout_constraintVertical_chainStyle="packed"
    app:title_content="用户名："
    app:title_size="15"
    app:title_color="#C0C0C0"/>
```
>> java代码中设置：
```
/**
*获取输入框内容
*/
inputBox.getText();
```
## 说明
>> 项目有许多不足之处，还望多多包涵           
>> 作者：renoside     
>> Github主页：https://github.com/renoside          
