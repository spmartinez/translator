package com.sales.market.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class TranslatorServiceImpl implements TranslatorService {

    @Override
    public void copyFile(String inputFileName, String outputFileName) throws IOException {
        File outputFile = new File(outputFileName);
        InputStream in = new FileInputStream(inputFileName);
        OutputStream out = new FileOutputStream(outputFile);

        StreamUtils.copy(in, out);
    }

    @Override
    public void readFileAndReverseOrder(String inputFileName, String outputFileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(inputFileName);
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
            String sb = "";
            String line;
            while ((line = br.readLine()) != null) {
                sb = line + '\n' + sb;
            }

            FileWriter myWriter = new FileWriter(outputFileName);
            myWriter.write(sb);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public String readFileAndCreateStatistics(String inputFileName, String outputFileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(inputFileName);
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
            String sb = "";
            String line;
            int strofas = 1;
            while ((line = br.readLine()) != null) {
                if ( line.length() == 0) {
                    strofas += 1;
                }
                sb = line + '\n' + sb;
            }
            sb = sb.replace(',', ' ');
            String[] words = sb.split(" ");

            Map<String,Integer> wordMap = new HashMap<String,Integer>();
            String word = "";
            for (String s : words) {
                word = s;
                if (wordMap.get(word) != null) {
                    wordMap.put(word, wordMap.get(word)+1);
                } else {
                    wordMap.put(word, 1);
                }
            }
            AtomicReference<String> wordMoreRepeat = new AtomicReference<>("");
            final Integer[] lastMajorRepeat = {0};
            wordMap.forEach((s, repeat) -> {
                if (repeat > lastMajorRepeat[0] && s.length() > 0) {
                    lastMajorRepeat[0] = repeat;
                    wordMoreRepeat.set(s);
                }
            });

            FileWriter myWriter = new FileWriter(outputFileName);
            myWriter.write("Cantidad de estrofas: " + strofas + "\n");
            myWriter.write("Palabra mas repetida: " + wordMoreRepeat.get());
            myWriter.close();
            return wordMoreRepeat.get();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void readFileAndReplace(String inputFileName, String outputFileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(inputFileName);
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
            String sb = "";
            String line;
            while ((line = br.readLine()) != null) {
                sb = sb + line + '\n'; //se mantiene el orden
            }

            sb = sb.replace(readFileAndCreateStatistics(inputFileName, outputFileName), "you");

            FileWriter myWriter = new FileWriter(outputFileName);
            myWriter.write(sb);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public String getStringFromInputStream(FileInputStream fileInputStream) throws IOException {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
            return sb.toString();
        }
    }
}
