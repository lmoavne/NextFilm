package com.xx.nextfilm.service;

import com.xx.nextfilm.dao.ActorDao;
import com.xx.nextfilm.dao.FilmDao;
import com.xx.nextfilm.dto.FilmEditor;
import com.xx.nextfilm.entity.ActorEntity;
import com.xx.nextfilm.entity.FilmEntity;
import com.xx.nextfilm.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CuiH on 2016/5/15.
 */
@Transactional
@Service("filmService")
public class FilmServiceImpl implements FilmService {

    @Autowired
    FilmDao filmDao;

    @Autowired
    ActorDao actorDao;


    public FilmEntity findFilmById(Long id, boolean needDirectors, boolean needActors) {
        return filmDao.findById(id, needDirectors, needActors);
    }


    public FilmEditor getFilmEditorById(Long id, boolean needDirectors, boolean needActors) {
        FilmEntity filmEntity = findFilmById(id, needDirectors, needActors);

        if (filmEntity == null) return null;

        FilmEditor filmEditor = new FilmEditor();

        filmEditor.setId(filmEntity.getId());
        filmEditor.setName(filmEntity.getName());
        filmEditor.setAlias(filmEntity.getAlias());
        filmEditor.setBrief(filmEntity.getBrief());
        filmEditor.setLanguage(filmEntity.getLanguage());
        filmEditor.setLength(filmEntity.getLength());
        filmEditor.setOnDate(Utils.convertDateToString(filmEntity.getOnDate()));
        filmEditor.setImageUrl(filmEntity.getImageUrl());
        filmEditor.setCategory(filmEntity.getCategory());
        filmEditor.setType(filmEntity.getType());

        List<Long> a = new ArrayList<Long>();
        List<ActorEntity> actors = filmEntity.getActors();
        if (needActors && actors != null) {
            for (ActorEntity actor: actors) {
                a.add(actor.getId());
            }
        }
        filmEditor.setActors(a);

        List<Long> d = new ArrayList<Long>();
        List<ActorEntity> directors = filmEntity.getDirectors();
        if (needDirectors && directors != null) {
            for (ActorEntity director: directors) {
                d.add(director.getId());
            }
        }
        filmEditor.setDirectors(d);

        return filmEditor;
    }


    public List<FilmEntity> findFilmByName(String name) {
        List<FilmEntity> list1 = filmDao.findByName(name);
        List<FilmEntity> list2 = filmDao.findByAlias(name);

        list1.addAll(list2);

        return list1;
    }


    public List<FilmEntity> findFilmByType(String type) {
        return filmDao.findByType(type);
    }


    public List<FilmEntity> findFilmByCategory(String category) {
        return filmDao.findByCategory(category);
    }


    public void createFilm(FilmEditor filmEditor) {
        filmDao.doSave(getEntityFromEditor(filmEditor, false));
    }


    public void updateFilm(FilmEditor filmEditor) {
        filmDao.doUpdate(getEntityFromEditor(filmEditor, true));
    }


    public void deleteFilm(FilmEntity film) {
        filmDao.doDelete(film);
    }


    public List<FilmEntity> findAllFilms() {
        return filmDao.findAll();
    }


    private FilmEntity getEntityFromEditor(FilmEditor filmEditor, boolean needId) {
        FilmEntity filmEntity = new FilmEntity();

        if (needId) {
            filmEntity.setId(filmEditor.getId());
        }

        filmEntity.setName(filmEditor.getName());
        filmEntity.setAlias(filmEditor.getAlias());
        filmEntity.setBrief(filmEditor.getBrief());
        filmEntity.setLanguage(filmEditor.getLanguage());
        filmEntity.setLength(filmEditor.getLength());
        filmEntity.setOnDate(Utils.convertStringToDate(filmEditor.getOnDate()));
        filmEntity.setImageUrl(filmEditor.getImageUrl());
        filmEntity.setCategory(filmEditor.getCategory());
        filmEntity.setType(filmEditor.getType());

        List<ActorEntity> directors = new ArrayList<ActorEntity>();
        List<Long> editorDirectors = filmEditor.getDirectors();
        if (editorDirectors != null) {
            for (Long id: editorDirectors) {
                directors.add(actorDao.findById(id));
            }
        }
        filmEntity.setDirectors(directors);

        List<ActorEntity> actors = new ArrayList<ActorEntity>();
        List<Long> editorActors = filmEditor.getActors();
        if (editorActors != null) {
            for (Long id: editorActors) {
                actors.add(actorDao.findById(id));
            }
        }
        filmEntity.setActors(actors);

        return filmEntity;
    }


    public HashMap<Long, String> getAllFilmsWithMap() {
        List<FilmEntity> allFilms = findAllFilms();

        HashMap<Long, String> map = new HashMap<Long, String>();
        for (FilmEntity film: allFilms) {
            map.put(film.getId(), film.getName());
        }

        return map;
    }

}