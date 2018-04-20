package fr.softeam.gestionimages.model.dropbox;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ListFolderDropboxModel{

	@JsonProperty("path")
	private String path;

	@JsonProperty("include_mounted_folders")
	private boolean includeMountedFolders;

	@JsonProperty("include_has_explicit_shared_members")
	private boolean includeHasExplicitSharedMembers;

	@JsonProperty("include_media_info")
	private boolean includeMediaInfo;

	@JsonProperty("include_deleted")
	private boolean includeDeleted;

	@JsonProperty("recursive")
	private boolean recursive;

	public ListFolderDropboxModel() {
		this.recursive = false;
		this.includeDeleted = false;
		this.includeMediaInfo = false;
		this.includeHasExplicitSharedMembers = false;
		this.includeMountedFolders = true;
	}

	public void setPath(String path){
		this.path = path;
	}

	public String getPath(){
		return path;
	}

	public void setIncludeMountedFolders(boolean includeMountedFolders){
		this.includeMountedFolders = includeMountedFolders;
	}

	public boolean isIncludeMountedFolders(){
		return includeMountedFolders;
	}

	public void setIncludeHasExplicitSharedMembers(boolean includeHasExplicitSharedMembers){
		this.includeHasExplicitSharedMembers = includeHasExplicitSharedMembers;
	}

	public boolean isIncludeHasExplicitSharedMembers(){
		return includeHasExplicitSharedMembers;
	}

	public void setIncludeMediaInfo(boolean includeMediaInfo){
		this.includeMediaInfo = includeMediaInfo;
	}

	public boolean isIncludeMediaInfo(){
		return includeMediaInfo;
	}

	public void setIncludeDeleted(boolean includeDeleted){
		this.includeDeleted = includeDeleted;
	}

	public boolean isIncludeDeleted(){
		return includeDeleted;
	}

	public void setRecursive(boolean recursive){
		this.recursive = recursive;
	}

	public boolean isRecursive(){
		return recursive;
	}

	@Override
 	public String toString(){
		return 
			"ListFolderDropboxModel{" + 
			"path = '" + path + '\'' + 
			",include_mounted_folders = '" + includeMountedFolders + '\'' + 
			",include_has_explicit_shared_members = '" + includeHasExplicitSharedMembers + '\'' + 
			",include_media_info = '" + includeMediaInfo + '\'' + 
			",include_deleted = '" + includeDeleted + '\'' + 
			",recursive = '" + recursive + '\'' + 
			"}";
		}
}