package com.exhibition.app.service.impl;

import com.exhibition.app.dao.ExhibitionDao;
import com.exhibition.app.domain.Exhibition;
import com.exhibition.app.domain.Ticket;
import com.exhibition.app.entity.ExhibitionEntity;
import com.exhibition.app.entity.TicketEntity;
import com.exhibition.app.service.ExhibitionService;
import com.exhibition.app.service.mapper.ExhibitionMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExhibitionServiceImplTest {
    public static final Exhibition EXHIBITION = initExhibition();
    public static final ExhibitionEntity EXHIBITION_ENTITY = initExhibitionEntity();
    private static List<Exhibition> EXHIBITION_LIST;
    private static List<ExhibitionEntity> ENTITY_LIST;

    @Mock
    private ExhibitionDao exhibitionDao;

    @Mock
    private ExhibitionMapper exhibitionMapper;

    @InjectMocks
    private ExhibitionServiceImpl exhibitionService;

    @Before
    public void init() {
        EXHIBITION_LIST = new ArrayList<>();
        ENTITY_LIST = new ArrayList<>();
        EXHIBITION_LIST.add(EXHIBITION);
        ENTITY_LIST.add(EXHIBITION_ENTITY);
    }

    @Test
    public void getExhibitionById() {
        when(exhibitionDao.findById(EXHIBITION_ENTITY.getId())).thenReturn(Optional.of(EXHIBITION_ENTITY));
        when(exhibitionMapper.mapEntityToDomain(EXHIBITION_ENTITY)).thenReturn(EXHIBITION);

        assertTrue(exhibitionService.getExhibitionById(EXHIBITION.getId()).isPresent());

        verify(exhibitionDao).findById(EXHIBITION_ENTITY.getId());
        verify(exhibitionMapper).mapEntityToDomain(EXHIBITION_ENTITY);
    }

    @Test
    public void getMonthExhibitionsMap() {
        when(exhibitionDao.findByDateLike(anyString())).thenReturn(ENTITY_LIST);
        when(exhibitionMapper.mapEntityToDomain(EXHIBITION_ENTITY)).thenReturn(EXHIBITION);

        assertTrue(exhibitionService.getMonthExhibitionsMap().size() > 0);

        verify(exhibitionDao, times(12)).findByDateLike(anyString());
        verify(exhibitionMapper, times(12)).mapEntityToDomain(EXHIBITION_ENTITY);
    }

    private static Exhibition initExhibition() {
        return Exhibition.builder()
                .withId(1L)
                .withName("Exhibition")
                .withDateFrom(Date.valueOf("2020-03-03"))
                .withDateTo(Date.valueOf("2020-04-04"))
                .withTheme("Theme")
                .withAbout("About")
                .withLongAbout("Long about")
                .withPrice(1234)
                .withImage("/img.png")
                .build();
    }

    private static ExhibitionEntity initExhibitionEntity() {
        return ExhibitionEntity.builder()
                .withId(1L)
                .withName("Exhibition")
                .withDateFrom(Date.valueOf("2020-03-03"))
                .withDateTo(Date.valueOf("2020-04-04"))
                .withTheme("Theme")
                .withAbout("About")
                .withLongAbout("Long about")
                .withPrice(1234)
                .withImage("/img.png")
                .build();
    }
}