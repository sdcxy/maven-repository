package com.github.sdcxy.modules.common.entity;

import lombok.Data;

/**
 * @ClassName DataSource
 * @Description TODO
 * @Author lxx
 * @Date 2019/9/10 0:48
 **/
@Data
public class DataSource {

    private String driverClassName;

    private String url;

    private String username;

    private String password;


}
