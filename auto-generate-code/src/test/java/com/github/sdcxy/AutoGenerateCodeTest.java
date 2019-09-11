package com.github.sdcxy;

import com.github.sdcxy.modules.generator.MySqlGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName AutoGenerateCodeTest
 * @Description TODO
 * @Author lxx
 * @Date 2019/9/11 12:25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutoGenerateCodeTest.class)
public class AutoGenerateCodeTest {

    @Test
    public void autoGenerateCode(){
        MySqlGenerator mySqlGenerator = new MySqlGenerator();
        String[] tableNames = new String[]{"sys_user","sys_role","sys_dept"
                ,"sys_user_role","sys_menu","sys_role_menu","sys_role_dept"};
        mySqlGenerator.AutoGenerateCode(tableNames,new String[]{"sys_"});
    }
}
