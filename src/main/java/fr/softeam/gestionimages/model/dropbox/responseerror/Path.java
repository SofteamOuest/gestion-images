package fr.softeam.gestionimages.model.dropbox.responseerror;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Path{

	@JsonProperty(".tag")
	private String tag;

	public void setTag(String tag){
		this.tag = tag;
	}

	public String getTag(){
		return tag;
	}

	@Override
 	public String toString(){
		return 
			"Path{" + 
			".tag = '" + tag + '\'' + 
			"}";
		}
}