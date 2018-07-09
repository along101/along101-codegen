package com.along101.codegen.languages;

import io.swagger.codegen.CodegenType;
import io.swagger.codegen.languages.AbstractJavaCodegen;
import io.swagger.codegen.languages.JavaClientCodegen;
import io.swagger.codegen.languages.features.BeanValidationFeatures;
import io.swagger.codegen.languages.features.OptionalFeatures;

/**
 * Created by yinzuolong on 2017/10/30.
 */
public class Along101JavaClientCodegen extends JavaClientCodegen {
    public Along101JavaClientCodegen() {
        super();
        embeddedTemplateDir = templateDir = "along101-java";
    }

    @Override
    public CodegenType getTag() {
        return CodegenType.CLIENT;
    }

    @Override
    public String getName() {
        return "along101-java";
    }

}
