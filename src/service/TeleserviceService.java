package service;
import domain.CallLog;
import domain.Configuration;
import domain.ConfigurationEnum;
import domain.Medicament;
import domain.Person;
import repository.CallLogRepository;
import repository.ConfigurationRepository;
import repository.MedicamentRepository;
import repository.PersonRepository;
import java.sql.Connection;
import java.sql.SQLException;

import config.Connect;
public class TeleserviceService {
	
    private PersonRepository personRepository;
    private MedicamentRepository medicamentRepository;
    private CallLogRepository callLogRepository;
    private ConfigurationRepository configurationRepository;
    private Person superUser = new Person();
    
    public  TeleserviceService (){
        personRepository = new PersonRepository();
        medicamentRepository = new MedicamentRepository();
        callLogRepository= new CallLogRepository();
        configurationRepository = new ConfigurationRepository();
    }
    
    public Person login ( Person user){
    	
        Connection conn = Connect.connect();
        Person personResponse = null;
        boolean isLogin = false;
        Medicament medicament= new Medicament();
        CallLog callLog = new CallLog();
        CallLog callLog2 = new CallLog();
        Person contract = new Person();
        Configuration config1h = new Configuration();
        config1h.setKey(ConfigurationEnum.SEX.name());
        config1h.setValue("H");
        Configuration config1M = new Configuration();
        config1M.setKey(ConfigurationEnum.SEX.name());
        config1M.setValue("M");

        Configuration config31 = new Configuration();
        config31.setKey(ConfigurationEnum.INGEST_NUMBER.name());
        config31.setValue("1");
        Configuration config32 = new Configuration();
        config32.setKey(ConfigurationEnum.INGEST_NUMBER.name());
        config32.setValue("2");

        Configuration config2S = new Configuration();
        config2S.setKey(ConfigurationEnum.HELP_HOME.name());
        config2S.setValue("Si");
        Configuration config2N = new Configuration();
        config2N.setKey(ConfigurationEnum.HELP_HOME.name());
        config2N.setValue("No");
        

        
        Configuration config4S = new Configuration();
        config4S.setKey(ConfigurationEnum.CIVIL_STATUS.name());
        config4S.setValue("Soltero/a");
        Configuration config4C = new Configuration();
        config4C.setKey(ConfigurationEnum.CIVIL_STATUS.name());
        config4C.setValue("Casado/a");
        Configuration config4D = new Configuration();
        config4D.setKey(ConfigurationEnum.CIVIL_STATUS.name());
        config4D.setValue("Divorciado/a");
        Configuration config4V = new Configuration();
        config4V.setKey(ConfigurationEnum.CIVIL_STATUS.name());
        config4V.setValue("Viudo/a");
        


        
        superUser.setOperator("paquito");
        superUser.setUserName("Francisco");
        superUser.setUserSurname("De la Rosa Calvo");
        superUser.setPassword("12345");
        superUser.setDocument("12345678G");
        superUser.setTf("123456789");
        superUser.setAddress("asd");
        superUser.setTypeUser("S");
        
        contract.setUserRef("12345678G");
        contract.setDocument("12345678F");
        contract.setTf("123123123");
        contract.setUserName("Perejildo");
        contract.setUserSurname("Muerde LLantas");
        
        medicament.setName("Ibuprofeno");
     //   medicament.setAmount(2);
        medicament.setBaseMedicine("Ibu");
        medicament.setDiaryIngest("34");
        medicament.setUserDocument("12345678G");

        callLog.setOperator(superUser.getOperator());
        callLog.setDocument(superUser.getDocument());
        callLog.setContactPerson("pepito");
        callLog.setCallReason("CallReason");
        callLog2.setOperator(superUser.getOperator()+2);
        callLog2.setDocument(superUser.getDocument());
        callLog2.setContactPerson("pepito2");
        callLog2.setCallReason("CallReason2");
        System.out.println(callLog);
//       try {
//    	   for(int i =1; i<5;i++)
//		System.out.println((callLogRepository.findCalls(superUser, conn)).get(i).toString());
//	} catch (SQLException e1) {
//		// TODO Auto-generated catch block
//		e1.printStackTrace();
//	}
        
        try{
        	try {
//        		configurationRepository.dropTable(conn);
//        		configurationRepository.createTable(conn);
//                configurationRepository.insert(config1h, conn);
//                configurationRepository.insert(config1M, conn);
//                configurationRepository.insert(config31, conn);
//                configurationRepository.insert(config32, conn);
//                configurationRepository.insert(config2S, conn);
//                configurationRepository.insert(config2N, conn);
//                configurationRepository.insert(config4S, conn);
//                configurationRepository.insert(config4C, conn);
//                configurationRepository.insert(config4D, conn);
//                configurationRepository.insert(config4V, conn);
//        		
//        		
//        		callLogRepository.dropTable(conn);
//        		callLogRepository.createTable(conn);
//        		callLogRepository.insert(callLog, conn);
//        		callLogRepository.insert(callLog2, conn);
//        		//callLogRepository.delete(superUser, conn);
//        		
//            personRepository.dropTable(conn);
//            personRepository.createTable(conn);
//            personRepository.insert(superUser,conn);
//            personRepository.update(superUser, conn);
//            personRepository.insert(contract,conn);
// //           personRepository.update(contract, conn);
////            personRepository.delete(superUser, conn);
//            
//            medicamentRepository.dropTable(conn);
//    		medicamentRepository.createTable(conn);
//    		medicamentRepository.insert(medicament, conn);
//    		medicamentRepository.update(medicament, conn);
//    		medicamentRepository.delete(superUser, conn);
//    		
//        	}catch (SQLException e){
//        		System.out.println("error: ");
//        		e.printStackTrace();
        	}
        	finally {
        		
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
