package com.hjy;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;


/**
 * @author： hjy
 * @date： 2021/2/18 0018,下午 19:49
 * @email: 541605007@qq.com
 * <p>
 * 代码自动生成
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        //  设置作者
        gc.setAuthor("hjy");
        //  是否打开资源管理器
        gc.setOpen(false);
        //  是否覆盖
        gc.setFileOverride(false);
        //  去Service的I前缀
        gc.setServiceName("%sService");
        //  设置主键策略
        gc.setIdType(IdType.ASSIGN_ID);
        //  设置日期类型
        gc.setDateType(DateType.ONLY_DATE);
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        //  2、数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/wuai?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        // 3、包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("wuai");
        pc.setParent("com.hjy");
        pc.setEntity("pojo");
        pc.setMapper("xml");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        //  4、策略配置
        StrategyConfig strategy = new StrategyConfig();
        //  包的驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //  列的驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //  设置表名的映射
        strategy.setInclude("music","game","video");
        //  自动生成Lombok
        strategy.setEntityLombokModel(true);
        //  逻辑删除
        strategy.setLogicDeleteFieldName("is_deleted");
        //  自动填充配置
        TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
        TableFill updateTime = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFills = new ArrayList<>();
        tableFills.add(createTime);
        tableFills.add(updateTime);
        strategy.setTableFillList(tableFills);
        //  乐观锁配置
        strategy.setVersionFieldName("version");
        //  开启驼峰命令风格
        strategy.setRestControllerStyle(true);
        //  链接驼峰命名  localhost:8080?/hello_id_2
        strategy.setControllerMappingHyphenStyle(true);

        mpg.setStrategy(strategy);

        //  执行
        mpg.execute();

    }


}
