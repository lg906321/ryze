package own.ryze.application.weixin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Sets;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Slf4j
public class SwaggerConfig
{
	private static String application_json = "application/hal+json;charset=utf-8;";

	@Bean
	public Docket createRestApi()
	{
		log.info("Swagger2初始化");

		ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
		apiInfoBuilder.title("ryze");
		apiInfoBuilder.description("QQ:1159930219");
		apiInfoBuilder.version("1.0");
		apiInfoBuilder.contact(new Contact("因雨而生", "https://github.com/BornToRain", "1159930219@qq.com"));

		Docket docket = new Docket(DocumentationType.SWAGGER_2);
		docket.apiInfo(apiInfoBuilder.build());
		docket.produces(Sets.newHashSet(application_json));
		docket.consumes(Sets.newHashSet(application_json));
		docket.protocols(Sets.newHashSet("http", "https"));
		docket.forCodeGeneration(true);

		return docket.select().apis(RequestHandlerSelectors.basePackage("own.ryze.application.weixin.web"))
				.paths(PathSelectors.any()).build();

	}

}
