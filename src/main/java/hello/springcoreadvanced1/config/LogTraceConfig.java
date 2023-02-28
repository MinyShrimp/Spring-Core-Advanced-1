package hello.springcoreadvanced1.config;

import hello.springcoreadvanced1.trace.logtrace.FieldLogTrace;
import hello.springcoreadvanced1.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
        return new FieldLogTrace();
    }
}