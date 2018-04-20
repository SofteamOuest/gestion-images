package fr.softeam.gestionimages.model.dropbox.responselistfolder;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class EntriesItem{

	@JsonProperty("path_display")
	private String pathDisplay;

	@JsonProperty("rev")
	private String rev;

	@JsonProperty("path_lower")
	private String pathLower;

	@JsonProperty("property_groups")
	private List<PropertyGroupsItem> propertyGroups;

	@JsonProperty("sharing_info")
	private SharingInfo sharingInfo;

	@JsonProperty("has_explicit_shared_members")
	private boolean hasExplicitSharedMembers;

	@JsonProperty("size")
	private int size;

	@JsonProperty("server_modified")
	private String serverModified;

	@JsonProperty("name")
	private String name;

	@JsonProperty(".tag")
	private String tag;

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

	public void setPathLower(String pathLower){
		this.pathLower = pathLower;
	}

	public String getPathLower(){
		return pathLower;
	}

	public void setPropertyGroups(List<PropertyGroupsItem> propertyGroups){
		this.propertyGroups = propertyGroups;
	}

	public List<PropertyGroupsItem> getPropertyGroups(){
		return propertyGroups;
	}

	public void setSharingInfo(SharingInfo sharingInfo){
		this.sharingInfo = sharingInfo;
	}

	public SharingInfo getSharingInfo(){
		return sharingInfo;
	}

	public void setHasExplicitSharedMembers(boolean hasExplicitSharedMembers){
		this.hasExplicitSharedMembers = hasExplicitSharedMembers;
	}

	public boolean isHasExplicitSharedMembers(){
		return hasExplicitSharedMembers;
	}

	public void setSize(int size){
		this.size = size;
	}

	public int getSize(){
		return size;
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

	public void setTag(String tag){
		this.tag = tag;
	}

	public String getTag(){
		return tag;
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
			"EntriesItem{" + 
			"path_display = '" + pathDisplay + '\'' + 
			",rev = '" + rev + '\'' + 
			",path_lower = '" + pathLower + '\'' + 
			",property_groups = '" + propertyGroups + '\'' + 
			",sharing_info = '" + sharingInfo + '\'' + 
			",has_explicit_shared_members = '" + hasExplicitSharedMembers + '\'' + 
			",size = '" + size + '\'' + 
			",server_modified = '" + serverModified + '\'' + 
			",name = '" + name + '\'' + 
			",.tag = '" + tag + '\'' + 
			",id = '" + id + '\'' + 
			",content_hash = '" + contentHash + '\'' + 
			",client_modified = '" + clientModified + '\'' + 
			"}";
		}
}