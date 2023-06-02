package com.gman.watchingocr;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

public class App {

	public static void main(String[] args){
		
		// Ejemplos: 
		// https://www.geeksforgeeks.org/tesseract-ocr-with-java-with-examples/
		// https://www.baeldung.com/java-ocr-tesseract
		
		
		
		// Inicializamos las variables que vamos a necesitar
        String pathToTrain = "src/main/resources/tessdata_best";
        String pathToInput = "src/main/resources/input/";
        String fullText=null;

		System.out.println("1 - Creamos instacia de Tesseract");
        ITesseract tesseract = new Tesseract();
        
        System.out.println("2 - Seteamos el entrenamiento realizado");
        // Entrenamientos: https://github.com/tesseract-ocr/tessdata_best
        // Entrenar tu propio modelo: https://stackoverflow.com/questions/63640134/tesseract-how-could-i-training-my-dataset

        tesseract.setDatapath(new File(pathToTrain).getPath());
        // Seteamos tanto el entrenamiento de español como el de ingles
        tesseract.setLanguage("spa+eng");

        System.out.println("3 - Initiate the watch.....");
        Path pathToWatch = Paths.get(pathToInput); 
        try {
			WatchDir watcher =new WatchDir(pathToWatch, true);
			watcher.setTesseract(tesseract);
			watcher.processEvents();
			
		} catch (IOException e) {
			e.printStackTrace();
		};
       
        System.out.println(fullText);
	}

	
}
