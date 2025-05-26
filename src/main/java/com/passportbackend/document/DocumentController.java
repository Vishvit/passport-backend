package com.passportbackend.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @GetMapping(value = "/documents")
    public List<Document> findAllDocuments() {
        return documentService.findAllDocuments();
    }

    @PostMapping(value = "/documents")
    public Document saveDocument(@RequestBody Document newDocument) {
        return documentService.saveDocument(newDocument);
    }

    @GetMapping(value = "/documents/{id}")
    public Document findDocument(@PathVariable Long id) {
        return documentService.findDocument(id);
    }

    @PutMapping(value = "/documents/{id}")
    public Document updateDocument(@PathVariable Long id, @RequestBody Document newDocument) {
        return documentService.updateDocument(id, newDocument);
    }

    @DeleteMapping(value = "/documents/{id}")
    public ResponseEntity<Object> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.ok().build();
    }
}
