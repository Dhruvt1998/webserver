package com.example.hms;

import com.example.hms.Model.Krankenhaus;
import com.example.hms.repository.KrankenhausRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputFilter;

import static com.example.hms.Config.readConfigFile;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class HmsApplication {


	public static void main(String[] args) {
		//SpringApplication.run(HmsApplication.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(HmsApplication.class, args);
		KrankenhausRepository krankenhausRepository = context.getBean(KrankenhausRepository.class);
		Krankenhaus krankenhaus = Krankenhaus.getInstance();

		Config config = readConfigFile();


		krankenhaus.setId(config.getID());
		krankenhaus.setName(config.getName());
		krankenhaus.setTotalBeds(config.getTotalBeds());
		krankenhaus.setAvailableBeds(config.getTotalBeds());

		System.out.println("Krankenhausname: " + krankenhaus.getName());
		System.out.println("KrankenhausID: " + krankenhaus.getId());
		System.out.println("Gesamtbetten: " + krankenhaus.getTotalBeds());
		System.out.println("Verfügbare Betten: " + krankenhaus.getAvailableBeds());
		krankenhausRepository.save(krankenhaus);

	}

}