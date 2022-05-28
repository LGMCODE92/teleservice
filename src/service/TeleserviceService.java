package service;
import domain.Person;
import repository.PersonRepository;
import java.sql.Connection;
import java.sql.SQLException;
import config.Connect;
public class TeleserviceService {
    private PersonRepository personRepository;
    public  TeleserviceService (){
        personRepository = new PersonRepository();
    }
    public Person login ( Person user){
        Connection conn = Connect.connect();
        Person personResponse = null;
        boolean isLogin = false;
        
        try{
            //personRepository.dropTable(conn);
            //personRepository.createTable(conn);
            //personRepository.insert(person,conn);

            personResponse = personRepository.findPerson(user, conn);
            isLogin = (null != personResponse 
            && personResponse.getUserName().equals(user.getUserName())
            && personResponse.getPassword().equals(user.getPassword()));
            conn.close();
            
        }catch(SQLException e){
           System.out.println(e.getMessage());
        } finally {
            personResponse.setError(isLogin ? null : "Usuario o contraseña invalido");
            return personResponse;
        }

    }
}
