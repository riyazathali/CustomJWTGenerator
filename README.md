# Custom JWT Generator

A custom JWT generator for WSO2 products.

This is created to remove tenant info from the "sub" claim like below...

```text
admin@carbon.super -> admin
``` 

### Build

This repository contains the source code of the Custom JWT Token Generator that is used as sample in the following article:

https://docs.wso2.com/display/AM260/Passing+Enduser+Attributes+to+the+Backend+Using+JWT

It is just needed to build the project using maven:

```shell
mvn clean package
```
