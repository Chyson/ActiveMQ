package cn.ashin.mqtest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.ashin.mq.MQProducer;
import cn.ashin.mq.params.MailParam;

/**
 * @author Chyson
 * @version 2016年11月15日 下午2:28:15 类说明
 */
public class MQProducerTest {
	private static final Log log = LogFactory.getLog(MQProducerTest.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
					"classpath:spring/spring-context.xml");
			context.start();

			MQProducer mqProducer = (MQProducer) context.getBean("mqProducer");
			for (int i = 0; i < 5; i++) {
				// 邮件发送，收件人的邮箱
				MailParam mail = new MailParam();
				mail.setTo("chysonli@126.com");
				mail.setSubject("ActiveMQ测试--->" + i);
				mail.setContent("通过ActiveMQ发送的邮件。");
				mqProducer.sendMessage(mail);
			}
			context.stop();
		} catch (Exception e) {
			log.error("--->MQ context start error:", e);
			System.exit(0);
		} finally {
			log.info("--->System.exit");
			System.exit(0);
		}
	}
}
