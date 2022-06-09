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
        user.setOperator("paquito");
        user.setPassword("123");
        user.setDocument("12345678g");
        user.setTf("123456789");
        
        
        try{
        	try {
            personRepository.dropTable(conn);
            personRepository.createTable(conn);
            personRepository.insert(user,conn);
        	}catch (SQLException e){
        		System.out.println("error: ");
        		e.printStackTrace();
        	}finally {
        		
            try {
				personResponse = personRepository.findOperator(user, conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            isLogin = (null != personResponse 
            && personResponse.getOperator().equals(user.getOperator())
            && personResponse.getPassword().equals(user.getPassword()));
            conn.close();
        	}
        }catch(SQLException e){
           System.out.println(e.getMessage());
        } finally {
            personResponse.setError(isLogin ? null : "Usuario o contraseña invalido");
            return personResponse;
        }

    }
}
