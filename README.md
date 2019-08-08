# Custom JWT Generator

A custom JWT generator for WSO2 products.

This is created to remove tenant info from the "sub" claim like below...

```text
admin@carbon.super -> admin
``` 

### Build

```shell
mvn clean package
```