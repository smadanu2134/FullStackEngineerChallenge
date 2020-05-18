package com.feedback.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.dto.HelloWorldBean;

@RestController
public class HelloWorldRestController 
{
//using get method and hello-world URI
@GetMapping(path="/hello-world")
public String helloWorld()
{
return "Hello World";
}
@GetMapping(path="/hello-world-bean")
//method- which returns "Hello World"
public HelloWorldBean helloWorldBean()
{
return new HelloWorldBean("Hello World");//constructor of HelloWorldBean
}
}
