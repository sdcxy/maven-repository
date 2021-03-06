package com.github.sdcxy.modules.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.SqlServerTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.github.sdcxy.modules.common.entity.DataSource;
import com.github.sdcxy.modules.common.enums.Constants;
import com.github.sdcxy.modules.common.util.DataBaseXmlUtils;
import com.github.sdcxy.modules.common.util.YamlUtils;

import java.util.*;

/**
 <p>
    代码生成器父类
 </p>
 * @className SuperGenerator
 * @description TODO
 * @author lxx
 * @version 1.0.0
 * @date 2019/9/9 14:46
 **/
public class SuperGenerator {

    // 自定义模板 默认找templates文件目录下的这些命名文件 只需要配置 xml 自定义位置
//    private static final String CONTROLLER_TEMPLATE = "/templates/controller.java.vm";
//    private static final String ENTITY_TEMPLATE = "/templates/entity.java.vm";
//    private static final String MAPPER_TEMPLATE = "/templates/mapper.java.vm";
    private static final String MAPPER_XML_TEMPLATE = "/templates/mapper.xml.vm";
//    private static final String SERVICE_TEMPLATE = "/templates/service.java.vm";
//    private static final String SERVICEIMPL_TEMPLATE = "/templates/serviceImpl.java.vm";
    //作者
    private static final String AUTHOR = "lxx";

    /**
     * 获取根目录
     * @version 1.0.0
     * @return
     */
    private String getRootPath() {
//        String file = Objects.requireNonNull(SuperGenerator.class.getClassLoader().getResource("")).getFile();
//        String rootPath = new File(file).getParentFile().getParentFile().getParent();
        // 通过System.getProperty("user.dir")来获取项目根目录
        return System.getProperty("user.dir");
    }

    /**
     * 获取JAVA目录
     * @version 1.0.0
     * @return
     */
    protected String getJavaPath() {
        return getRootPath() + "/src/main/java";
    }

    /**
     * 获取Resource目录
     * @version 1.0.0
     * @return
     */
    protected String getResourcePath() {
        return getRootPath() + "/src/main/resources";
    }


    /**
     *  获取 dataSourceConfig
     * @param dbType 数据库类型
     * @version 1.0.0
     * @return
     */
    @Deprecated
    protected DataSourceConfig getDataSourceConfig(DbType dbType) {
        // 读取配置文件的dataSource.xml 返回dataSource
        DataSource dataSource  = YamlUtils.readDataSourceYaml();

        if (dataSource == null) {
            dataSource = DataBaseXmlUtils.readDataBaseXml(dbType.getDb());
        }
        // 实例化generator dataSourceConfig
        DataSourceConfig dataSourceConfig =  new DataSourceConfig();
            dataSourceConfig
                 // 设置数据库类型
                .setDbType(dbType)
                 // 设置数据库driver
                .setDriverName(dataSource.getDriverClassName())
                 // 设置数据库url
                .setUrl(dataSource.getUrl())
                 // 设置数据库用户名
                .setUsername(dataSource.getUsername())
                 // 设置数据库密码
                .setPassword(dataSource.getPassword());
            // 判断数据库类型设置 数据库类型的转换
            if (dbType.equals(DbType.MYSQL)) {
                dataSourceConfig.setTypeConvert(new MySqlTypeConvert(){
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        //  数据库类型转换
                        if (fieldType.toLowerCase().equals("bit")) {
                            return DbColumnType.BOOLEAN;
                        }
                        if (fieldType.toLowerCase().equals("tinyint")) {
                            return DbColumnType.BOOLEAN;
                        }
                        if (fieldType.toLowerCase().equals("date")) {
                            return DbColumnType.LOCAL_DATE;
                        }
                        if (fieldType.toLowerCase().equals("time")) {
                            return DbColumnType.LOCAL_TIME;
                        }
                        if (fieldType.toLowerCase().equals("datetime")) {
                            return DbColumnType.LOCAL_DATE_TIME;
                        }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                });
            } else if (dbType.equals(DbType.SQL_SERVER)) {
                dataSourceConfig.setTypeConvert(new SqlServerTypeConvert(){
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                });
            }
            // 返回 generator的dataSourceConfig
        return dataSourceConfig;
    }


