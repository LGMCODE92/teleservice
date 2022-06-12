package service;
import domain.CallLog;
import domain.Medicament;
import domain.Person;
import repository.CallLogRepository;
import repository.MedicamentRepository;
import repository.PersonRepository;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import config.Connect;
public class TeleserviceService {
	
    private PersonRepository personRepository;
    private MedicamentRepository medicamentRepository;
    private CallLogRepository callLogRepository;
    private Person superUser = new Person();
    
    public  TeleserviceService (){
        personRepository = new PersonRepository();
        medicamentRepository = new MedicamentRepository();
        callLogRepository= new CallLogRepository();
    }
    
    public Person login ( Person user){
    	
        Connection conn = Connect.connect();
        Person personResponse = null;
        boolean isLogin = false;
        Medicament medicament= new Medicament();
        CallLog callLog = new CallLog();
        
        superUser.setOperator("paquito");
        superUser.setPassword("12345");
        superUser.setDocument("12345678g");
        superUser.setTf("123456789");
        superUser.setAddress("asd");
        
        medicament.setName("Ibuprofeno");
        medicament.setAmount(2);
        medicament.setBaseMedicine("Ibu");
        medicament.setDiaryIngest("34");
        medicament.setUserDocument("12345678g");

        callLog.setDate(new Date());
        callLog.setOperator(superUser.getOperator());
        callLog.setDocument(superUser.getDocument());
        callLog.setContactPerson("pepito");
        callLog.setCallReason("CallReason");
        
       
        
        try{
        	try {
        		
        		callLogRepository.createTable(conn);
        		callLogRepository.insert(callLog, conn);
        		callLogRepository.delete(superUser, conn);
        		
            personRepository.dropTable(conn);
            personRepository.createTable(conn);
            personRepository.insert(superUser,conn);
            personRepository.update(superUser, conn);
//            personRepository.delete(superUser, conn);
            
            medicamentRepository.dropTable(conn);
    		medicamentRepository.createTable(conn);
    		medicamentRepository.insert(medicament, conn);
    		medicamentRepository.update(medicament, conn);
    		medicamentRepository.delete(superUser, conn);
    		
        	}catch (SQLException e){
        		System.out.println("error: ");
        		e.printStackTrace();
        	}finally {
        		
            try {
				personResponse = personRepository.findOperator(user, conn);
				System.out.println(personResponse);
			} catch (Exception e) {
				System.out.println("Error linea 49");
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
