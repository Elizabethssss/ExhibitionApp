package com.exhibition.app.service.mapper;

import com.exhibition.app.domain.Exposition;
import com.exhibition.app.domain.Ticket;
import com.exhibition.app.domain.User;
import com.exhibition.app.entity.ExpositionEntity;
import com.exhibition.app.entity.TicketEntity;
import com.exhibition.app.entity.UserEntity;
import com.exhibition.app.service.PasswordEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExpositionMapperTest {
    private static final Long ID = 1L;
    private static final String NAME ="Field of roses";
    private static final String ABOUT ="Good smelling roses";
    private static final String IMAGE ="flower.png";
    private static final Long EXHIBITION_ID = 1L;

    private ExpositionMapper expositionMapper = new ExpositionMapper();

    @Mock
    private ExpositionEntity expositionEntity;

    @Mock
    private Exposition exposition;

    @Test
    public void mapEntityToDomainShouldReturnExposition() {
        when(expositionEntity.getId()).thenReturn(ID);
        when(expositionEntity.getName()).thenReturn(NAME);
        when(expositionEntity.getAbout()).thenReturn(ABOUT);
        when(expositionEntity.getImage()).thenReturn(IMAGE);
        when(expositionEntity.getExhibitionId()).thenReturn(EXHIBITION_ID);

        Exposition exposition = expositionMapper.mapEntityToDomain(expositionEntity);

        assertEquals(ID, exposition.getId());
        assertEquals(NAME, exposition.getName());
        assertEquals(ABOUT, exposition.getAbout());
        assertEquals(IMAGE, exposition.getImage());
        assertEquals(EXHIBITION_ID, exposition.getExhibitionId());
    }

    @Test
    public void mapDomainToEntityShouldReturnExpositionEntity() {
        when(exposition.getId()).thenReturn(ID);
        when(exposition.getName()).thenReturn(NAME);
        when(exposition.getAbout()).thenReturn(ABOUT);
        when(exposition.getImage()).thenReturn(IMAGE);
        when(exposition.getExhibitionId()).thenReturn(EXHIBITION_ID);

        ExpositionEntity expositionEntity = expositionMapper.mapDomainToEntity(exposition);

        assertEquals(ID, expositionEntity.getId());
        assertEquals(NAME, expositionEntity.getName());
        assertEquals(ABOUT, expositionEntity.getAbout());
        assertEquals(IMAGE, expositionEntity.getImage());
        assertEquals(EXHIBITION_ID, expositionEntity.getExhibitionId());
    }
}