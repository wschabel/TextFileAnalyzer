package com.textfileanalyzer;

public class AnalysisResult {
    private String fileName;
    private String fileType;
    private long fileSize;
    private int lineCount;
    private int wordCount;
    private char selectedCharacter;
    private int characterCount;

    public AnalysisResult(String fileName, String fileType, long fileSize,
                          int lineCount, int wordCount, char selectedCharacter,
                          int characterCount) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.lineCount = lineCount;
        this.wordCount = wordCount;
        this.selectedCharacter = selectedCharacter;
        this.characterCount = characterCount;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public int getLineCount() {
        return lineCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public char getSelectedCharacter() {
        return selectedCharacter;
    }

    public int getCharacterCount() {
        return characterCount;
    }
}
