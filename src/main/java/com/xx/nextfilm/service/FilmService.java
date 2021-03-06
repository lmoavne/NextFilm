package com.xx.nextfilm.service;

import com.xx.nextfilm.dto.editor.FilmEditor;
import com.xx.nextfilm.dto.shower.FilmShower1;
import com.xx.nextfilm.dto.shower.FilmShower2;
import com.xx.nextfilm.dto.shower.FilmShower3;
import com.xx.nextfilm.entity.FilmEntity;

import java.util.List;

/**
 * Created by CuiH on 2016/5/15.
 */
public interface FilmService {

    FilmEntity findFilmById(Long id, boolean needDirectors, boolean needActors);

    FilmEditor getFilmEditorById(Long id);

    List<FilmEntity> findFilmsByName(String name, boolean needDirectors, boolean needActors);

    List<FilmEntity> findFilmsByType(String type, boolean needDirectors, boolean needActors);

    List<FilmEntity> findFilmsByCategory(String category, boolean needDirectors, boolean needActors);

    void createFilm(FilmEditor filmEditor);

    void updateFilm(FilmEditor filmEditor);

    void deleteFilm(FilmEntity film);

    List<FilmEntity> findAllFilms(boolean needDirectors, boolean needActors);

    List<FilmShower1> findAllFilmsWithShower1();

    List<FilmShower2> findAllFilmsWithShower2();

    List<FilmShower3> findAllFilmsWithShower3();

}
