package controller;
import service.PersonService;
import domain.Person;
public class PersonController {
	
    private PersonService personService;

    public PersonController(){
    	personService = new PersonService();
    }
    public Person getPerson ( Person person){
        Person response = null;
        try{
        	response = personService.getPerson(person);

        }catch (Exception e){
        	e.printStackTrace();
        	System.out.println("ErrorPersonControler");
        	response = new Person();
            response.setError(e.getMessage());
        }
        return response;
    }
    
    /**
     * savePerson
     * @param person person
     * @return String with status response
     */
    public String savePerson ( Person person){
    	String response = "OK";
        try{
        	personService.savePerson(person);

        }catch (Exception e){
        	e.printStackTrace();
        	System.out.println("ErrorPersonControlerSave");
        	response = e.getMessage();
        }
        return response;
    }
    
    /**
     * updatePerson
     * @param person person
     * @return String with status response
     */
    public String updatePerson ( Person person){
    	String response = "OK";
        try{
        	personService.savePerson(person);

        }catch (Exception e){
        	e.printStackTrace();
        	System.out.println("ErrorPersonControlerUpdate");
        	response = e.getMessage();
        }
        return response;
    }
    
    /**
     * deletePerson
     * @param person person
     * @return String with status response
     */
    public String deletePerson ( Person person){
    	String response = "OK";
        try{
        	personService.savePerson(person);

        }catch (Exception e){
        	e.printStackTrace();
        	System.out.println("ErrorPersonControlerdelete");
        	response = e.getMessage();
        }
        return response;
    }
        
}
