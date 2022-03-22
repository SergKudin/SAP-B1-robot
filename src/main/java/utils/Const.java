package utils;

public enum Const {
    SITE ("https://www.olx.ua"),
    REQUEST ("ткань для тафтинга")
;

     private String info;
    Const(String info){
        this.info = info;
    }
    public String getConst(){ return info;}
}

