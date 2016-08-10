package own.ryze.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RyzeApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(RyzeApplication.class, args);
	}
}
