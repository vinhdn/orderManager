package com.vinhdn.order.manager.config

import com.vinhdn.order.manager.interceptor.CommonInterceptor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConfig: WebMvcConfigurer {

    @Autowired
    lateinit var commonInterceptor: CommonInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        addTokenInterceptorTo(registry)
    }

    /**
     * set check token authentication  exclude
     * @param registry
     */
    private fun addTokenInterceptorTo(registry: InterceptorRegistry) {
        registry.addInterceptor(commonInterceptor)
            .excludePathPatterns("/**/auth/**")
            .excludePathPatterns("/**/ping")
            .excludePathPatterns("/**/error")
            .excludePathPatterns("/**/swagger-resources/**")
            .excludePathPatterns("/**/swagger-ui.html")
            .excludePathPatterns("/**/admin/**")
            .addPathPatterns("/**")
    }
}