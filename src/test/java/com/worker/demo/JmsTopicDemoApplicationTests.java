package com.worker.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import com.worker.demo.jms.Receiver;
import com.worker.demo.jms.Sender;

@SpringBootTest
class JmsTopicDemoApplicationTests {

	@Autowired
	  private Sender sender;

	  @Autowired
	  private Receiver receiver;
	@Test
	void contextLoads() throws Exception {
		sender.send("order-001");

	    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
	    assertThat(receiver.getLatch().getCount()).isEqualTo(0);
	}

}
