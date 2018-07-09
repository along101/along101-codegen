package com.along101.codegen.languages;

import io.swagger.codegen.ClientOptInput;
import io.swagger.codegen.DefaultGenerator;
import io.swagger.codegen.config.CodegenConfigurator;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by yinzuolong on 2017/11/17.
 */
public class JavaClientCodegenTest {

    private String folder = "../testGenerate/java";


    @Before
    public void setUp() throws Exception {
        FileUtils.deleteDirectory(new File(folder));
    }


    @Test
    public void testJavaClient() throws Exception {
        CodegenConfigurator configurator = CodegenConfigurator.fromFile("src/test/resources/config.json");
        configurator.setLang("java");
        configurator.setLibrary("jersey1");
        configurator.setOutputDir(folder);
        configurator.setInputSpec("src/test/resources/petstore.json");
        ClientOptInput clientOptInput = configurator.toClientOptInput();

        new DefaultGenerator().opts(clientOptInput).generate();

    }
}
