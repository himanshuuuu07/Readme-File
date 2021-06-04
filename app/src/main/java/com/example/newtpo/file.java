package com.example.newtpo;

public class file
{
    String fileName,fileUrl,fileDes;

    public file() {
    }

    public file(String fileName, String fileUrl, String fileDes) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.fileDes = fileDes;
    }

    public String getFileDes() {
        return fileDes;
    }

    public void setFileDes(String fileDes) {
        this.fileDes = fileDes;
    }



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}