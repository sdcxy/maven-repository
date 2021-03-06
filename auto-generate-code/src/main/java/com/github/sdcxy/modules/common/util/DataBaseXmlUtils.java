package com.github.sdcxy.modules.common.util;


import com.github.sdcxy.modules.common.entity.DataSource;
import com.github.sdcxy.modules.common.enums.Constants;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * @className DataBaseXmlUtils
 * @description TODO
 * @author lxx
 * @version 1.0.0
 * @date 2019/9/10 0:20
 **/
public class DataBaseXmlUtils {


    private static final String MYSQL_URL = "jdbc:mysql://IP:PORT/DB_NAME?characterEncoding=utf8&useSSL=false&useUnicode=true&autoReconnect=true&serverTimezone=Asia/Shanghai";

    private static final String SQL_SERVER_URL = "jdbc:sqlserver://IP:PORT;DatabaseName=DB_NAME";


    /**
     *  获取配置文件的路径
     * @version 1.0.1
     * @return
     */
    private static String getDataBaseXmlPath(){
        // 获取根目录的配置文件
        return System.getProperty("user.dir") + "/src/main/resources/" + Constants.DEFAULT_DATABASE_XML_PATH.getValue();
    }

    /**
     *  读取xml 配置文件
     * @param dbType 数据库类型
     * @version 1.0.0
     * @return
     */
    @Deprecated
    public static DataSource readDataBaseXml(String dbType){
        // 创建一个dataSource实体类来接收数据
        DataSource dataSource = new DataSource();

        // 获取根目录的配置文件
        String filePath = getDataBaseXmlPath();

        File file = new File(filePath);

        if (!file.exists()) {
            try {
                throw new FileNotFoundException("文件不存在");
            } catch (FileNotFoundException e) {
                System.out.println(filePath + "文件不存在");
            }
        }
        // 读取配置文件
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            // 得到根目录
            Element root = document.getRootElement();
            // 数据库配置的父节点
            Element dbTypeName;

            if (dbType.equals("mysql")) {
                dbTypeName = root.element("mysql");
                dataSource.setDriverClassName(Constants.MYSQL.getValue());
                dataSource.setUrl(MYSQL_URL.replace("IP",dbTypeName.elementText("ip"))
                        .replace("PORT",dbTypeName.elementText("port"))
                        .replace("DB_NAME",dbTypeName.elementText("dbName")));
                dataSource.setUsername(dbTypeName.elementText("username"));
                dataSource.setPassword(dbTypeName.elementText("password"));
            }else if (dbType.equals("sqlserver")) {
                dbTypeName = root.element("sqlserver");
                dataSource.setDriverClassName(Constants.SQLSERVER.getValue());
                dataSource.setUrl(SQL_SERVER_URL.replace("IP",dbTypeName.elementText("ip"))
                        .replace("PORT",dbTypeName.elementText("port"))
                        .replace("DB_NAME",dbTypeName.elementText("dbName")));
                dataSource.setUsername(dbTypeName.elementText("username"));
                dataSource.setPassword(dbTypeName.elementText("password"));

            }

        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }
        return dataSource;
    }

    /**
     *  写入xml
     * @param dbType 数据库类型
     * @version 1.0.0
     * @param dataSource 写入的数据库参数类
     */
    @Deprecated
    public static void writeDataBaseXml(String dbType,DataSource dataSource){

        String filePath = getDataBaseXmlPath();

        File file = new File(filePath);

        if (!file.exists()) {
            try {
                throw new FileNotFoundException("文件不存在");
            } catch (FileNotFoundException e) {
                System.out.println(filePath + "文件不存在");
            }
        }

        try{
            SAXReader reader = new SAXReader();

            Document document = reader.read(file);

            Element root = document.getRootElement();
            // 获取需要写入的实体类的参数
            String url = dataSource.getUrl();
            String username = dataSource.getUsername();
            String password = dataSource.getPassword();

            Element dbTypeName;

            if (dbType.equals("mysql")){
                dbTypeName = root.element(dbType);
                String subUrl = url.substring(url.indexOf("//") + 2,url.indexOf("?"));
                // 解析url 获取ip , port dbName
                String ip = subUrl.substring(0,subUrl.indexOf(":"));
                String port = subUrl.substring(subUrl.indexOf(":") + 1,subUrl.indexOf("/"));
                String dbName = subUrl.substring(subUrl.indexOf("/") + 1);
                // 设置参数
                dbTypeName.element("ip").setText(ip);
                dbTypeName.element("port").setText(port);
                dbTypeName.element("dbName").setText(dbName);
                dbTypeName.element("username").setText(username);
                dbTypeName.element("password").setText(password);
//                log.info("设置mysql参数成功!-->ip:[{}],port:[{}],dbName:[{}],username:[{}],password:[{}]",ip,port,dbName,username,password);

            } else if(dbType.equals("sqlserver")){
                dbTypeName = root.element(dbType);
                String subUrl = url.substring(url.indexOf("//") + 2);

                // 解析url 获取ip , port dbName
                String ip = subUrl.substring(0,subUrl.indexOf(":"));
                String port = subUrl.substring(subUrl.indexOf(":") + 1,subUrl.indexOf(";"));
                String dbName = subUrl.substring(subUrl.indexOf("=") + 1);
                // 设置参数
                dbTypeName.element("ip").setText(ip);
                dbTypeName.element("port").setText(port);
                dbTypeName.element("dbName").setText(dbName);
                dbTypeName.element("username").setText(username);
                dbTypeName.element("password").setText(password);
//                log.info("设置sqlserver参数成功!-->ip:[{}],port:[{}],dbName:[{}],username:[{}],password:[{}]",ip,port,dbName,username,password);
            }

            XMLWriter writer = new XMLWriter(new FileWriter(file));
            writer.write(document);
            writer.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
