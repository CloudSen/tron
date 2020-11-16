package com.clouds3n.common.torn.autoconfigure.script;

import com.clouds3n.common.torn.autoconfigure.AutoConfigConstants;
import com.clouds3n.common.torn.autoconfigure.system.StarterAutoConfiguration;
import com.clouds3n.common.torn.autoconfigure.system.properties.StarterProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author CloudS3n
 * @date 2019/5/18 13:51
 */
@Slf4j
@Order(1)
@Configuration
@ConditionalOnProperty(name = "config.starter.enable-script", havingValue = "true")
@AutoConfigureAfter(StarterAutoConfiguration.class)
public class ApplicationScriptsWriterAutoConfiguration implements ApplicationRunner {

    static {
        log.info(AutoConfigConstants.LOADING_SCRIPT_RUNNER_AUTO_CONFIGURE);
    }

    @Value("${spring.application.name}")
    private String applicationName;
    private final StarterProperties starterProperties;

    public ApplicationScriptsWriterAutoConfiguration(StarterProperties starterProperties) {
        this.starterProperties = starterProperties;
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            if (!starterProperties.getEnableScript()) {
                log.info("[ WRITER ] 不创建脚本文件...");
                return;
            }
            Assert.notNull(applicationName, "[ WRITER ] Application Name不能为空，请检查配置文件");
            log.info("[ WRITER ] 正在创建项目脚本文件...");
            this.writeScriptFiles();
        } catch (Exception e) {
            log.error("[ WRITER ] !\n{}", ExceptionUtils.getStackTrace(e));
        }

    }

    private void writeScriptFiles() throws IOException {
        Path runPath = new File("run.sh").toPath();
        Path stopPath = new File("stop.sh").toPath();
        Path runAndWatchPath = new File("runAndWatch.sh").toPath();
        if (Files.exists(runPath)) {
            Files.delete(runPath);
        }
        if (Files.exists(stopPath)) {
            Files.delete(stopPath);
        }
        if (Files.exists(runAndWatchPath)) {
            Files.delete(runAndWatchPath);
        }
        Files.createFile(runPath);
        Files.createFile(stopPath);
        Files.createFile(runAndWatchPath);
        Files.write(runPath, runShellBuilder().toString().getBytes(StandardCharsets.UTF_8));
        Files.write(stopPath, stopShellBuilder().toString().getBytes(StandardCharsets.UTF_8));
        Files.write(runAndWatchPath, runAndWatchShellBuilder().toString().getBytes(StandardCharsets.UTF_8));
        log.info("[ WRITER ] run.sh 创建成功，路径：{}", runPath.toRealPath());
        log.info("[ WRITER ] stop.sh 创建成功，路径：{}", stopPath.toRealPath());
        log.info("[ WRITER ] runAndWatch.sh 创建成功，路径：{}", runAndWatchPath.toRealPath());
    }

    private StringBuilder runShellBuilder() {
        return new StringBuilder().append("#!/bin/bash").append("\n")
            .append("# Author: CloudS3n").append("\n")
            .append("\n")
            .append("projectName='").append(applicationName).append("'\n")
            .append("clear").append("\n")
            .append("echo \"==> Starting ${projectName}...\"").append("\n")
            .append("if [[ ! -d \"/home/5s/web/${projectName}/logs/info/\" ]]; then").append("\n")
            .append("\t").append("mkdir -p \"/home/5s/web/${projectName}/logs/info/\"").append("\n")
            .append("fi").append("\n")
            .append("java -jar \"/home/5s/web/${projectName}/service/${projectName##*/}.jar\" --spring.profiles.active=prod &>/dev/null &").append("\n")
            .append("echo \"==> Logs all at /home/5s/web/${projectName}/logs/\"").append("\n");
    }

    private StringBuilder runAndWatchShellBuilder() {
        return runShellBuilder()
            .append("sleep 2").append("\n")
            .append("echo \"Reading log \"`ls -t \"/home/5s/web/${projectName}/logs/info/\" | head -1`").append("\n")
            .append("watch tail -n 30 \"/home/5s/web/${projectName}/logs/info/\"`ls -t \"/home/5s/web/${projectName}/logs/info/\" | head -1`").append("\n");
    }

    private StringBuilder stopShellBuilder() {
        return new StringBuilder().append("#!/bin/bash").append("\n")
            .append("# Author: CloudS3n").append("\n")
            .append("\n")
            .append("projectName='").append(applicationName).append("'\n")
            .append("pidFilePath=\"/home/5s/web/${projectName}/service/${projectName##*/}-pid\"").append("\n")
            .append("clear").append("\n")
            .append("echo \"==> Stopping ${projectName}...\"").append("\n")
            .append("if [[ ! -f ${pidFilePath} ]]; then").append("\n")
            .append("\t").append("echo \"==> no need to stop\"").append("\n")
            .append("\t").append("exit").append("\n")
            .append("fi").append("\n")
            .append("pid=\"$(cat ${pidFilePath})\"").append("\n")
            .append("if [[ -z $pid ]]; then").append("\n")
            .append("\t").append("echo \"==> no need to stop\"").append("\n")
            .append("\t").append("exit").append("\n")
            .append("fi").append("\n")
            .append("echo \"==> ${projectName} pid=$pid\"").append("\n")
            .append("$(kill -9 \"$pid\")").append("\n")
            .append("rm -f ${pidFilePath}").append("\n")
            .append("echo \"==> Success to stop ${projectName}\"");
    }
}
