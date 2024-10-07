package Common.Exceptions;

public class ExistsException extends Exception {

    public ExistsException(String id, String entityType){
        super("Entity with ID " + id + " of TYPE " + entityType + " exists.");
    }

}
