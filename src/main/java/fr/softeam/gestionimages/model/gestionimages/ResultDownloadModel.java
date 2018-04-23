package fr.softeam.gestionimages.model.gestionimages;

public class ResultDownloadModel {
    private String filename;
    private byte[] fileData;

    public ResultDownloadModel() {
    }

    public ResultDownloadModel(String filename, byte[] fileData) {
        this.filename = filename;
        this.fileData = fileData;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
