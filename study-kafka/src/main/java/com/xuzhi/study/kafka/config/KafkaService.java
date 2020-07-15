package com.xuzhi.study.kafka.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.function.Consumer;

@Component
public class KafkaService {

  protected Logger logging = LoggerFactory.getLogger(KafkaService.class);

  @Autowired
  private KafkaTemplate kafkaTemplate;

  /**
   * Kafka生产者
   */
  public void send(String msg, String topic) {
    ListenableFuture future = kafkaTemplate.send(topic, msg);
    future.addCallback(d -> logging.info("send-消息发送成功: " + msg),
      throwable -> logging.info("send-消息发送失败: " + msg));
  }

  public void send(String msg, String topic, Consumer<String> successCallback, Consumer<String> errorCallback) {
    ListenableFuture future = kafkaTemplate.send(topic, msg);
    future.addCallback(d ->  successCallback.accept(msg),
      throwable -> errorCallback.accept(msg));
  }
}
