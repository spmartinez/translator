package com.sales.market.service;

import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public interface TranslatorService  {
    void copyFile(String inputFileName, String outputFileName ) throws IOException;

    void readFileAndReverseOrder(String inputFileName, String outputFileName ) throws IOException;

    String readFileAndCreateStatistics(String inputFileName, String outputFileName ) throws IOException;

    void readFileAndReplace(String inputFileName, String outputFileName ) throws IOException;

    String getStringFromInputStream(FileInputStream fileInputStream) throws IOException;

}