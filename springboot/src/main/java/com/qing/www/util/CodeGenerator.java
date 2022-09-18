package com.qing.www.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * Mybatis-plus代码生成器
 */
public class CodeGenerator {
    public static final String DATASOURCE="anyview";
    public static final String basePath="G:\\Work\\workspace\\web\\Anyview\\springboot\\";
    public static final String tableName="t_user_token";
    public static final String modelName="";

    public static final String url="jdbc:mysql://localhost:3306/"+DATASOURCE;
    public static final String username="root";
    public static final String password="root";

    public static void main(String[] args) throws Exception{
        //生成SpringBoot
        generate(tableName);
    }

    public static void generate(String tableName){
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Gin") // 设置作者
                            .outputDir("G://Work//workspace//web//Anyview//springboot//src//main//java//"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.qing.www") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "G://Work//workspace//web//Anyview//springboot//src//main//resources//mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder().enableLombok();
                    builder.mapperBuilder().build();
                    builder.controllerBuilder().enableHyphenStyle()  // 开启驼峰转连字符
                            .enableRestStyle();  // 开启生成@RestController 控制器
                    builder.addInclude(tableName) // 设置需要生成的表名
                            .addTablePrefix("t_"); // 设置过滤表前缀
                })
                .templateConfig(builder -> {
                    builder.controller("/templates/controller.java");
                })
                .execute();
    }
}
