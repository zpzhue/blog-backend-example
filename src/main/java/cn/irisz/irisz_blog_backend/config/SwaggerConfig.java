package cn.irisz.irisz_blog_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * SwaggerConfig类
 * </p>
 *
 * @author 朱志鹏
 * @since 2021/9/5 0005
 */

@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket defaultApi() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("admin")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.irisz.irisz_blog_backend.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContext());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("IRISZ博客接口")
                .description("springboot | swagger")
                // 作者信息
                .contact(new Contact("name", "个人主页url", "email"))
                .version("1.0.0")
                .build();
    }


    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> result = new ArrayList<>();
        result.add(new ApiKey("Authorization", "Authorization", "header"));
        return result;
    }

    private List<SecurityContext> securityContext() {
        List<SecurityContext> result = new ArrayList<>();
        AntPathMatcher matcher = new AntPathMatcher();
        boolean match = matcher.match("/account/login", "/account/login");
        System.out.println(match);
        SecurityContext context = SecurityContext.builder()
                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.regex("/.*").and(PathSelectors.ant("/account/login")))
                .forPaths(PathSelectors.none())
                .build();

        result.add(context);
        return result;
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("admin","access admin");
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        scopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization", scopes));
        return result;
    }
}
