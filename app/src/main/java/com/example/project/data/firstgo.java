package com.example.project.data;

public class firstgo {
    public static int runsplash = 0;
    public static boolean runapp(){
        if (runsplash == 0){
            runsplash = 1;
            return true;
        }

        return false;
    }
}
