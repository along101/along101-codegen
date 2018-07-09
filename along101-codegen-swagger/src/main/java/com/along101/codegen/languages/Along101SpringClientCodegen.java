package com.along101.codegen.languages;

import io.swagger.codegen.CodegenType;
import io.swagger.codegen.SupportingFile;
import io.swagger.codegen.languages.SpringCodegen;
import io.swagger.models.Scheme;
import io.swagger.models.Swagger;

import java.io.File;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by yinzuolong on 2017/10/30.
 */
public class Along101SpringClientCodegen extends SpringCodegen {

    public Along101SpringClientCodegen() {
        super();
        embeddedTemplateDir = templateDir = "along101-spring";
    }

    @Override
    public CodegenType getTag() {
        return CodegenType.CLIENT;
    }

    @Override
    public String getName() {
        return "along101-spring";
    }

    @Override
    public void processOpts() {
        super.processOpts();
        if (library.equals(SPRING_CLOUD_LIBRARY)) {
            apiTestTemplateFiles.put("api_test.mustache", ".java");
            supportingFiles.add(new SupportingFile("SpringBootApplicationTest.mustache",
                    (testFolder + File.separator + basePackage).replace(".", java.io.File.separator), "SpringBootApplicationTest.java"));
            supportingFiles.add(new SupportingFile("application.mustache",
                    ("src.test.resources").replace(".", java.io.File.separator), "application.properties"));
            supportingFiles.add(new SupportingFile("spring.mustache",
                    ("src.main.resources.META-INF").replace(".", java.io.File.separator), "spring.factories"));
            supportingFiles.add(new SupportingFile("DateTimeDeserializer.mustache",
                    (sourceFolder + File.separator + configPackage).replace(".", java.io.File.separator), "DateTimeDeserializer.java"));
            supportingFiles.add(new SupportingFile("DateTimeSerializer.mustache",
                    (sourceFolder + File.separator + configPackage).replace(".", java.io.File.separator), "DateTimeSerializer.java"));
        }
    }

    @Override
    public void preprocessSwagger(Swagger swagger) {
        super.preprocessSwagger(swagger);
        if (swagger.getSchemes() == null || swagger.getSchemes().size() == 0) {
            swagger.scheme(Scheme.HTTP);
        }
        if (swagger.getVendorExtensions() == null) {
            swagger.setVendorExtensions(new LinkedHashMap<String, Object>());
        }
        if (swagger.getInfo() != null && swagger.getInfo().getVendorExtensions() != null) {
            for (String key : swagger.getInfo().getVendorExtensions().keySet()) {
                if (!swagger.getVendorExtensions().containsKey(key)) {
                    swagger.getVendorExtensions().put(key, swagger.getInfo().getVendorExtensions().get(key));
                }
            }
        }
    }

    public Map<String, Object> postProcessSupportingFileData(Map<String, Object> objs) {
        super.postProcessSupportingFileData(objs);
        if (this.vendorExtensions != null) {
            objs.put("vendorExtensions", vendorExtensions);
        }
        return objs;
    }

    public boolean shouldOverwrite(String filename) {
        if (skipOverwrite && new File(filename).exists()) {
            return false;
        }
        return true;
    }
}
