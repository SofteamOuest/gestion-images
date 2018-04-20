package fr.softeam.gestionimages.model.gestionimages;

public class ResultDownloadModel {
    private String path;
    private byte[] fileData;

    public ResultDownloadModel() {
    }

    public ResultDownloadModel(String path, byte[] fileData) {
        this.path = path;
        this.fileData = fileData;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
