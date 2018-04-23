package fr.softeam.gestionimages.model.dropbox;

public class DeleteDropboxModel {
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public DeleteDropboxModel(String path) {
        this.path = path;
    }
}
