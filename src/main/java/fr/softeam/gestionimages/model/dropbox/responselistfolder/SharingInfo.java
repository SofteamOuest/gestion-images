package fr.softeam.gestionimages.model.dropbox.responselistfolder;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SharingInfo{

	@JsonProperty("read_only")
	private boolean readOnly;

	@JsonProperty("modified_by")
	private String modifiedBy;

	@JsonProperty("parent_shared_folder_id")
	private String parentSharedFolderId;

	public void setReadOnly(boolean readOnly){
		this.readOnly = readOnly;
	}

	public boolean isReadOnly(){
		return readOnly;
	}

	public void setModifiedBy(String modifiedBy){
		this.modifiedBy = modifiedBy;
	}

	public String getModifiedBy(){
		return modifiedBy;
	}

	public void setParentSharedFolderId(String parentSharedFolderId){
		this.parentSharedFolderId = parentSharedFolderId;
	}

	public String getParentSharedFolderId(){
		return parentSharedFolderId;
	}

	@Override
 	public String toString(){
		return 
			"SharingInfo{" + 
			"read_only = '" + readOnly + '\'' + 
			",modified_by = '" + modifiedBy + '\'' + 
			",parent_shared_folder_id = '" + parentSharedFolderId + '\'' + 
			"}";
		}
}