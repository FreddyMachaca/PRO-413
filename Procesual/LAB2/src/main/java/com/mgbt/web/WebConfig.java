package com.mgbt.web;

import java.util.Locale;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

//Esta clase es un listener, es necesaria para implementar internacionalización (i18n)
@Configuration //Esta clase debe ser un Bean de configuración
public class WebConfig implements WebMvcConfigurer {
    
    //Este método es para configurar el idioma por defecto de la aplicación. A su vez crea una instancia
    //del objeto SessionLocaleResolver y lo agrega al contenedor de Spring gracias a @Bean
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver();
        slr.setDefaultLocale(new Locale("es"));
        return slr;
    }
    
    //Este es un interceptor que permite cambiar de idioma de manera dinámica
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        var lci = new LocaleChangeInterceptor();
        lci.setParamName("lang"); //Cuando se adjunte el parametro lang en las URL, indica cual es el lenguaje a utilizar
        return lci;
    }
    
    //Método para registrar el interceptor
    @Override
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registro) {
        //Paths a los que se puede acceder sin pasar por ningun controlador
        registro.addViewController("/").setViewName("index");
        registro.addViewController("/login");
        registro.addViewController("/errores/403").setViewName("/errores/403");
    }
}
