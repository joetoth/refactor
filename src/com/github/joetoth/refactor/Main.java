package com.github.joetoth.refactor;

import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.Field;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.MethodSource;

import java.io.File;

/**
 * Created by joetoth on 6/14/15.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        JavaClassSource javaClass =
                Roaster.parse(JavaClassSource.class, new File("/home/joetoth/projects/parze/src/com/company/Patch.java"));

        Field f = javaClass.getField("o");
        javaClass.removeField(f);
        javaClass.addField("String o");

        MethodSource ctor = getCtor(javaClass);
        ctor.removeParameter("Object", "o");
        ctor.addParameter("String", "o");
        ctor.setBody(ctor.getBody() + " System.out.println();");


//        Object t = s.getGoverningType();


//        Annotation a = javaClass.getAnnotation("Deprecated");

//        javaClass.addMethod()
//                .setPublic()
//                .setStatic(true)
//                .setName("main")
//                .setReturnTypeVoid()
//                .setBody("System.out.println(\"Hello World\");")
//                .addParameter("java.lang.String[]", "args");
        System.out.println(javaClass);
    }

    private static MethodSource getCtor(JavaClassSource s) {
        for (MethodSource<JavaClassSource> m : s.getMethods()) {
            if (m.isConstructor()) {
                return m;
            }
        }
        return null;
    }
}
