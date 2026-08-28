package org.example.controller;

import org.example.service.DocumentService;
import org.springframework.ai.document.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            documentService.ingestPdf(file);
            return ResponseEntity.ok("Upload and Ingest successfully!");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/ingest")
    public ResponseEntity<String> ingest(@RequestParam("file") MultipartFile file) {
        // Có thể gộp chung logic với upload trong bài toán này
        return upload(file);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Document>> search(@RequestParam("keyword") String keyword) {
        return ResponseEntity.ok(documentService.search(keyword));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") String id) {
        documentService.deleteDocument(id);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
