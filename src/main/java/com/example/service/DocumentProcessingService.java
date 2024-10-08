package com.example.service;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class DocumentProcessingService {

    public final Directory indexDirectory;
    public Map<String, Integer> wordToIdMap = new HashMap<>();
    private int currentId = 0;

    public DocumentProcessingService() {
        this.indexDirectory = new RAMDirectory();
    }

    public String processDocument(File file) throws IOException {
        String content = readFileContent(file);
        List<Integer> embeddings = generateEmbeddings(content);
        String assetId = storeInVectorDatabase(file.getName(), content);

        return assetId;
    }

    public String readFileContent(File file) throws IOException {
        if (file.getName().endsWith(".pdf")) {
            return extractTextFromPDF(file);
        } else if (file.getName().endsWith(".txt")) {
            return new String(Files.readAllBytes(file.toPath()));
        } else {
            throw new IllegalArgumentException("Unsupported file type");
        }
    }

    private String extractTextFromPDF(File file) throws IOException {
        try (PDDocument document = PDDocument.load(file)) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }

    public List<Integer> generateEmbeddings(String content) {
        String[] words = content.toLowerCase().split("\\W+");
        List<Integer> embeddings = new ArrayList<>();
        for (String word : words) {
            if (wordToIdMap.containsKey(word)) {
                embeddings.add(wordToIdMap.get(word));
            } else {
                wordToIdMap.put(word, currentId++);
                embeddings.add(wordToIdMap.get(word));
            }
        }

        return embeddings;
    }

    public String storeInVectorDatabase(String documentId, String content) throws IOException {
        FieldType idFieldType = new FieldType();
        idFieldType.setStored(true);
        idFieldType.setIndexOptions(IndexOptions.DOCS);

        FieldType contentFieldType = new FieldType();
        contentFieldType.setStored(false);
        contentFieldType.setIndexOptions(IndexOptions.DOCS);

        try (IndexWriter writer = new IndexWriter(indexDirectory, new IndexWriterConfig())) {
            Document doc = new Document();
            doc.add(new Field("id", documentId, idFieldType));
            doc.add(new Field("content", content, contentFieldType));
            writer.addDocument(doc);
        }
        return documentId;
    }

    public String generateUniqueAssetId() {
        // Generate a UUID as the unique asset ID
        return UUID.randomUUID().toString();
    }

}
