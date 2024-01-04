package com.example.hms.Middleware.ApplicationStub;

import com.example.hms.Middleware.ClientStub.ClientStub;
import com.example.hms.Middleware.ClientStub.IClientStub;
import com.example.hms.Model.IModelKrankenhaus;
import com.example.hms.Model.Krankenhaus;
import com.example.hms.repository.KrankenhausSystemRepository;
import org.aspectj.apache.bcel.classfile.Method;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class ApplicationStubCaller {

    private final KrankenhausSystemRepository krankenhausSystemRepository;
    private IClientStub clientStub;

    public ApplicationStubCaller(KrankenhausSystemRepository krankenhausSystemRepository) {
        this.krankenhausSystemRepository = krankenhausSystemRepository;
    }

    //Autowire with DB
                       //id,("name", "ABeds", "TBeds", ...)


    //in DB gucken, welche Krankenhäuser gibt es bzw. welche id, ports, hosts gibt es.
    public List<HospitalInfo> lookUP(){
        return krankenhausSystemRepository.findAll();
    }

    public void test() throws IOException, NoSuchMethodException {
        List<HospitalInfo> hospitalInfos = lookUP();
        for (HospitalInfo hospitalInfo : hospitalInfos){
            clientStub = new ClientStub(hospitalInfo.getHost().getHostName(),hospitalInfo.getPort());  //getHostName return a String
            String response = clientStub.invoke(Krankenhaus.getInstance().getClass().getMethod("getAvailableBeds"));
            //TODO: JSON Object bearbeiten
            hospitalInfo.setAvailableBeds(Integer.parseInt(response));
        }
    }




    //1-der Leader meldet sich beim neuen Knoten


    //Für jedes Krankenhaus ein ClientSocket erstellen


    //Response vom Server in DB speichern

}
