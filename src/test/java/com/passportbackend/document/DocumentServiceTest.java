package com.passportbackend.document;

import com.passportbackend.project.Project;
import com.passportbackend.project.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class DocumentServiceTest {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private DocumentService documentService;

    @Test
    void testFindAllDocuments() {
        Project project = new Project();
        project.setName("Name");
        project.setStatus("status1");
        projectRepository.save(project);
        Document document = new Document();
        document.setProject(project);
        document.setStatus("status1");
        document.setFilePath("/file");
        documentRepository.save(document);
        List<Document> documents = documentService.findAllDocuments();
        document = documents.getFirst();
        project = document.getProject();
        assertEquals(1, documents.size());
        assertNotNull(project);
    }

    @Test
    void testSaveDocument() {
        Project project = new Project();
        project.setName("Name");
        project.setStatus("status1");
        projectRepository.save(project);
        Document expected = new Document();
        expected.setProject(project);
        expected.setStatus("status1");
        expected.setFilePath("/file");
        documentService.saveDocument(expected);
        Document actual = documentRepository.findById(expected.getId()).orElseThrow();
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getProject(), actual.getProject());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getFilePath(), actual.getFilePath());
    }

    @Test
    void testFindDocument() {
        Project project = new Project();
        project.setName("Name");
        project.setStatus("status1");
        projectRepository.save(project);
        Document expected = new Document();
        expected.setProject(project);
        expected.setStatus("status1");
        expected.setFilePath("/file");
        expected = documentRepository.save(expected);
        Document actual = documentService.findDocument(expected.getId());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getProject(), actual.getProject());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getFilePath(), actual.getFilePath());
    }

    @Test
    void testUpdateDocument() {
        Project project = new Project();
        project.setName("Name");
        project.setStatus("status1");
        projectRepository.save(project);
        Document expected = new Document();
        expected.setProject(project);
        expected.setStatus("status1");
        expected.setFilePath("/file");
        expected = documentRepository.save(expected);
        expected.setStatus("status2");
        Document actual = documentService.updateDocument(expected.getId(), expected);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getProject(), actual.getProject());
        assertEquals(expected.getStatus(), actual.getStatus());
        assertEquals(expected.getFilePath(), actual.getFilePath());
    }

    @Test
    void testDeleteDocument() {
        Project project = new Project();
        project.setName("Name");
        project.setStatus("status1");
        projectRepository.save(project);
        Document expected = new Document();
        expected.setProject(project);
        expected.setStatus("status1");
        expected.setFilePath("/file");
        expected = documentRepository.save(expected);
        long before = documentRepository.findById(expected.getId()).stream().count();
        documentService.deleteDocument(expected.getId());
        long after = documentRepository.findById(expected.getId()).stream().count();
        assertTrue(before > after);
    }
}
