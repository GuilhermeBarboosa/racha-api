package br.com.gui.racha.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfiguration {


    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.gui"))
                .build()
                .apiInfo(getApiInfo())
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.POST, responseMessageForPost()) // responses padrão para POST e PUT
                .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
                .globalResponseMessage(RequestMethod.PUT, responseMessageForPost()) // responses padrão para POST e PUT
                .globalResponseMessage(RequestMethod.DELETE, responseMessageForDELETE());

    }

    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Racha API")
                .description("API para racha")
                .contact(new Contact("GUI", "", "guilherme.rocha@codiub.com.br"))
                .version("1.0.0")
                .build();
    }

    private List<ResponseMessage> responseMessageForGET() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Erro interno no servidor.")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Você não tem permissão para acessar este recurso.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("A requisição solicitada é inválida.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(401)
                    .message("Não Autorizado.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("A requisição solicitada não foi encontrada.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(200)
                    .message("A requisição solicitada foi executada com sucesso.")
                    .build());
        }};
    }

    private List<ResponseMessage> responseMessageForPost() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Erro interno no servidor.")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Você não tem permissão para acessar este recurso.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("A requisição solicitada é inválida.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(401)
                    .message("Não Autorizado.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(200)
                    .message("A requisição solicitada foi executada com sucesso.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(201)
                    .message("A requisição solicitada executada com sucesso, recurso criado/atualizado.")
                    .build());
        }};
    }


    private List<ResponseMessage> responseMessageForDELETE() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Erro interno no servidor.")
                    .responseModel(new ModelRef("Error"))
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Você não tem permissão para acessar este recurso.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("A requisiçào solicitada é inválida.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(401)
                    .message("Não Autorizado.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("A requisição solicitada não foi encontrada.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(200)
                    .message("A requisição solicitada foi executada com sucesso.")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(204)
                    .message("A requisição solicitada foi executada com sucesso. Recurso removido. ")
                    .build());
        }};
    }

}