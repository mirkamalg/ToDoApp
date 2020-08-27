package com.mirkamal.todoassignmentapp.utils.lib;

public interface ResponseCallBack {

    void handleTheResponseOfSuccessfulPost();

    void handleTheResponseOfFailedPost();

    void handleTheResponseOfFailedGet();

    void handleTheResponseOfSuccessfulGet();

}
