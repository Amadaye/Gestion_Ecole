package com.gestionEcole.gestionEcole.config;

import com.gestionEcole.gestionEcole.utils.Constants;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.gestionEcole.gestionEcole.utils.Constants.APP_ROOT;

//@Configuration
//@EnableSwagger2
public class SwaggerConfiguration {

    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(
                        new ApiInfoBuilder()
                                .description("Systeme de gestion des ecoles API documentation")
                                .title("Gestion des ecoles API")
                                .build()
                )
                .groupName("REST API pour la gestion des ecoles")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gestionEcole.gestionEcole"))
                .paths(PathSelectors.ant(APP_ROOT + " /**"))
                .build();
    }
}
