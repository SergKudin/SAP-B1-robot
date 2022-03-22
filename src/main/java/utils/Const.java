package utils;

public enum Const {
    SITE ("https://192.168.0.63:8100/dispatcher"),
    REQUEST ("ткань для тафтинга"),
;

     private String info;
    Const(String info){
        this.info = info;
    }
    public String getConst(){ return info;}
}

