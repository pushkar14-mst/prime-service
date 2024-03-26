package edu.iu.pupatil.primeservice.repository;

import edu.iu.pupatil.primeservice.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Repository
public class AuthenticationFileRepository implements IAuthenticationRepository {
    private static final Logger LOG= LoggerFactory.getLogger(AuthenticationFileRepository.class);
    private static final String DATABASE_NAME="data/customers.txt";
    private static final String NEW_Line=System.lineSeparator();
    public AuthenticationFileRepository(){
        File file=new File(DATABASE_NAME);
        file.getParentFile().mkdir();
        try{
            file.createNewFile();
        }catch (IOException e){
            LOG.error(e.getMessage());
        }
    }
    @Override
    public Customer findByUsername(String username) throws IOException{
        Path path=Paths.get(DATABASE_NAME);
        List<String> data= Files.readAllLines((path));
        for(String line:data){
            if(!line.trim().isEmpty()){
                String[] properties=line.split(",");
                if(properties[0].trim().equalsIgnoreCase(username.trim())){
                    return new Customer(properties[0].trim(),properties[1].trim());
                }
            }
        }
        return null;
    }
    @Override
    public boolean save(Customer customer) throws IOException{
        Customer cus=findByUsername(customer.getUsername());
        if(cus==null){
            Path path= Paths.get(DATABASE_NAME);
            String data=String.format("%1$s,%2$s",customer.getUsername().trim(),customer.getPassword().trim());
            data+=NEW_Line;
            Files.write(path,data.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,StandardOpenOption.APPEND);
            return true;
        }
        return false;
    }
}
