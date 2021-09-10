package com.sales.market.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StreamUtils;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorServiceImplTest {
    @Autowired
    private TranslatorService service;

    @Test
    public void whenCopyOriginalStreamToOutputStream_thenCorrect() throws IOException {
        service = new TranslatorServiceImpl();

        String inputFileName = "src/test/resources/Original.txt";
        String outputFileName = "src/test/resources/output.txt";
        service.copyFile(inputFileName, outputFileName);

        assertTrue(new File(outputFileName).exists());
        String inputFileContent = service.getStringFromInputStream(new FileInputStream(inputFileName));
        String outputFileContent = service.getStringFromInputStream(new FileInputStream(outputFileName));
        assertEquals(inputFileContent, outputFileContent);
    }

    @Test
    public void whenReadFileAndReverting_thenCorrect() throws IOException {
        service = new TranslatorServiceImpl();

        String inputFileName = "src/test/resources/output.txt";
        String outputFileName = "src/test/resources/estrofasEnOrdenInverso.txt";

        service.readFileAndReverseOrder(inputFileName, outputFileName);

        assertTrue(new File(outputFileName).exists());
    }

    @Test
    public void whenReadFileReverseAndGenerateStadistics_thenCorrect() throws IOException {
        service = new TranslatorServiceImpl();

        String inputFileName = "src/test/resources/estrofasEnOrdenInverso.txt";
        String outputFileName = "src/test/resources/statistics.txt";

        service.readFileAndCreateStatistics(inputFileName, outputFileName);

        assertTrue(new File(outputFileName).exists());
    }

    @Test
    public void whenReadFileReverseAndReplaceWordYou_thenCorrect() throws IOException {
        service = new TranslatorServiceImpl();

        String inputFileName = "src/test/resources/estrofasEnOrdenInverso.txt";
        String outputFileName = "src/test/resources/finaloutput.txt";

        service.readFileAndReplace(inputFileName, outputFileName);

        assertTrue(new File(outputFileName).exists());
    }
}