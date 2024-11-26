package com.cgvsu;

import com.cgvsu.model.Model;
import com.cgvsu.objreader.ObjReader;
import com.cgvsu.objwriter.ObjWriterClass;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        String BEFORE_FOLDER = "before/";
        String AFTER_FOLDER = "after/";
        File folder = new File("TestModels/" + BEFORE_FOLDER);
        ObjWriterClass objWriter = new ObjWriterClass();
        for (File file : folder.listFiles()) {
            Model model = read(BEFORE_FOLDER + file.getName());
            objWriter.write(model, "TestModels/" + AFTER_FOLDER + file.getName());
            Model model2 = read(AFTER_FOLDER + file.getName());
            if (!model.equals(model2)) {
                System.out.println("Model has changed after writing: " + file.getName());
                return;
            }
        }
        System.out.println("All models equivalents");
    }
    private static Model read(String filename) throws IOException {
        Path fileName = Path.of("TestModels/" + filename);
        String fileContent = Files.readString(fileName);
        System.out.println("Loading model " + filename);
        return ObjReader.read(fileContent);
    }
}
