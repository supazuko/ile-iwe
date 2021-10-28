package com.ileiwe;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
class IleIweApplicationTests {

	@Autowired
	DataSource dataSource;

	@Test
	void contextLoads() {
		assertThat(dataSource).isNotNull();
		log.info("Database properties ->{}", dataSource);

		Connection connection = null;
		try {
			connection = dataSource.getConnection();
			assertThat(connection).isNotNull();
			log.info("Database connection ->{}", connection.getCatalog());
		} catch (SQLException exception) {
			log.info("An exception occurred -> {}", exception.getMessage());
		}
	}

}
