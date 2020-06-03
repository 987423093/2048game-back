package com.xinyuzang.game.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;

/**
 * @author zhoutao
 * @date 2020/6/1
 */
public class MyBatisPlusGenerator {

    public static void main(String[] args) {

        new MyBatisPlusGenerator().createMyBatisPlus();
    }

    private void createMyBatisPlus() {

        AutoGenerator autoGenerator = new AutoGenerator();
        //设置数据源
        String propertiesUrl = System.class.getResource("/db.properties").getPath();
        autoGenerator.setDataSource(MyBatisPlusUtil.getDataSourceConfig(propertiesUrl));
        //设置全局配置
        autoGenerator.setGlobalConfig(MyBatisPlusUtil.getGlobalConfig());
        //包名设置
        autoGenerator.setPackageInfo(MyBatisPlusUtil.getPackageConfig());
        //数据库配置
        autoGenerator.setStrategy(MyBatisPlusUtil.getStrategyConfig());
        //自定义配置
        InjectionConfig injectionConfig = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        autoGenerator.setCfg(injectionConfig);
        autoGenerator.execute();
    }
}
