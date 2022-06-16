package controller;
import service.CallLogService;
import domain.CallLog;
public class CallLogController {
	
    private CallLogService callLogService;

    public CallLogController(){
    	callLogService = new CallLogService();
    }
    /**
     * saveCall
     * @param callLog callLog
     * @return String with status response
     */
    public String saveCall ( CallLog callLog){
    	String response = "OK";
        try{
        	callLogService.saveCall(callLog);

        }catch (Exception e){
        	e.printStackTrace();
        	System.out.println("ErrorCallLogControler");
        	response = e.getMessage();
        }
        return response;
    }
        
}
