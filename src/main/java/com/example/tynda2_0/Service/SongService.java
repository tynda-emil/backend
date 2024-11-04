package com.example.tynda2_0.Service;

import com.example.tynda2_0.Entity.Album;
import com.example.tynda2_0.Entity.Song;
import com.example.tynda2_0.Repository.AlbumRepository;
import com.example.tynda2_0.Repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final AlbumRepository albumRepository;

    @Autowired
    public SongService(SongRepository songRepository, AlbumRepository albumRepository) {
        this.songRepository = songRepository;
        this.albumRepository = albumRepository;
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Optional<Song> getSongById(Long id) {
        return songRepository.findById(id);
    }

    public Song createSong(Song song, Long albumId) {
        Optional<Album> album = albumRepository.findById(albumId);
        album.ifPresent(song::setAlbum);
        return songRepository.save(song);
    }

    public Song updateSong(Long id, Song updatedSong) {
        return songRepository.findById(id).map(song -> {
            song.setTitle(updatedSong.getTitle());
            song.setDuration(updatedSong.getDuration());
            song.setAlbum(updatedSong.getAlbum());
            song.setGenre(updatedSong.getGenre());
            song.setUrl(updatedSong.getUrl());
            song.setCounter(updatedSong.getCounter());
            return songRepository.save(song);
        }).orElseThrow(() -> new RuntimeException("Song not found"));
    }

    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }
}
