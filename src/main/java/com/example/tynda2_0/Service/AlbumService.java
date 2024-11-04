package com.example.tynda2_0.Service;

import com.example.tynda2_0.Entity.Album;
import com.example.tynda2_0.Entity.User;
import com.example.tynda2_0.Repository.AlbumRepository;
import com.example.tynda2_0.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, UserRepository userRepository) {
        this.albumRepository = albumRepository;
        this.userRepository = userRepository;
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Optional<Album> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }

    public Album createAlbum(Album album, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(album::setUser);
        return albumRepository.save(album);
    }

    public Album updateAlbum(Long id, Album updatedAlbum) {
        return albumRepository.findById(id).map(album -> {
            album.setTitle(updatedAlbum.getTitle());
            album.setUser(updatedAlbum.getUser());
            return albumRepository.save(album);
        }).orElseThrow(() -> new RuntimeException("Album not found"));
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}
