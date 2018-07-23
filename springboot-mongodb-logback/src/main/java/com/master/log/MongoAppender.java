package com.master.log;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.mongodb.BasicDBObject;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.event.LoggingEvent;
import org.springframework.data.mongodb.core.MongoTemplate;


/**
 * 日志插入MongoDB
 * @author tuonioooo
 * @version 1.0.0
 * @date 16/5/23 下午4:53.
 * @blog https://blog.csdn.net/tuoni123
 */
@Setter
@Getter
public class MongoAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    @Override
    protected void append(ILoggingEvent eventObject) {
        MongoTemplate mongoTemplate = SpringContextUtil.getBean(MongoTemplate.class);
        if (mongoTemplate != null) {
//            final BasicDBObject doc = new BasicDBObject();
//            doc.append("level", eventObject.getLevel().toString());
//            doc.append("logger", eventObject.getLoggerName());
//            doc.append("thread", eventObject.getThreadName());
//            doc.append("message", eventObject.getFormattedMessage());
//            mongoTemplate.insert(()eventObject.getMessage(), "logback");
              mongoTemplate.insert(eventObject.getMessage(), "logback");
        }

    }
}