    /**
     *  获取 dataSourceConfig
     * @version 1.0.1
     * @return
     */
    protected DataSourceConfig getDataSourceConfig() {
        DataSource dataSource = YamlUtils.readDataSourceYaml();
        // 实例化generator dataSourceConfig
        DataSourceConfig dataSourceConfig =  new DataSourceConfig();
        if (dataSource != null) {
            if (dataSource.getDriverClassName().equals(Constants.MYSQL.getValue()) || dataSource.getDriverClassName().equals(Constants.MYSQL_PLUS.getValue()) ){
                // 设置数据库类型
                dataSourceConfig.setDbType(DbType.MYSQL);
                // 数据库类型的转换
                dataSourceConfig.setTypeConvert(new MySqlTypeConvert(){
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        //  数据库类型转换
                        if (fieldType.toLowerCase().equals("bit")) {
                            return DbColumnType.BOOLEAN;
                        }
                        if (fieldType.toLowerCase().equals("date")) {
                            return DbColumnType.LOCAL_DATE;
                        }
                        if (fieldType.toLowerCase().equals("time")) {
                            return DbColumnType.LOCAL_TIME;
                        }
                        if (fieldType.toLowerCase().equals("tinyint")) {
                            return DbColumnType.BOOLEAN;
                        }
                        if (fieldType.toLowerCase().equals("datetime")) {
                            return DbColumnType.LOCAL_DATE_TIME;
                        }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                });
            }else if (dataSource.getDriverClassName().equals(Constants.SQLSERVER.getValue())){
                // 设置数据库类型
                dataSourceConfig.setDbType(DbType.SQL_SERVER);
                // 数据库类型转换
                dataSourceConfig.setTypeConvert(new SqlServerTypeConvert(){
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                });
            } else {
                throw new RuntimeException("NotFound Driver-Class-Name,Please Check It");
            }
            dataSourceConfig
                    // 设置数据库url
                    .setUrl(dataSource.getUrl())
                    // 设置数据库用户名
                    .setUsername(dataSource.getUsername())
                    // 设置数据库密码
                    .setPassword(dataSource.getPassword())
                    // 设置数据库driver
                    .setDriverName(dataSource.getDriverClassName());
        } else {
            // dataSource 为null 则默认找 getDataSourceConfig(DbType.MYSQL);
            dataSourceConfig = getDataSourceConfig(DbType.MYSQL);
        }
        // 返回 generator的dataSourceConfig
        return dataSourceConfig;
    }

    /**
     * 获取TableFill策略
     * @version 1.0.0
     * @return
     */
    protected List<TableFill> getTableFills() {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
        tableFillList.add(new TableFill("createTime", FieldFill.INSERT));
        tableFillList.add(new TableFill("updateTime",FieldFill.INSERT_UPDATE));
        tableFillList.add(new TableFill("createUid",FieldFill.INSERT));
        tableFillList.add(new TableFill("updateUid",FieldFill.INSERT_UPDATE));
        return tableFillList;
    }

    /**
     * 获取 StrategyConfig 生成策略配置
     * @version 1.0.0
     * @param tableNames 数据库表名
     * @param tablePrefix 数据库表前缀
     * @return
     */
    protected StrategyConfig getStrategyConfig(String[] tableNames,String[] tablePrefix) {
        List<TableFill> tableFillList = getTableFills();
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                // 全局大写命名
                .setCapitalMode(false)
                // 数据库表名
                .setInclude(tableNames)
                // 表名生成策略 驼峰命名
                .setNaming(NamingStrategy.underline_to_camel)
                // 设置 TableFill策略
                .setTableFillList(tableFillList)
                // 实体是否使用Lombok插件
                .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                .setEntityBooleanColumnRemoveIsPrefix(true)
                // 控制层是否使用Rest风格
                .setRestControllerStyle(false)
                // 【实体】是否生成字段常量（默认 false）
                .setEntityColumnConstant(true)
                // 【实体】是否为构建者模型（默认 false）
                .setEntityBuilderModel(false);

        if (tablePrefix != null) {
            // 去除表前缀
            strategyConfig.setTablePrefix(tablePrefix);
        }
        return strategyConfig;

    }

