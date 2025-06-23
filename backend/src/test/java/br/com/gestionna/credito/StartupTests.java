package br.com.gestionna.credito;

import br.com.gestionna.credito.dto.LogDTO;
import br.com.gestionna.credito.repository.CreditoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = StartupTests.TestConfig.class)
@TestPropertySource(locations = "classpath:application.properties")
class StartupTests {

	@Test
	void contextLoads() {
	}

	@Configuration
	@EnableAutoConfiguration(exclude = {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class
	})
	static class TestConfig {
		@Bean
		public CreditoRepository creditoRepository() {
			return Mockito.mock(CreditoRepository.class);
		}

		@Bean
		public KafkaTemplate<String, LogDTO> kafkaTemplate() {
			return Mockito.mock(KafkaTemplate.class);
		}
	}
}
