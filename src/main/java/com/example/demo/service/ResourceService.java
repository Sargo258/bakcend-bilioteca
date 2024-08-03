package com.example.demo.service;

;


import com.example.demo.models.Resource;
import com.example.demo.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    public Resource createResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    public Resource updateResource(Long id, Resource resource) {
        Resource existingResource = resourceRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found"));
        existingResource.setTitle(resource.getTitle());
        existingResource.setAuthor(resource.getAuthor());
        existingResource.setGenre(resource.getGenre());
        existingResource.setYearOfPublication(resource.getYearOfPublication());
        existingResource.setIsbn(resource.getIsbn());
        existingResource.setDownloadLink(resource.getDownloadLink());
        existingResource.setType(resource.getType());
        return resourceRepository.save(existingResource);
    }

    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }
}