    /**
     *  获取 StrategyConfig 生成策略配置
     * @version 1.0.0
     * @param tableNames 数据库表名
     * @param tablePrefix 数据库表前缀
     * @return
     */
    protected StrategyConfig getStrategyConfig(String tableNames,String tablePrefix) {
        List<TableFill> tableFillList = getTableFills();
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                // 全局大写命名
                .setCapitalMode(false)
                // 数据库表名
                .setInclude(tableNames)
                // 表名生成策略 驼峰命名
                .setNaming(NamingStrategy.underline_to_camel)
                // 设置 TableFill策略
                .setTableFillList(tableFillList)
                // 实体是否使用Lombok插件
                .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                .setEntityBooleanColumnRemoveIsPrefix(true)
                // 控制层是否使用Rest风格
                .setRestControllerStyle(false)
                // 【实体】是否生成字段常量（默认 false）
                .setEntityColumnConstant(true)
                // 【实体】是否为构建者模型（默认 false）
                .setEntityBuilderModel(false);

        if (StringUtils.isNotEmpty(tablePrefix)) {
            // 去除表前缀
            strategyConfig.setTablePrefix(tablePrefix);
        }
        return strategyConfig;

    }

    /**
     *  获取 PackageConfig
     * @version 1.0.0
     * @param parentPackageName 父级包
     * @param moduleName 模块名
     * @return
     */
    protected PackageConfig getPackageConfig(String parentPackageName,String moduleName) {
        PackageConfig packageConfig = new PackageConfig();
        packageConfig
                    // 实体类
                    .setEntity("entity")
                    // mapper类
                    .setMapper("mapper")
                    // 服务类
                    .setService("service")
                    // 服务实现类
                    .setServiceImpl("service.impl")
                    // 前端控制器类
                    .setController("controller");
        if (StringUtils.isNotEmpty(parentPackageName)){
            // 设置父级包
            packageConfig.setParent(parentPackageName);
        } else {
            // 设置默认父级包
            packageConfig.setParent("com.github.sdcxy");
        }
        if (StringUtils.isNotEmpty(moduleName)) {
            // 设置模块名
            packageConfig.setModuleName(moduleName);
        }
        return packageConfig;
    }



    /**
     *  获取 GlobalConfig
     * @version 1.0.0
     * @return
     */
    protected GlobalConfig getGlobalConfig(){
        return new GlobalConfig()
                // 作者
                .setAuthor(AUTHOR)
                // 输出目录
                .setOutputDir(getJavaPath())
                // 是否启动AR模式
                .setActiveRecord(true)
                // XML ResultMap
                .setBaseResultMap(false)
                // XML columnList
                .setBaseColumnList(false)
                // XML 二级缓存
                .setEnableCache(false)
                // 是否生成 kotlin 代码
                .setKotlin(false)
                // 不覆盖文件
                .setFileOverride(true)
                // 生成后不打开
                .setOpen(false)
                // 是否开启swagger2
                .setSwagger2(true)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setEntityName("%s")
                .setXmlName("%sMapper")
                .setMapperName("%sMapper")
                .setServiceName("I%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sRestController");

    }


    /**
     *  获取 TemplateConfig
     * @version 1.0.0
     * @return
     */
    protected TemplateConfig getTemplateConfig(){
        // 不配置Mapper.xml文件的生成，后面会配置xml文件生成的自定义存放位置
        return new TemplateConfig().setXml(null);
    }


