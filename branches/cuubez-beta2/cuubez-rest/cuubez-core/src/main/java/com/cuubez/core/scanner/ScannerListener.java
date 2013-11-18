package com.cuubez.core.scanner;


import java.io.InputStream;

public interface ScannerListener {


   public void process(String resourceName, InputStream input);

}
