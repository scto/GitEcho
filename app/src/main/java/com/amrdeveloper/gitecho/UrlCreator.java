package com.amrdeveloper.gitecho;

public class UrlCreator {

    public static String userUrl(String username){
        return "https://api.github.com/users/".concat(username);
    }

}
