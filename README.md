# yaml文件检查工具

## how to use

> 支持文件和目录检测，文件后缀必须为`yaml`或`yml`。如果是目录，只检测目录中的`yaml`或`yml`为后缀的文件。

- waring信息和error信息
```shell
java -cp yamlCheckTool-1.0-SNAPSHOT-jar-with-dependencies.jar com.fs.Cli /root/test/test.yml
``` 
或者检测目录
```shell
java -cp yamlCheckTool-1.0-SNAPSHOT-jar-with-dependencies.jar com.fs.Cli /root/test/
``` 
- 只保留error信息
```shell
java -cp yamlCheckTool-1.0-SNAPSHOT-jar-with-dependencies.jar com.fs.Cli /root/test/test.yml --no-warnings
```
- help
```shell
java -cp yamlCheckTool-1.0-SNAPSHOT-jar-with-dependencies.jar com.fs.Cli -h
```