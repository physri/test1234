package com.physri.rest.webservices.restfulwebservices.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.physri.rest.webservices.restfulwebservices.model.HelloWorldResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class HelloWorldDemoController {
    @Autowired
    MessageSource messageSource;
    @GetMapping("hw-demo")
    public String myBasicHelloWorldApi() {
        return "REST api testing!!!!!";
    }

    @GetMapping("hw-bean-demo")
    public HelloWorldResp myHelloWorldBeanVer() {
        HelloWorldResp helloWorldResp = new HelloWorldResp();
        helloWorldResp.setMsg("REST api testing - HW bean!!!!!");
        helloWorldResp.setStatus("SUCCESS");
        return helloWorldResp;
    }

    @GetMapping("hw-path-var-demo/quantity/{quantity}")
    public HelloWorldResp myHelloWorldBeanWithPathVar(@PathVariable int quantity) {
        HelloWorldResp helloWorldResp = new HelloWorldResp();
        helloWorldResp.setMsg("REST api testing - HW bean!!!!!");
        helloWorldResp.setStatus("SUCCESS");
        helloWorldResp.setQuantity(quantity);
        return helloWorldResp;
    }

    @GetMapping("hw-demo-i18n")
    public String myBasicHelloWorldI18NApi() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.msg",null, "default msg", locale);
        //return "REST api testing!!!!!";
    }

    @GetMapping("hw-hateoas-demo")
    public MappingJacksonValue myBasicHelloWorldHateoasApi() {
        HelloWorldResp helloWorldResp = new HelloWorldResp();
        helloWorldResp.setMsg("REST api testing - HW bean!!!!!");
        helloWorldResp.setStatus("SUCCESS");
        EntityModel entityModel = EntityModel.of(helloWorldResp);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).myHelloWorldBeanVer());
        entityModel.add(link.withRel("hateoas-demo-link"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(helloWorldResp);
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("status");
        FilterProvider filters =
                new SimpleFilterProvider().addFilter("SomeBeanFilter", filter );
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}