package com.qishanor.common.data.mybatis;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jackson 配置类
 * 解决 Long 类型精度丢失问题，将 Long 类型序列化为 String
 */
@Configuration
public class JacksonTypeConvertConfig {

    /**
     * 配置 Jackson 序列化器，将 Long 类型序列化为 String
     * 解决前端 JavaScript 精度丢失问题（JavaScript Number 类型只能安全表示 53 位整数）
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            // 将 Long 类型序列化为 String
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.serializerByType(Long.TYPE, ToStringSerializer.instance);
        };
    }
}





