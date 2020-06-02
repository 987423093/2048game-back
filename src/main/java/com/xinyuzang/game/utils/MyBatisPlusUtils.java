package com.xinyuzang.game.utils;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.*;
import java.util.Properties;

/**
 * @author zhoutao
 * @date 2020/6/1
 */
public class MyBatisPlusUtils {

    /**
     * 数据源配置
     * @param propertiesUrl
     * @return
     */
    public static DataSourceConfig getDataSourceConfig(String propertiesUrl) {

        Properties properties = new Properties();
        File file = new File(propertiesUrl);
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(file));
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDriverName(properties.getProperty("driver"));
        dataSourceConfig.setUrl(properties.getProperty("url"));
        dataSourceConfig.setUsername(properties.getProperty("username"));
        dataSourceConfig.setPassword(properties.getProperty("password"));
        return dataSourceConfig;
    }

    /**
     * 全局策略配置
     * @return
     */
    public static GlobalConfig getGlobalConfig() {

        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir") + "\\src\\main\\java");
        globalConfig.setAuthor("XinYuZang");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);
        globalConfig.setMapperName("%sMapper");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setControllerName("%sController");
        globalConfig.setIdType(IdType.AUTO);
        return globalConfig;
    }

    /**
     * 包名设置
     * @return
     */
    public static PackageConfig getPackageConfig() {

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.xinyuzang.game");
        packageConfig.setXml("mapper.xml");
        packageConfig.setEntity("domain.entity");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        return packageConfig;
    }

    /**
     * 数据库表配置
     * @return
     */
    public static StrategyConfig getStrategyConfig() {

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setInclude("user");
        return strategyConfig;
    }
}
