package controller;
import service.MedicamentService;
import domain.Medicament;
public class MedicamentController {
	
    private MedicamentService medicamentService;

    public MedicamentController(){
    	medicamentService = new MedicamentService();
    }
    /**
     * saveMedicament
     * @param medicament medicament
     * @return String with status response
     */
    public String saveMedicament ( Medicament medicament){
    	String response = "Medicamento guardado correctamente";
        try{
        	medicament.medicationSaveOrUpdateValid();
        	medicamentService.saveMedicament(medicament);

        }catch (Exception e){
        	e.printStackTrace();
        	System.out.println("ErrorMedicamentControlerInsert");
        	response = e.getMessage();
        }
        return response;
    }
    
    /**
     * updateMedicament
     * @param medicament medicament
     * @return String with status response
     */
    public String updateMedicament ( Medicament medicament){
    	String response = "Medicamento actualizado correctamente";
        try{
        	medicament.medicationSaveOrUpdateValid();
        	medicamentService.updateMedicament(medicament);

        }catch (Exception e){
        	e.printStackTrace();
        	System.out.println("ErrorMedicamentControlerUpdate");
        	response = e.getMessage();
        }
        return response;
    }
    
    /**
     * deleteMedicament
     * @param medicament medicament
     * @return String with status response
     */
    public String deleteMedicament ( Medicament medicament){
    	String response = "Medicamento eliminado correctamente";
        try{
        	medicamentService.deleteMedicament(medicament);

        }catch (Exception e){
        	e.printStackTrace();
        	System.out.println("ErrorMedicamentControlerDelete");
        	response = e.getMessage();
        }
        return response;
    }
        
}
