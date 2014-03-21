package com.cuubez.core.resource.metaData;

public class ClassMetaData {
	
	private String path;
	private String[] consume;
	private String[] produce;
    private Class<?> clazz;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

    public String[] getConsume() {
        return consume;
    }

    public void setConsume(String[] consume) {
        this.consume = consume;
    }

    public String[] getProduce() {
        return produce;
    }

    public void setProduce(String[] produce) {
        this.produce = produce;
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }
}
