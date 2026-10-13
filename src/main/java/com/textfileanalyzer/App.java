package com.textfileanalyzer;

import java.nio.file.Path;
import java.util.Scanner;
import java.io.IOException;

public class App 
{
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a character to search for: ");
        String input = scanner.nextLine();

        while (input.length() != 1) {
            System.out.println("Please enter exactly one character.");
            System.out.print("Enter a character to search for: ");
            input = scanner.nextLine();
        }

        char target = input.charAt(0);

        Path txtPath = Path.of("passage.txt");
        Path docxPath = Path.of("passage.docx");

        try {
            AnalysisResult txtResult = FileAnalyzer.analyzeFile(txtPath, target);
            AnalysisResult docxResult = FileAnalyzer.analyzeFile(docxPath, target);

            System.out.println("\nAnalysis completed Successfully!");
            displayResult(txtResult);
            displayResult(docxResult);
            compareResults(txtResult, docxResult);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid file: " + e.getMessage());
        }
    }

    public static void displayResult(AnalysisResult result) {
        System.out.println("\nFile: " + result.getFileName());
        System.out.println("Type: " + result.getFileType());
        System.out.println("Size: " + result.getFileSize() + " bytes");
        System.out.println("Lines: " + result.getLineCount());
        System.out.println("Words: " + result.getWordCount());
        System.out.println("Occurrences of '" + result.getSelectedCharacter() + "': " + result.getCharacterCount());
    }

    public static void compareResults(AnalysisResult txt, AnalysisResult docx) {
        System.out.println("\n--- File Comparison ---");

        System.out.println("Line counts match: "
                + (txt.getLineCount() == docx.getLineCount()));

        System.out.println("Word counts match: "
                + (txt.getWordCount() == docx.getWordCount()));

        System.out.println("Character counts match: "
                + (txt.getCharacterCount() == docx.getCharacterCount()));

        System.out.println("File size difference: "
                + Math.abs(txt.getFileSize() - docx.getFileSize())
                + " bytes");
    }
}
