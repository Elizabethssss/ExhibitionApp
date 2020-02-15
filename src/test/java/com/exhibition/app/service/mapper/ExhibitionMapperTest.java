package com.exhibition.app.service.mapper;

import com.exhibition.app.domain.Exhibition;
import com.exhibition.app.domain.Exposition;
import com.exhibition.app.entity.ExhibitionEntity;
import com.exhibition.app.entity.ExpositionEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExhibitionMapperTest {
    private static final Long ID = 1L;
    private static final String NAME ="Flowers Expo 2020";
    private static final Date DATE_FROM = Date.valueOf(LocalDate.of(2020, 7, 22));
    private static final Date DATE_TO = Date.valueOf(LocalDate.of(2020, 7, 29));
    private static final String THEME = "Art";
    private static final String ABOUT = "Smells good";
    private static final String LONG_ABOUT = "Lot of flowers";
    private static final double PRICE = 333.33;
    private static final String IMAGE ="flowerParty.png";

    private ExhibitionMapper exhibitionMapper = new ExhibitionMapper();

    @Mock
    private ExhibitionEntity exhibitionEntity;

    @Mock
    private Exhibition exhibition;

    @Test
    public void mapEntityToDomainShouldReturnExhibition() {
        when(exhibitionEntity.getId()).thenReturn(ID);
        when(exhibitionEntity.getName()).thenReturn(NAME);
        when(exhibitionEntity.getDateFrom()).thenReturn(DATE_FROM);
        when(exhibitionEntity.getDateTo()).thenReturn(DATE_TO);
        when(exhibitionEntity.getTheme()).thenReturn(THEME);
        when(exhibitionEntity.getAbout()).thenReturn(ABOUT);
        when(exhibitionEntity.getLongAbout()).thenReturn(LONG_ABOUT);
        when(exhibitionEntity.getPrice()).thenReturn(PRICE);
        when(exhibitionEntity.getImage()).thenReturn(IMAGE);

        Exhibition exhibition = exhibitionMapper.mapEntityToDomain(exhibitionEntity);

        assertEquals(ID, exhibition.getId());
        assertEquals(NAME, exhibition.getName());
        assertEquals(DATE_FROM, exhibition.getDateFrom());
        assertEquals(DATE_TO, exhibition.getDateTo());
        assertEquals(THEME, exhibition.getTheme());
        assertEquals(ABOUT, exhibition.getAbout());
        assertEquals(LONG_ABOUT, exhibition.getLongAbout());
        assertEquals(PRICE, exhibition.getPrice(), 0);
        assertEquals(IMAGE, exhibition.getImage());
    }

    @Test
    public void mapDomainToEntityShouldReturnExhibitionEntity() {
        when(exhibition.getId()).thenReturn(ID);
        when(exhibition.getName()).thenReturn(NAME);
        when(exhibition.getDateFrom()).thenReturn(DATE_FROM);
        when(exhibition.getDateTo()).thenReturn(DATE_TO);
        when(exhibition.getTheme()).thenReturn(THEME);
        when(exhibition.getAbout()).thenReturn(ABOUT);
        when(exhibition.getLongAbout()).thenReturn(LONG_ABOUT);
        when(exhibition.getPrice()).thenReturn(PRICE);
        when(exhibition.getImage()).thenReturn(IMAGE);

        ExhibitionEntity exhibitionEntity = exhibitionMapper.mapDomainToEntity(exhibition);

        assertEquals(ID, exhibitionEntity.getId());
        assertEquals(NAME, exhibitionEntity.getName());
        assertEquals(DATE_FROM, exhibitionEntity.getDateFrom());
        assertEquals(DATE_TO, exhibitionEntity.getDateTo());
        assertEquals(THEME, exhibitionEntity.getTheme());
        assertEquals(ABOUT, exhibitionEntity.getAbout());
        assertEquals(LONG_ABOUT, exhibitionEntity.getLongAbout());
        assertEquals(PRICE, exhibitionEntity.getPrice(), 0);
        assertEquals(IMAGE, exhibitionEntity.getImage());    }
}