package com.github.sdcxy.modules.common.util;

import com.github.sdcxy.modules.common.entity.DataSource;
import com.github.sdcxy.modules.common.enums.Constants;
import org.ho.yaml.Yaml;

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
        Map resultMap;
        try {
            // 判断 application.yml 或者 dataSource.xml 是否存在
            if (applicationFile.exists() || dataSourceFile.exists()) {
                // 读取配置文件
                Map dataSourceMap = Yaml.loadType(applicationFile, HashMap.class);
                // 获取dataSource的配置属性 保存在map 中
                resultMap = (HashMap)dataSourceMap.get("dataSource");
                // 判断是否获取到dataSource属性
                if (resultMap != null) {
                    dataSource = new DataSource();
                    if (resultMap.get("driver-class-name") == null) {throw new NullPointerException("driver-class-name property was not found");}
                    if (resultMap.get("url") == null) {throw new NullPointerException("url property was not found");}
                    if (resultMap.get("username") == null) {throw new NullPointerException("username property was not found");}
                    if (resultMap.get("password") == null) {throw new NullPointerException("password property was not found");}
                    dataSource.setDriverClassName(resultMap.get("driver-class-name") + "");
                    dataSource.setUrl(resultMap.get("url") + "");
                    dataSource.setUsername(resultMap.get("username")+ "");
                    dataSource.setPassword(resultMap.get("password")+ "");
                } else {
                    throw new NullPointerException("The dataSource property was not found in the configuration file");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("application.yml or dataSource.xml was not found");
        }
        return dataSource;
    }

    public void writeDataSourceYaml(){

    }

}
