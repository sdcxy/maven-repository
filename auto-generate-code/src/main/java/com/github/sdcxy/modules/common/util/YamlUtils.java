package com.github.sdcxy.modules.common.util;

import com.github.sdcxy.modules.common.entity.DataSource;
import com.github.sdcxy.modules.common.enums.Constants;
import org.ho.yaml.Yaml;
import org.ho.yaml.exception.YamlException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @className YamlUtils
 * @description TODO
 * @author lxx
 * @version 1.0.1
 * @date 2019/9/12 9:20
 **/
public class YamlUtils {

    /**
     * 读取配置文件 application.yml 或者 dataSource.yml
     * @return
     */
    public static DataSource readDataSourceYaml(){
        DataSource dataSource = null;
        // 获取文件路径
        String applicationYaml = System.getProperty("user.dir") + "/src/main/resources/" + Constants.DEFAULT_APPLICATION_YAML_PATH.getValue();
        String dataSourceYaml = System.getProperty("user.dir") + "/src/main/resources/" + Constants.DEFAULT_DATASOURCE_YAML_PATH.getValue();

        File applicationFile = new File(applicationYaml);
        File dataSourceFile = new File(dataSourceYaml);
        try {
            Map map = null;
            // 判断 application.yml 或者 dataSource.xml 是否存在 两者都存在以application.yml为主
            if (applicationFile.exists()) {
                // 读取配置文件
                map = Yaml.loadType(applicationFile, HashMap.class);
            } else {
                if (dataSourceFile.exists()) {
                    // 读取配置文件
                    map = Yaml.loadType(dataSourceFile, HashMap.class);
                } else {
                    throw new FileNotFoundException("application.yml or dataSource.xml was not found");
                }
            }
            // 判断 map 是否为null
            if(map != null) {
                // 获取spring的配置属性 保存在map 中
                Map springMap = (HashMap) map.get("spring");
                // 判断是否获取到spring属性
                if (springMap != null) {
                    Map dataSourceMap = (HashMap) springMap.get("dataSource");
                    // 判断dataSource属性是否存在
                    if (dataSourceMap != null) {
                        dataSource = new DataSource();
                        // 判断 driver-class-name
                        if (dataSourceMap.get("driver-class-name") == null) {
                            throw new NullPointerException("driver-class-name property was not found");
                        }else {
                            dataSource.setDriverClassName(dataSourceMap.get("driver-class-name") + "");
                        }

                        // 判断url
                        if (dataSourceMap.get("url") == null) {
                            // 使用HikariDataSource 的url 为jdbc-url
                            if (dataSourceMap.get("jdbc-url") == null ) {
                                throw new NullPointerException("url property was not found");
                            }else {
                                dataSource.setUrl(dataSourceMap.get("jdbc-url") + "");
                            }
                        }else {
                            dataSource.setUrl(dataSourceMap.get("url") + "");
                        }

                        // 判断 username
                        if (dataSourceMap.get("username") == null) {
                            throw new NullPointerException("username property was not found");
                        }else {
                            dataSource.setUsername(dataSourceMap.get("username")+ "");
                        }

                        // 判断 password
                        if (dataSourceMap.get("password") == null) {
                            throw new NullPointerException("password property was not found");
                        }else {
                            dataSource.setPassword(dataSourceMap.get("password")+ "");
                        }

                    }else {
                        throw new NullPointerException("The dataSource property was not found in the configuration file");
                    }
                }else {
                    // 旧版本的实现方式
                    Map dataSourceOldMap = (HashMap) map.get("dataSource");
                    // 判断dataSource属性是否存在
                    if (dataSourceOldMap != null) {
                        dataSource = new DataSource();
                        // 判断 driver-class-name
                        if (dataSourceOldMap.get("driver-class-name") == null) {
                            throw new NullPointerException("driver-class-name property was not found");
                        }else {
                            dataSource.setDriverClassName(dataSourceOldMap.get("driver-class-name") + "");
                        }

                        // 判断url
                        if (dataSourceOldMap.get("url") != null) {
                            dataSource.setUrl(dataSourceOldMap.get("url") + "");
                        }else {
                            // 使用HikariDataSource 的url 为jdbc-url
                            if (dataSourceOldMap.get("jdbc-url") != null ) {
                                dataSource.setUrl(dataSourceOldMap.get("jdbc-url") + "");
                            }else {
                                throw new NullPointerException("url property was not found");
                            }
                        }

                        // 判断 username
                        if (dataSourceOldMap.get("username") == null) {
                            throw new NullPointerException("username property was not found");
                        }else {
                            dataSource.setUsername(dataSourceOldMap.get("username")+ "");
                        }

                        // 判断 password
                        if (dataSourceOldMap.get("password") == null) {
                            throw new NullPointerException("password property was not found");
                        }else {
                            dataSource.setPassword(dataSourceOldMap.get("password")+ "");
                        }
                    }else {
                        throw new NullPointerException("The spring property was not found in the configuration file");
                    }
                }
            }else {
                throw new FileNotFoundException("application.yml or dataSource.xml was not found");
            }

        } catch (FileNotFoundException e) {
            System.out.println("application.yml or dataSource.xml was not found");
        } catch (YamlException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
        }
        return dataSource;
    }
}
