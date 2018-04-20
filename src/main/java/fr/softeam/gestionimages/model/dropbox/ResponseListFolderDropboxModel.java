package fr.softeam.gestionimages.model.dropbox;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.softeam.gestionimages.model.dropbox.responselistfolder.EntriesItem;

import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ResponseListFolderDropboxModel{

	@JsonProperty("cursor")
	private String cursor;

	@JsonProperty("entries")
	private List<EntriesItem> entries;

	@JsonProperty("has_more")
	private boolean hasMore;

	public void setCursor(String cursor){
		this.cursor = cursor;
	}

	public String getCursor(){
		return cursor;
	}

	public void setEntries(List<EntriesItem> entries){
		this.entries = entries;
	}

	public List<EntriesItem> getEntries(){
		return entries;
	}

	public void setHasMore(boolean hasMore){
		this.hasMore = hasMore;
	}

	public boolean isHasMore(){
		return hasMore;
	}

	@Override
 	public String toString(){
		return 
			"ResponseListFolderDropboxModel{" + 
			"cursor = '" + cursor + '\'' + 
			",entries = '" + entries + '\'' + 
			",has_more = '" + hasMore + '\'' + 
			"}";
		}
}