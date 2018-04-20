package fr.softeam.gestionimages.model.dropbox.responseerror;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ResponseErrorModel{

	@JsonProperty("error_summary")
	private String errorSummary;

	@JsonProperty("error")
	private Error error;

	public void setErrorSummary(String errorSummary){
		this.errorSummary = errorSummary;
	}

	public String getErrorSummary(){
		return errorSummary;
	}

	public void setError(Error error){
		this.error = error;
	}

	public Error getError(){
		return error;
	}

	@Override
 	public String toString(){
		return 
			"ResponseErrorModel{" + 
			"error_summary = '" + errorSummary + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}