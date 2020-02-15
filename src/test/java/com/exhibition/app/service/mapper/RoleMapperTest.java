package com.exhibition.app.service.mapper;

import com.exhibition.app.domain.Role;
import com.exhibition.app.domain.Ticket;
import com.exhibition.app.entity.RoleEntity;
import com.exhibition.app.entity.TicketEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoleMapperTest {
    private static final Integer ID = 1;
    private static final String NAME ="Admin";
    private static final String DESCRIPTION ="Can do everything";

    private RoleMapper roleMapper = new RoleMapper();

    @Mock
    private RoleEntity roleEntity;

    @Mock
    private Role role;

    @Test
    public void mapEntityToDomainShouldReturnRole() {
        when(roleEntity.getId()).thenReturn(ID);
        when(roleEntity.getName()).thenReturn(NAME);
        when(roleEntity.getDescription()).thenReturn(DESCRIPTION);

        Role role = roleMapper.mapEntityToDomain(roleEntity);

        assertEquals(ID, role.getId());
        assertEquals(NAME, role.getName());
        assertEquals(DESCRIPTION, role.getDescription());
    }

    @Test
    public void mapDomainToEntityShouldReturnRoleEntity() {
        when(role.getId()).thenReturn(ID);
        when(role.getName()).thenReturn(NAME);
        when(role.getDescription()).thenReturn(DESCRIPTION);

        RoleEntity roleEntity = roleMapper.mapDomainToEntity(role);

        assertEquals(ID, roleEntity.getId());
        assertEquals(NAME, roleEntity.getName());
        assertEquals(DESCRIPTION, roleEntity.getDescription());
    }
}