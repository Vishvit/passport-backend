package com.passportbackend.document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public List<Document> findAllDocuments() {
        return documentRepository.findAll();
    }

    public Document saveDocument(Document newDocument) {
        return documentRepository.save(newDocument);
    }

    public Document findDocument(Long id) {
        return documentRepository.findById(id).orElseThrow();
    }

    public Document updateDocument(Long id, Document newDocument) {
        return documentRepository.findById(id).map(document -> {
            newDocument.setId(document.getId());
            return documentRepository.save(newDocument);
        }).orElseThrow();
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}
