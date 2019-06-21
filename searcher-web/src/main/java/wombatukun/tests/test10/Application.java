package wombatukun.tests.test10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableCaching
@EnableHystrixDashboard
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
