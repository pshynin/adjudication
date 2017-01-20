package com.agmednet.testrail.client;

/**
 * Created by pshynin on 1/18/17.
 */
public class ClientException extends Exception
{
    public ClientException(String message)
    {
        super(message);
    }

    public ClientException(String message, Exception ex)
    {
        super(message, ex);
    }
}