package fr.softeam.gestionimages.model.dropbox.responseerror;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Error{

	@JsonProperty("path")
	private Path path;

	@JsonProperty(".tag")
	private String tag;

	public void setPath(Path path){
		this.path = path;
	}

	public Path getPath(){
		return path;
	}

	public void setTag(String tag){
		this.tag = tag;
	}

	public String getTag(){
		return tag;
	}

	@Override
 	public String toString(){
		return 
			"Error{" + 
			"path = '" + path + '\'' + 
			",.tag = '" + tag + '\'' + 
			"}";
		}
}