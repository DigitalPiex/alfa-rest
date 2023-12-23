package com.example.restalfabank.service.parser;

public class GetArgument {

    public static String getArgument(String[] args) {
        for (String arg : args) {
            if (arg.contains("--classpath=") ||
                    arg.contains("--file=") ||
                    arg.contains("--url=")
            ) {
                return arg;
            }
        }
        return "";
    }

}
