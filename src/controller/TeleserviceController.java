package controller;
import service.TeleserviceService;
import domain.Person;
public class TeleserviceController {
    private TeleserviceService teleserviceService; 

    public TeleserviceController(){
         teleserviceService = new TeleserviceService();
    }
    public Person login ( String userName, String password){
        Person response = null;
        Person user = new Person();
        user.setUserName(userName);
        user.setPassword(new String (password));
        
        try{
            user.loginValid();
            response = teleserviceService.login(user);
        }catch (Exception e){
            response.setError(e.getMessage());
        }
        return response;
    }
        
}
