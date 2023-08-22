package com.suber.provider.service;

import com.suber.provider.util.MyException;

/**
 * @author suber
 * 2023/8/12 17:34
 */
public interface ErrorService {
    public String paymentTimeout(Integer timeout);

    public String paymentFail();

    public String paymentFailMust() throws MyException;
}
