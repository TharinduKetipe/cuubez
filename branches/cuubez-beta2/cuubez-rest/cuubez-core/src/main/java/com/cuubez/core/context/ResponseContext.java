package com.cuubez.core.context;


import com.cuubez.core.util.MediaType;

public class ResponseContext {

   private String content;
   private Object returnObject;
   private MediaType mediaType;


    public ResponseContext(MediaType mediaType, Object returnObject) {
        this.mediaType = mediaType;
        this.returnObject = returnObject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }
}
