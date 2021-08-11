package by.epam.dkozyrev1.ecafe.dao.exception;

public class DAOConnectionPoolRaisingException extends DAOException{
    public DAOConnectionPoolRaisingException(){
        super();
    }
    public DAOConnectionPoolRaisingException(String message){
        super(message);
    }
    public DAOConnectionPoolRaisingException(String message, Throwable cause){
        super(message,cause);
    }
    public DAOConnectionPoolRaisingException(Throwable cause){
        super(cause);
    }
}
