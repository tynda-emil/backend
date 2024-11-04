package com.example.tynda2_0.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class Playlist_trackId implements java.io.Serializable {
    private static final long serialVersionUID = 193323931699912295L;
    @NotNull
    @Column(name = "song_id", nullable = false)
    private Long songId;

    @NotNull
    @Column(name = "album_id", nullable = false)
    private Long albumId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Playlist_trackId entity = (Playlist_trackId) o;
        return Objects.equals(this.albumId, entity.albumId) &&
                Objects.equals(this.songId, entity.songId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, songId);
    }

}