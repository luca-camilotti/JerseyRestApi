package luke.jersey.rest.classes;

/*
 * Class for error messages 
 * returned to client
 * */
public class ErrorResponse {

	public ErrorResponse() {
		
	}
	
	private int status; // response status code
	private String message; // error message
	
	
	public ErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
