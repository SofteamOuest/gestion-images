package fr.softeam.gestionimages.model.dropbox;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(value = { "media_info" })
public class ResponseDownloadDropboxModel{

	@JsonProperty("path_display")
	private String pathDisplay;

	@JsonProperty("rev")
	private String rev;

	@JsonProperty("size")
	private int size;

	@JsonProperty("path_lower")
	private String pathLower;

	@JsonProperty("server_modified")
	private String serverModified;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private String id;

	@JsonProperty("content_hash")
	private String contentHash;

	@JsonProperty("client_modified")
	private String clientModified;

	public void setPathDisplay(String pathDisplay){
		this.pathDisplay = pathDisplay;
	}

	public String getPathDisplay(){
		return pathDisplay;
	}

	public void setRev(String rev){
		this.rev = rev;
	}

	public String getRev(){
		return rev;
	}

	public void setSize(int size){
		this.size = size;
	}

	public int getSize(){
		return size;
	}

	public void setPathLower(String pathLower){
		this.pathLower = pathLower;
	}

	public String getPathLower(){
		return pathLower;
	}

	public void setServerModified(String serverModified){
		this.serverModified = serverModified;
	}

	public String getServerModified(){
		return serverModified;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setContentHash(String contentHash){
		this.contentHash = contentHash;
	}

	public String getContentHash(){
		return contentHash;
	}

	public void setClientModified(String clientModified){
		this.clientModified = clientModified;
	}

	public String getClientModified(){
		return clientModified;
	}

	@Override
 	public String toString(){
		return 
			"ResponseDownloadDropboxModel{" + 
			"path_display = '" + pathDisplay + '\'' + 
			",rev = '" + rev + '\'' + 
			",size = '" + size + '\'' + 
			",path_lower = '" + pathLower + '\'' + 
			",server_modified = '" + serverModified + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",content_hash = '" + contentHash + '\'' + 
			",client_modified = '" + clientModified + '\'' + 
			"}";
		}
}