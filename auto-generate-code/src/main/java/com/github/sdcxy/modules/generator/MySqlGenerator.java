package com.github.sdcxy.modules.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;

/**
 * @ClassName MySqlGenerator
 * @Description TODO
 * @Author lxx
 * @Date 2019/9/9 16:21
 **/
@Deprecated
public class MySqlGenerator extends SuperGenerator {

    private AutoGenerator autoGenerateCode(String parentPackageName,String moduleName,String tableName,String tablePrefix){
        return  getAutoGenerator(parentPackageName,moduleName, tableName,tablePrefix, DbType.MYSQL);
    }

    public void AutoGenerateCode(String parentPackageName,String moduleName,String tableName,String tablePrefix){
        this.autoGenerateCode(parentPackageName,moduleName,tableName,tablePrefix).execute();
    }

    public void AutoGenerateCode(String parentPackageName,String tableName,String tablePrefix){
        this.autoGenerateCode(parentPackageName,null,tableName,tablePrefix).execute();
    }

    public void AutoGenerateCode(String tableName,String tablePrefix){
        this.autoGenerateCode(null,null,tableName,tablePrefix).execute();
    }

    public void AutoGenerateCode(String tableName){
        this.autoGenerateCode(null,null,tableName,null).execute();
    }



    private AutoGenerator autoGenerateCode(String parentPackageName,String moduleName,String[] tableName,String[] tablePrefix){
        return getAutoGenerator(parentPackageName,moduleName,tableName,tablePrefix, DbType.MYSQL);
    }

    public void AutoGenerateCode(String parentPackageName,String moduleName,String[] tableName,String[] tablePrefix){
        autoGenerateCode(parentPackageName, moduleName, tableName, tablePrefix).execute();
    }

    public void AutoGenerateCode(String parentPackageName,String[] tableName,String[] tablePrefix){
        autoGenerateCode(parentPackageName, null, tableName, tablePrefix).execute();
    }

    public void AutoGenerateCode(String[] tableName,String[] tablePrefix){
        autoGenerateCode(null, null, tableName, tablePrefix).execute();
    }

    public void AutoGenerateCode(String[] tableName){
        autoGenerateCode(null, null, tableName, null).execute();
    }

}
