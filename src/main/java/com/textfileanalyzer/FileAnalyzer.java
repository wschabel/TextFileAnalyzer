package com.textfileanalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import java.io.InputStream;

public class FileAnalyzer {

    public static String readTxt(Path path) throws IOException {
        return Files.readString(path);
    }

    public static String readDocx(Path path) throws IOException {
        StringBuilder text = new StringBuilder();

        try (InputStream stream = Files.newInputStream(path);
        XWPFDocument document = new XWPFDocument(stream)) {
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                text.append(paragraph.getText()).append("\n");
            }
        }

        return text.toString().replaceFirst("\\R$", "");
    }

    public static int countLines(String text) {
        if (text.isEmpty()) {
            return 0;
        }

        return text.split("\\R", -1).length;
    }

    public static int countWords(String text) {
        if (text.isBlank()) {
            return 0;
        }

        return text.trim().split("\\s+").length;
    }

    public static int countCharacters(String text, char target) {
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            if (Character.toLowerCase(text.charAt(i)) == Character.toLowerCase(target)) {
                count++;
            }
        }

        return count;
    }

    public static AnalysisResult analyzeFile(Path path, char target) throws IOException {
        String fileName = path.getFileName().toString();
        String fileType;
        String text;

        if (fileName.toLowerCase().endsWith(".txt")) {
            fileType = "TXT";
            text = readTxt(path);
        }
        else if (fileName.toLowerCase().endsWith(".docx")) {
            fileType = "DOCX";
            text = readDocx(path);
        }
        else {
            throw new IllegalArgumentException("Unsupported file type: " + fileName);
        }

        long fileSize = Files.size(path);
        int lineCount = countLines(text);
        int wordCount = countWords(text);
        int characterCount = countCharacters(text, target);

        return new AnalysisResult(
                fileName,
                fileType,
                fileSize,
                lineCount,
                wordCount,
                target,
                characterCount
        );
    }
}
