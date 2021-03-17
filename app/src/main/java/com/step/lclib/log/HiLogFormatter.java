package com.step.lclib.log;

public interface HiLogFormatter<T> {

    String format(T data);
}