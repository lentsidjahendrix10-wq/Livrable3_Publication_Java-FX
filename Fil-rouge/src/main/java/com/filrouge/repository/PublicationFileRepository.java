package com.filrouge.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.filrouge.model.Publication;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.net.URISyntaxException;

public class PublicationFileRepository {
	private static final PublicationFileRepository INSTANCE = new PublicationFileRepository();
	public static PublicationFileRepository getInstance() { return INSTANCE; }

    private final Path folder;
    private final ObjectMapper mapper;

    public PublicationFileRepository() {
        this.folder = Paths.get("src/main/resources/repository/publication");
        this.mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .registerModule(new com.fasterxml.jackson.datatype.jsr310.JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public void save(Publication publication) {
        try {
            if (publication.getUid() == null) {
                publication.setUid(UUID.randomUUID());
            }
            Files.createDirectories(folder);
            Path file = folder.resolve(publication.getUid().toString() + ".json");
            mapper.writeValue(file.toFile(), publication);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Publication load(UUID uid) {
        try {
            Path file = folder.resolve(uid.toString() + ".json");
            return mapper.readValue(file.toFile(), Publication.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Publication> findAll() {
        List<Publication> result = new ArrayList<>();
        try {
            Files.createDirectories(folder);
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(folder, "*.json")) {
                for (Path file : stream) {
                    Publication pub = mapper.readValue(file.toFile(), Publication.class);
                    result.add(pub);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void delete(UUID uid) {
        try {
            Path file = folder.resolve(uid.toString() + ".json");
            Files.deleteIfExists(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}