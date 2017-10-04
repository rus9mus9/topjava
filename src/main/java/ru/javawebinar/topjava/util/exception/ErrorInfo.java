package ru.javawebinar.topjava.util.exception;


import org.postgresql.util.PSQLException;
import org.springframework.validation.BindException;

import java.util.StringJoiner;

public class ErrorInfo {
    private final String url;
    private final String cause;
    private final String detail;

    public ErrorInfo(CharSequence url, Throwable ex) {
        this.url = url.toString();
        this.cause = ex.getClass().getSimpleName();
        if(ex instanceof BindException)
        {
            StringJoiner joiner = new StringJoiner("<br>");
            ((BindException) ex).getFieldErrors().forEach(
                    error ->
                    {
                        String msg = error.getDefaultMessage();
                        if(!msg.startsWith(error.getField()))
                        {
                            msg = error.getField() + ' ' + msg;
                        }
                        joiner.add(msg);
                    });
            this.detail = joiner.toString();
        }
        else if (ex instanceof PSQLException)
        {
            this.detail = "User with this email already exists";
        }
        else
        this.detail = ex.getLocalizedMessage();
    }
}