package com.example.hms;

import com.example.hms.Middleware.ApplicationStub.ApplicationStubCallee;
import com.example.hms.Middleware.NamingService.AddressInfo;
import com.example.hms.Middleware.NamingService.INamingService;
import com.example.hms.Middleware.NamingService.NamingService;
import com.example.hms.Middleware.ServerStub.IServerStub;
import com.example.hms.Middleware.ServerStub.ServerStub;
import com.example.hms.Model.Krankenhaus;
import com.example.hms.Service.KrankenhausService;
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


	public static void main(String[] args){
		//SpringApplication.run(HmsApplication.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(HmsApplication.class, args);
		KrankenhausRepository krankenhausRepository = context.getBean(KrankenhausRepository.class);
		Krankenhaus krankenhaus = Krankenhaus.getInstance();
		IServerStub serverStub = context.getBean(IServerStub.class);

		Config config = readConfigFile();

		serverStub.register();

//		INamingService namingService = new NamingService();
//		AddressInfo addressInfo = new AddressInfo("localhost", config.getPort());
//		namingService.register();
		krankenhaus.setPort(config.getPort());
		krankenhaus.setIpAddress(config.getIpAddress());
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