package com.churninsight.one.utils;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(title = "ChurnInsight ONE API", version = "1.0", description = "API documentation for ChurnInsight ONE application", contact = @Contact(name = "ChurnInsight Support")))
public class OpenApiConfig {

}
