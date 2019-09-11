# maven-repository
github maven 仓库

# auto-generate-code
## 基于mybatis-plus-generator 简易代码生成器

* 目前支持数据库类型 mysql sqlServer
* 需要在配置数据库 在resources/config/dataSource.xml进行配置


# 使用方法
1.  在pom文件中添加依赖包,还要相应的数据库依赖包
    <dependency>
      <groupId>com.github.sdcxy</groupId>
      <artifactId>auto-generate-code</artifactId>
      <version>1.0-RELEASE</version>
    </dependency>

2.  配置数据库
在resources/config/下创建一个数据库配置文件dataSource.xml
配置查看源代码的xml文件

3.  实例化MySqlGenerator 或者 SqlServerGenerator
默认父级包为: GroupId com.github.sdcxy



