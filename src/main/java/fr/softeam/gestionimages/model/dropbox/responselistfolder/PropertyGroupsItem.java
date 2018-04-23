package fr.softeam.gestionimages.model.dropbox.responselistfolder;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;
import java.util.List;

@Generated("com.robohorse.robopojogenerator")
public class PropertyGroupsItem{

	@JsonProperty("template_id")
	private String templateId;

	@JsonProperty("fields")
	private List<FieldsItem> fields;

	public void setTemplateId(String templateId){
		this.templateId = templateId;
	}

	public String getTemplateId(){
		return templateId;
	}

	public void setFields(List<FieldsItem> fields){
		this.fields = fields;
	}

	public List<FieldsItem> getFields(){
		return fields;
	}

	@Override
 	public String toString(){
		return 
			"PropertyGroupsItem{" + 
			"template_id = '" + templateId + '\'' + 
			",fields = '" + fields + '\'' + 
			"}";
		}
}