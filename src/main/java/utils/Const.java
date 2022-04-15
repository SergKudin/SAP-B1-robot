package utils;

public enum Const {
    SITE ("https://192.168.0.63:8100/dispatcher"),
//    SITE ("https://mvnrepository.com/artifact/com.github.jesg/phantomjsdriver/2.0.0"),
    ;

     private String info;
    Const(String info){
        this.info = info;
    }
    public String getConst(){ return info;}
}

