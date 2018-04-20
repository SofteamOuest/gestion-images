package fr.softeam.gestionimages.model.dropbox.responselistfolder;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class FieldsItem{

	@JsonProperty("name")
	private String name;

	@JsonProperty("value")
	private String value;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	@Override
 	public String toString(){
		return 
			"FieldsItem{" + 
			"name = '" + name + '\'' + 
			",value = '" + value + '\'' + 
			"}";
		}
}