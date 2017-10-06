package com.teamtreehouse.dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.teamtreehouse.Application;
import com.teamtreehouse.domain.Favorite;
import com.teamtreehouse.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static com.teamtreehouse.domain.Favorite.FavoriteBuilder;

/**
 * Created by kylebudd on 10/6/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@DatabaseSetup("classpath:favorites.xml")
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class
})
public class FavoriteDaoTest {
    @Autowired
    private FavoriteDao favoriteDao;

    @Before
    public void setUp() {
        User user = new User();
        user.setId(1L);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user,null));
    }

    @Test
    public void findAll_ShouldReturnTwoFavorites() throws Exception {
        assertThat(favoriteDao.findAll(),hasSize(2));
    }

    @Test
    public void save_ShouldPersistEntity() throws Exception {
        String placeId = "KBuddTesting1234!";
        Favorite favorite = new FavoriteBuilder().withPlaceId(placeId).build();
        favoriteDao.saveForCurrentUser(favorite);
        assertThat(favoriteDao.findByPlaceId(placeId),notNullValue(Favorite.class));
    }

}
//Final Version
