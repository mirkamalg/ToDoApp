package com.mirkamal.todoassignmentapp.utils.lib;

public interface ResponseCallBack {

    default void handleTheResponseOfSuccessfulPost() {};

    default void handleTheResponseOfFailedPost() {};

    default void handleTheResponseOfFailedGet() {};

    default void handleTheResponseOfSuccessfulGet() {};

}
