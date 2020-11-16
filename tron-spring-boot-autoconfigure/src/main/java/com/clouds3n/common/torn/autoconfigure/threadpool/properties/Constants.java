package com.clouds3n.common.torn.autoconfigure.threadpool.properties;

/**
 * @author CloudS3n
 * @date 2020-06-25 15:58
 */
class Constants {
    private static final String TASK_EXECUTOR_PREFIX = "[ TASK-EXECUTOR ] ";
    static final String THREAD_NAME_PREFIX_INVALID = TASK_EXECUTOR_PREFIX + "you must specify thread name prefix";
    static final String CORE_POLL_SIZE_INVALID = TASK_EXECUTOR_PREFIX + "core pool size must greater or equal to 10";
    static final String MAX_POLL_SIZE_INVALID = TASK_EXECUTOR_PREFIX + "max pool size must greater or equal to 50";
    static final String QUEUE_SIZE_INVALID = TASK_EXECUTOR_PREFIX + "queue capacity size must greater or equal to 30";
    static final String TIME_OUT_INVALID = TASK_EXECUTOR_PREFIX + "you must specify whether to allow core thread timeout";
    static final String KEEP_ALIVE_INVALID = TASK_EXECUTOR_PREFIX + "you must specify keep alive seconds";
    private static final String TASK_SCHEDULER_PREFIX = "[ TASK-SCHEDULER ] ";
    static final String SCHEDULER_THREAD_NAME_PREFIX_INVALID = TASK_SCHEDULER_PREFIX + "you must specify thread name prefix";
    static final String POOL_SIZE_INVALID = TASK_SCHEDULER_PREFIX + "pool size must greater or equals to 20";
}
