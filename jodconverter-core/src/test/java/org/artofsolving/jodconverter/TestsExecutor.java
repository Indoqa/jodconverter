package org.artofsolving.jodconverter;

import java.util.HashSet;
import java.util.Set;

import org.artofsolving.jodconverter.office.ExternalOfficeManagerTest;
import org.artofsolving.jodconverter.office.OfficeUtilsTest;
import org.artofsolving.jodconverter.office.PooledOfficeManagerTest;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

public class TestsExecutor {

    public static void main(String[] args) {
        TestNG testng = new TestNG();

        TestListenerAdapter tla = new TestListenerAdapter();
        testng.addListener(tla);

        Set<Class<?>> testClasses = new HashSet<Class<?>>();

        testClasses.add(OfficeDocumentConverterFunctionalTest.class);
        testClasses.add(ExternalOfficeManagerTest.class);
        testClasses.add(OfficeUtilsTest.class);
        testClasses.add(PooledOfficeManagerTest.class);

        testng.setTestClasses(testClasses.toArray(new Class[testClasses.size()]));
        testng.run();
    }
}
