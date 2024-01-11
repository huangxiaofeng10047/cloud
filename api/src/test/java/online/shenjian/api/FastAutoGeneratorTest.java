package online.shenjian.api;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

/**
 * 根据数据表生成代码工具
 *
 * @author shenjian
 * @since 2023/8/16
 */
public class FastAutoGeneratorTest {

    /**
     * 生成的目录在/tmp/下
     * @param args
     */
    public static void main(String[] args) {
        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig.Builder("jdbc:mysql://127.0.0.1:3309/cloud?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false&allowMultiQueries=true&serverTimezone=Asia/Shanghai","root","123456");
        FastAutoGenerator.create(dataSourceConfig)
            // 全局配置
            .globalConfig((scanner, builder) -> builder.enableSwagger().author("huangxf"))
            // 包配置
            .packageConfig((scanner, builder) -> builder.parent("online.shenjian"))
            // 策略配置
            .strategyConfig((scanner, builder) -> builder.addInclude(getTables("user"))
                .controllerBuilder().enableRestStyle().enableHyphenStyle()
                .entityBuilder().enableLombok().enableTableFieldAnnotation()
                .idType(com.baomidou.mybatisplus.annotation.IdType.INPUT).formatFileName("%sModel")
                .build()
            )
            .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