    /**
     * 获取 InjectionConfig 自定义配置
     * @version 1.0.0
     * @return
     */
    protected InjectionConfig getInjectionConfig(){
        // 自定义配置
        return new InjectionConfig() {
            @Override
            public void initMap() {
                // 自定义填充mapper;
                Map<String, Object> map = new HashMap<>();
                this.setMap(map);
            }
        }.setFileOutConfigList(Collections.singletonList(new FileOutConfig(MAPPER_XML_TEMPLATE){
            // 自定义mapper.xml输出目录 resources/mapper/tableInfo.getEntityName().xml
            @Override
            public String outputFile(TableInfo tableInfo) {
                return  getResourcePath() + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        }));
    }


    /**
     * 获取 AutoGenerator
     * @param parentPackageName 父级包
     * @param moduleName 模块名
     * @param tableNames 数据库表名
     * @param tablePrefix 数据库表名前缀
     * @param dbType 数据库类型
     * @version 1.0.0
     * @return
     */
    @Deprecated
    public AutoGenerator getAutoGenerator(String parentPackageName,String moduleName,
                                          String[] tableNames,String[] tablePrefix,DbType dbType
                                          ){
        return new AutoGenerator()
                // 全局配置
                .setGlobalConfig(getGlobalConfig())
                // 包名配置
                .setPackageInfo(getPackageConfig(parentPackageName,moduleName))
                // 模板配置
                .setTemplate(getTemplateConfig())
                // 自定义配置
                .setCfg(getInjectionConfig())
                //策略配置
                .setStrategy(getStrategyConfig(tableNames,tablePrefix))
                // 配置数据源
                .setDataSource(getDataSourceConfig(dbType));

    }

    /**
     * 获取 AutoGenerator
     * @param parentPackageName 父级包
     * @param moduleName 模块名
     * @param tableNames 数据库表名
     * @param tablePrefix 数据库表名前缀
     * @param dbType 数据库类型
     * @version 1.0.0
     * @return
     */
    @Deprecated
    public AutoGenerator getAutoGenerator(String parentPackageName, String moduleName,
                                          String tableNames,String tablePrefix,DbType dbType
                                          ){
        return new AutoGenerator()
                // 全局配置
                .setGlobalConfig(getGlobalConfig())
                // 包名配置
                .setPackageInfo(getPackageConfig(parentPackageName,moduleName))
                // 模板配置
                .setTemplate(getTemplateConfig())
                // 自定义配置
                .setCfg(getInjectionConfig())
                // 策略配置
                .setStrategy(getStrategyConfig(tableNames,tablePrefix))
                // 配置数据源
                .setDataSource(getDataSourceConfig(dbType));
    }


    /**
     *  获取 AutoGenerator
     * @param parentPackageName 父级包
     * @param moduleName 模块名
     * @param tableNames 数据库表名
     * @param tablePrefix 数据库表名前缀
     * @version 1.0.1
     * @return
     */
    public AutoGenerator getAutoGenerator(String parentPackageName,String moduleName,
                                          String[] tableNames,String[] tablePrefix
    ){
        return new AutoGenerator()
                // 全局配置
                .setGlobalConfig(getGlobalConfig())
                // 包名配置
                .setPackageInfo(getPackageConfig(parentPackageName,moduleName))
                // 模板配置
                .setTemplate(getTemplateConfig())
                // 自定义配置
                .setCfg(getInjectionConfig())
                //策略配置
                .setStrategy(getStrategyConfig(tableNames,tablePrefix))
                // 配置数据源
                .setDataSource(getDataSourceConfig());

    }

    /**
     *  获取 AutoGenerator
     * @param parentPackageName 父级包
     * @param moduleName 模块名
     * @param tableNames 数据库表名
     * @param tablePrefix 数据库表名前缀
     * @version 1.0.1
     * @return
     */
    public AutoGenerator getAutoGenerator(String parentPackageName, String moduleName,
                                          String tableNames,String tablePrefix
    ){
        return new AutoGenerator()
                // 全局配置
                .setGlobalConfig(getGlobalConfig())
                // 包名配置
                .setPackageInfo(getPackageConfig(parentPackageName,moduleName))
                // 模板配置
                .setTemplate(getTemplateConfig())
                // 自定义配置
                .setCfg(getInjectionConfig())
                // 策略配置
                .setStrategy(getStrategyConfig(tableNames,tablePrefix))
                // 配置数据源
                .setDataSource(getDataSourceConfig());
    }
}
