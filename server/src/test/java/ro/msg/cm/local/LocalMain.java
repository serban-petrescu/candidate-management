package ro.msg.cm.local;

import org.springframework.boot.SpringApplication;
import ro.msg.cm.Main;

public class LocalMain {

	public static void main(String[] args) {
		System.setProperty("spring.profiles.active", "local");
		SpringApplication.run(Main.class, args);
	}
}
