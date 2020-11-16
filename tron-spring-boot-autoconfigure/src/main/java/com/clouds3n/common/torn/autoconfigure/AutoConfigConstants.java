package com.clouds3n.common.torn.autoconfigure;

/**
 * @author CloudS3n
 * @date 2020-06-25 16:38
 */
public class AutoConfigConstants {
    public static final String SUCCESS_OPERATE = "操作成功";
    public static final String ERROR_OPERATE = "操作异常";
    public static final String SERVER_ERROR = "服务端错误";
    public static final String NO_AUTHORIZATION = "没有权限";
    public static final String HEX_FORMAT = "%02x";
    public static final String HEX_0X_FORMAT = "0x%04X";
    private static final String AUTO_CONFIG_PREFIX = "[ AUTO-CONFIGURE ] ";
    public static final String LOADING_THREAD_POOL_AUTO_CONFIGURE = AUTO_CONFIG_PREFIX + "Loading thread pool auto configure...";
    public static final String LOADING_DRUID_MONITOR_AUTO_CONFIGURE = AUTO_CONFIG_PREFIX + "Loading druid monitor auto configure...";
    public static final String LOADING_DRUID_MONITOR_SERVLET = AUTO_CONFIG_PREFIX + "Loading druid monitor ServletRegistrationBean...";
    public static final String LOADING_DRUID_MONITOR_FILTER = AUTO_CONFIG_PREFIX + "Loading druid monitor FilterRegistrationBean...";
    public static final String LOADING_GLOBAL_EXCEPTION_AUTO_CONFIGURE = AUTO_CONFIG_PREFIX + "Loading global exception auto configure...";
    public static final String LOADING_STARTER_AUTO_CONFIGURE = AUTO_CONFIG_PREFIX + "Loading starter auto configure...";
    public static final String LOADING_MYBATIS_PLUS_AUTO_CONFIGURE = AUTO_CONFIG_PREFIX + "Loading mybatis-plus auto configure...";
    public static final String LOADING_TRANSACTION_AUTO_CONFIGURE = AUTO_CONFIG_PREFIX + "Loading transaction auto configure...";
    public static final String LOADING_SECURITY_AUTO_CONFIGURE = AUTO_CONFIG_PREFIX + "Loading security auto configure...";
    public static final String LOADING_ES_AUTO_CONFIGURE = AUTO_CONFIG_PREFIX + "Loading elastic search auto configure...";
    public static final String LOADING_ES_CLIENT = AUTO_CONFIG_PREFIX + "Loading elastic high level client...";
    public static final String LOADING_ES_SNIFF = AUTO_CONFIG_PREFIX + "Loading elastic sniff...";
    public static final String LOADING_SECURITY_CORS = AUTO_CONFIG_PREFIX + "Loading security cors...";
    public static final String LOADING_TRANSACTION_MANAGER = AUTO_CONFIG_PREFIX + "Loading platform transaction manager...";
    public static final String MYBATIS_PLUS_MAPPERS = AUTO_CONFIG_PREFIX + "Loading mybatis xml mappers...\n{}";
    public static final String LOADING_SCRIPT_RUNNER_AUTO_CONFIGURE = AUTO_CONFIG_PREFIX + "Loading script writer auto configure...";
    public static final String LOADING_MONITOR_AUTO_CONFIGURE = AUTO_CONFIG_PREFIX + "Loading monitor auto configure...";
    public static final String LOADING_THREAD_POOL_TASK_EXECUTOR = AUTO_CONFIG_PREFIX + "Loading ThreadPoolTaskExecutor...";
    public static final String LOADING_THREAD_POOL_TASK_SCHEDULER = AUTO_CONFIG_PREFIX + "Loading ThreadPoolTaskScheduler...";
}
