package com.github.yoshaul.internal;

import java.util.Properties;
import java.util.TreeMap;

/**
 * @author Yossi Shaul
 */
public class PrintSystemEnv {

    public static void main(String[] args) {
        Properties props = System.getProperties();
        TreeMap<String, String> sortedProps = new TreeMap<>();
        props.forEach((k, v) -> sortedProps.put((String) k, (String) v));
        System.out.println("Java System Properties:\n");
        sortedProps.forEach((k, v) -> System.out.println(k + ": " + v));

        TreeMap<String, String> sortedEnv = new TreeMap<>(System.getenv());
        System.out.println("\nSystem Environment:\n");
        sortedEnv.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
