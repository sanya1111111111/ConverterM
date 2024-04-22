package ImageConverter;

import java.io.File;

public class FileTools {
    private File file;
    public FileTools(File file){
        this.file = file;
    }
    public String getNameWithoutFormat(){
        return this.file.getName().substring(0, getDotPosition());
    }
    public String getFormat(){
        return this.file.getName().substring(getDotPosition()+1);
    }
    public int getDotPosition(){
        return this.file.getName().lastIndexOf(".");
    }
}

