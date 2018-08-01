package org.zstack.test.core.retry;

import junit.framework.Assert;
import org.junit.Test;
import org.zstack.core.retry.Retry;
import org.zstack.core.retry.RetryCondition;
import org.zstack.testlib.SubCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestRetry extends SubCase {
    int successAfter = 3;

    private String sayHello() {
        if (successAfter == 0) {
            return "hello";
        } else {
            successAfter--;
            throw new RuntimeException("on purpose");
        }
    }

    @Override
    public void setup() {

    }

    @Override
    public void environment() {

    }

    @Test
    public void test() {
        String ret = (String) new Retry() {

            {
                __name__ = "test";
            }

            @Override
            protected Object call() {
                return sayHello();
            }
        }.run();

        Assert.assertEquals("hello", ret);

        successAfter = 3;
        boolean s = false;
        try {
            ret = (String) new Retry() {
                @Override
                @RetryCondition(times = 1)
                protected Object call() {
                    return sayHello();
                }
            }.run();
        } catch (RuntimeException e) {
            s = true;
        }
        Assert.assertTrue(s);

        successAfter = 3;
        s = false;
        try {
            ret = (String) new Retry() {
                @Override
                @RetryCondition(times = 1, onExceptions = {IOException.class})
                protected Object call() {
                    return sayHello();
                }
            }.run();
        } catch (RuntimeException e) {
            s = true;
        }
        Assert.assertTrue(s);

        successAfter = 3;
        ret = (String) new Retry() {
            @Override
            @RetryCondition(interval = 2)
            protected Object call() {
                return sayHello();
            }
        }.run();

        Assert.assertEquals("hello", ret);

        List<Integer> ctimes = new ArrayList();
        try {
            successAfter = 3;
            Retry testStopRetry = new Retry() {
                @Override
                @RetryCondition()
                protected Object call() {
                    ctimes.add(1);
                    stop();
                    return sayHello();
                }
            };
            testStopRetry.run();
            assert false;
        }catch (Exception e){
            assert 1 == ctimes.size();
            assert true;
        }

    }

    @Override
    public void clean() {

    }
}
