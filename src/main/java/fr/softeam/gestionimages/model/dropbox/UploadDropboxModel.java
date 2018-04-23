package fr.softeam.gestionimages.model.dropbox;

public class UploadDropboxModel {
    private String path;
    private String mode;
    private Boolean autorename;
    private Boolean mute;

    public UploadDropboxModel() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Boolean getAutorename() {
        return autorename;
    }

    public void setAutorename(Boolean autorename) {
        this.autorename = autorename;
    }

    public Boolean getMute() {
        return mute;
    }

    public void setMute(Boolean mute) {
        this.mute = mute;
    }

    @Override
    public String toString() {
        return "UploadDropboxModel{" +
                "path='" + path + '\'' +
                ", mode='" + mode + '\'' +
                ", autorename=" + autorename +
                ", mute=" + mute +
                '}';
    }

    public void initDropboxModel(String path){
        this.setPath(path);
        this.setMode("overwrite");
        this.setAutorename(false);
        this.setMute(true);
    }
}
