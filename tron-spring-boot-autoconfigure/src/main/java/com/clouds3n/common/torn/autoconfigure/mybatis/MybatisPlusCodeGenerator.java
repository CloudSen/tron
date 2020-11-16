package com.clouds3n.common.torn.autoconfigure.mybatis;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.sql.DataSource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Mybatis Plus 代码生成器
 *
 * @author CloudS3n
 */
public class MybatisPlusCodeGenerator {

    private static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void run(GeneratorParam generatorParam) {
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + generatorParam.getOutputPath());
        gc.setAuthor(generatorParam.getCodeAuthor());
        gc.setOpen(false);
        // 设置主键是手动赋值
        gc.setIdType(generatorParam.getIdType());
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(generatorParam.getDatasource().getDbUrl());
        dsc.setDriverName(generatorParam.getDatasource().getDriver());
        dsc.setUsername(generatorParam.getDatasource().getUsername());
        dsc.setPassword(generatorParam.getDatasource().getPassword());
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(null);
        pc.setParent(generatorParam.getParentPackage());
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                if (StringUtils.isBlank(pc.getModuleName())) {
                    return projectPath + generatorParam.getSqlXmlLocation() + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                } else {
                    return projectPath + generatorParam.getSqlXmlLocation() + pc.getModuleName()
                        + File.separator + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
                }
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(generatorParam.getEnableRestController());
        strategy.setChainModel(generatorParam.getEnableChain());
        strategy.setEntitySerialVersionUID(true);
        strategy.setEntityBooleanColumnRemoveIsPrefix(true);
        // 生成实体类时，默认去调T开头的表前缀
        strategy.setTablePrefix(generatorParam.getTablePrefix());
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setEntityLombokModel(generatorParam.getEnableLombok());
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    @Data
    @Accessors(chain = true)
    public static class GeneratorParam {
        private GeneratorDatasource datasource;
        private String outputPath;
        private String parentPackage;
        private String sqlXmlLocation;
        private String codeAuthor;
        private IdType idType = IdType.INPUT;
        private Boolean enableLombok = true;
        private Boolean enableRestController = true;
        private Boolean enableChain = true;
        private String[] tablePrefix = new String[]{"T_"};
    }

    @Data
    @Accessors(chain = true)
    public static class GeneratorDatasource {
        private String dbUrl;
        private String driver;
        private String username;
        private String password;
    }
}
