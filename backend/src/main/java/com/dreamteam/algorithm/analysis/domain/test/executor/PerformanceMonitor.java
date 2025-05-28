package com.dreamteam.algorithm.analysis.domain.test.executor;

import com.sun.management.ThreadMXBean;

import java.lang.management.ManagementFactory;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public final class PerformanceMonitor {
    private static final ThreadMXBean THREAD_MX_BEAN = (ThreadMXBean) ManagementFactory.getThreadMXBean();

    static <T> T measureExecution(Callable<T> action, Consumer<Long> timeConsumer, Consumer<Long> memoryConsumer) throws Exception {
        long start = System.nanoTime();
        long beforeMemory = getThreadAllocatedBytes();
        T result = action.call();
        long afterMemory = getThreadAllocatedBytes();
        timeConsumer.accept(System.nanoTime() - start);
        memoryConsumer.accept(afterMemory - beforeMemory);
        return result;
    }

    static long getThreadAllocatedBytes() {
        return THREAD_MX_BEAN.getThreadAllocatedBytes(Thread.currentThread().threadId());
    }
}
