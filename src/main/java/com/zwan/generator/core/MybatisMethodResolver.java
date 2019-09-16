package com.zwan.generator.core;

import org.apache.ibatis.builder.annotation.MapperAnnotationBuilder;
import org.apache.ibatis.builder.annotation.MethodResolver;

import java.lang.reflect.Method;

/**
 * 继承 {@link MethodResolver}
 *
 * @author miemie
 * @since 2019-01-05
 */
public class MybatisMethodResolver extends MethodResolver {

    private final MybatisMapperAnnotationBuilder annotationBuilder;
    private final Method method;

    public MybatisMethodResolver(MapperAnnotationBuilder annotationBuilder, Method method) {
        super(annotationBuilder, method);
        this.annotationBuilder = (MybatisMapperAnnotationBuilder) annotationBuilder;
        this.method = method;
    }

    @Override
    public void resolve() {
        annotationBuilder.parseStatement(method);
    }
}
