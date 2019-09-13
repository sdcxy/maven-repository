package com.github.sdcxy.modules.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;

/**
 * @ClassName GeneratorFactory
 * @Description TODO
 * @Author lxx
 * @version 1.0.1
 * @Date 2019/9/13 8:09
 **/
public class GeneratorFactory extends SuperGenerator {

    /**
     * 获取 AutoGenerator
     * @param parentPackageName 父级包
     * @param moduleName 模块名称
     * @param tableName 表名
     * @param tablePrefix 表前缀
     * @return
     */
    private AutoGenerator create(String parentPackageName, String moduleName, String tableName, String tablePrefix){
        return getAutoGenerator(parentPackageName, moduleName, tableName, tablePrefix);
    }

    public void autoGenerateCode(String parentPackageName, String moduleName, String tableName, String tablePrefix){
        this.create(parentPackageName,moduleName,tableName,tablePrefix).execute();
    }

    public void autoGenerateCode(String parentPackageName, String tableName,String tablePrefix){
        this.create(parentPackageName,null,tableName,tablePrefix).execute();
    }

    public void autoGenerateCode(String parentPackageName,String tableName){
        this.create(parentPackageName,null,tableName,null).execute();
    }

    public void autoGenerateCode(String tableName){
        this.create(null,null,tableName,null).execute();
    }



    /**-------------数据表数组----------**/

    private AutoGenerator create(String parentPackageName, String moduleName, String[] tableName, String[] tablePrefix){
        return getAutoGenerator(parentPackageName, moduleName, tableName, tablePrefix);
    }

    public void autoGenerateCode(String parentPackageName, String moduleName, String[] tableName, String[] tablePrefix){
        this.create(parentPackageName,moduleName,tableName,tablePrefix).execute();
    }

    public void autoGenerateCode(String parentPackageName, String[] tableName,String[] tablePrefix){
        this.create(parentPackageName,null,tableName,tablePrefix).execute();
    }

    public void autoGenerateCode(String parentPackageName,String[] tableName){
        this.create(parentPackageName,null,tableName,null).execute();
    }

    public void autoGenerateCode(String[] tableName){
        this.create(null,null,tableName,null).execute();
    }


}
