用文本编辑器打开mksignal.bat，将下面命令中的test.apk替换为要签名的apk名字，testnew.apk修改为签名以后的apk名字。然后双击mksignal.bat开始签名。

java -jar signapk.jar platform.x509.pem platform.pk8 test.apk testnew.apk