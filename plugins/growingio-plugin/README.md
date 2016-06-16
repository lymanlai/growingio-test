
### 1. 安装插件
```
cordova plugin add growingio-plugin --variable GROWINGIO_SCHEME=您的Scheme
```
`GROWINGIO_SCHEME`需要您在项目管理中查看对应产品的URL Scheme。
### 2. 修改配置文件
修改`config.xml`文件，添加以下代码：
```
<preference name="GROWINGIO_PROJECT_ID" value="您的项目ID" />
```

### 3. 配置自定义维度

```
window.cordova.exec(null, null, "growingio-plugin", "setCS", [
    ['setCS1', 'CS1_key', 'CS1_value'],
    ['setCS2', 'CS2_key', 'CS2_value'],
    ...
]);
```
自定义维度最多支持10个，所以可以使用`setCS1`到`setCS10`。

更多帮助信息请访问 https://help.growingio.com/SDK/Android.html