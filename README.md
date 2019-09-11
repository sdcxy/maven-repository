# auto-generate-code
## 基于mybatis-plus-generator 简易代码生成器

* 目前支持数据库类型(已测试) **mysql sqlServer**
* 需要在配置数据库 在resources/config/dataSource.xml进行配置


# 使用方法
1.  在pom文件中添加依赖包,还有相应的数据库依赖包
    ```
    <dependency>
          <groupId>com.github.sdcxy</groupId>
          <artifactId>auto-generate-code</artifactId>
          <version>1.0-RELEASE</version>
    </dependency>
    ```

2.  配置数据库
在resources/config/下创建一个数据库配置文件**dataSource.xml**
配置查看源代码的xml文件

3.  实例化MySqlGenerator 或者 SqlServerGenerator
```
MySqlGenerator mySql = new MySqlGenerator();
SqlServerGenerator sqlServer = new SqlServerGenerator();
```
* 默认父级包为:  ***com.github.sdcxy***
* 调用AutoGenerateCode方法进行代码生成
```
mySql.autoGenerateCode(...params);
sqlServer.autoGenerateCode(...params);
```
* 参数说明：
```
* parentPackageName：父级包
* moduleName： 模块名称
* tableName: 数据库表名(支持字符串或字符串数组)
* tablePrefix: 数据库表前缀
```



