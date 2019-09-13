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
* @version-1.0.0 版本 在resources/config/下创建一个数据库配置文件 dataSource.xml


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

# 版本说明
* 2019-09-11
1. 更新版本1.0.0 配置方式按照上面说明

* 2019-09-13
1. 版本1.0.0 中的xml配置方法已经被弃用了，建议使用yml配置方式
2. 版本1.0.1 启用yml的数据库配置方式,无需配置数据库类型，会自动根据driver-class-name 判断数据库类型
    ```$xslt
        dataSource:
        driver-class-name: com.mysql.jdbc.Driver
        url: jdbc:mysql://127.0.0.1:3306/demo?characterEncoding=utf8&useSSL=false&useUnicode=true&autoReconnect=true&serverTimezone=Asia/Shanghai
        username: root
        password: root
    ```
3. 弃用MySqlGenerator和SqlServerGenerator类，使用GeneratorFactory类代替，使用方法还是一样

4. 配置文件更新为: yml配置 需要在resources/application.yml 或者 resources/dataSource.yml进行配置

* 2019-09-13
* 修复enums目录结果，修复语法上的错误