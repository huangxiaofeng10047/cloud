package online.shenjian.api.config;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hxf16
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder().group("public")
            // 指定路径
            .pathsToMatch("/**")
            // 指定特定的 API 文档信息
            .addOpenApiCustomizer(userApiCustomizer()).build();
    }

    /**
     * 定义 OpenApiCustomizer ，用于指定的 group
     * @return
     */
    public OpenApiCustomizer userApiCustomizer() {
        final List<Server> servers = new ArrayList<>();
        servers.add(new Server().url("http://localhost:8080").description("local Env"));
        //        return openApi -> openApi.info(new io.swagger.v3.oas.models.info.Info().title("Cloud API文档")
        //        .version("1.0")
        //                .contact(new io.swagger.v3.oas.models.info.Contact().name("").email(""))).servers(servers)
        //            // 接口增加权限校验，如果接口需要，添加 security = { @SecurityRequirement(name = "token")}即可
        //            .components(new Components()
        //                .addSchemas("ResponseVo",getSchemaWithDifferentDescription(ResponseVo.class,"local
        //                responsevo")
        //            )
        //                .addSchemas("UserInfoDto",getSchemaWithDifferentDescription(ResponseVo.class,"local
        //                responsevo"))
        //                .addSecuritySchemes("token",
        //                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
        return openApi -> openApi.info(new io.swagger.v3.oas.models.info.Info().title("Cloud API文档").version("1.0")
                .contact(new io.swagger.v3.oas.models.info.Contact().name("").email(""))).servers(servers)
            // 接口增加权限校验，如果接口需要，添加 security = { @SecurityRequirement(name = "token")}即可
            ;

    }

    private Schema getSchemaWithDifferentDescription(Class className, String description) {
        ResolvedSchema resolvedSchema =
            ModelConverters.getInstance().resolveAsResolvedSchema(new AnnotatedType(className).resolveAsRef(false));
        return resolvedSchema.schema.description(description);
    }
}

