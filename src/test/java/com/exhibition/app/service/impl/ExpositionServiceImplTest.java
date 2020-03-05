package com.exhibition.app.service.impl;

import com.exhibition.app.dao.ExpositionDao;
import com.exhibition.app.dao.Page;
import com.exhibition.app.domain.Exposition;
import com.exhibition.app.entity.ExpositionEntity;
import com.exhibition.app.service.mapper.ExpositionMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExpositionServiceImplTest {
    public static final Page PAGE = new Page(0, 1);
    public static final ExpositionEntity EXPOSITION_ENTITY = ExpositionEntity.builder()
            .withId(1L)
            .withName("Concert")
            .withAbout("Music")
            .withImage("/img.jpg")
            .withExhibitionId(1L)
            .build();
    public static final Exposition EXPOSITION = Exposition.builder()
            .withId(1L)
            .withName("Concert")
            .withAbout("Music")
            .withImage("/img.jpg")
            .withExhibitionId(1L)
            .build();
    public static List<ExpositionEntity> ENTITY_LIST;

    @Mock
    private ExpositionDao expositionDao;

    @Mock
    private ExpositionMapper mapper;

    @InjectMocks
    private ExpositionServiceImpl expositionService;

    @Before
    public void init() {
        ENTITY_LIST = new ArrayList<>();
        ENTITY_LIST.add(EXPOSITION_ENTITY);
    }

    @Test
    public void getExpositionsByExhibIdShouldReturnList() {
        when(expositionDao.findAll(EXPOSITION_ENTITY.getExhibitionId(), PAGE)).thenReturn(ENTITY_LIST);
        when(mapper.mapEntityToDomain(EXPOSITION_ENTITY)).thenReturn(EXPOSITION);

        assertTrue(expositionService.getExpositionsByExhibId(EXPOSITION_ENTITY.getExhibitionId(), PAGE).size() > 0);

        verify(expositionDao).findAll(EXPOSITION_ENTITY.getExhibitionId(), PAGE);
    }

    @Test
    public void countShouldReturnValueGreaterThan0() {
     when(expositionDao.count(EXPOSITION_ENTITY.getExhibitionId())).thenReturn(1);

     assertTrue(expositionService.count(EXPOSITION_ENTITY.getExhibitionId()) > 0);

     verify(expositionDao).count(EXPOSITION_ENTITY.getExhibitionId());
    }

}