package com.cuubez.core.context;


import javax.ws.rs.core.MediaType;

public class ResponseContext {

   private String content;
   private Object returnObject;
   private String mediaType;


    public ResponseContext(String mediaType, Object returnObject) {
        this.mediaType = mediaType;
        this.returnObject = returnObject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }
}
