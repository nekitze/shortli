package edu.nikitazubov.shortli.service;

import edu.nikitazubov.shortli.entity.Url;
import edu.nikitazubov.shortli.entity.User;
import edu.nikitazubov.shortli.repository.UrlRepository;
import edu.nikitazubov.shortli.repository.UserRepository;
import edu.nikitazubov.shortli.util.UrlShortener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlServiceImplTest {

    @Mock
    private UrlRepository urlRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UrlShortener urlShortener;

    @InjectMocks
    private UrlServiceImpl urlService;

    @Mock
    private Authentication authentication;

    private static final User TEST_USER = new User();

    private static final String LONG_URL = "http://chart.apis.google.com/chart?chs=500x500&chma=0,0,100,100&cht=p&chco=FF0000%2CFFFF00%7CFF8000%2C00FF00%7C00FF00%2C0000FF&chd=t%3A122%2C42%2C17%2C10%2C8%2C7%2C7%2C7%2C7%2C6%2C6%2C6%2C6%2C5%2C5&chl=122%7C42%7C17%7C10%7C8%7C7%7C7%7C7%7C7%7C6%7C6%7C6%7C6%7C5%7C5&chdl=android%7Cjava%7Cstack-trace%7Cbroadcastreceiver%7Candroid-ndk%7Cuser-agent%7Candroid-webview%7Cwebview%7Cbackground%7Cmultithreading%7Candroid-source%7Csms%7Cadb%7Csollections%7Cactivity|Chart";
    private static final String SHORT_KEY = "Ab1dEf";

    private static final Url EXAMPLE_OWNED_URL = new Url();
    private static final Url EXAMPLE_ANONYMOUS_URL = new Url();

    @BeforeAll
    static void setUp() {
        TEST_USER.setId(1L);
        TEST_USER.setEmail("user_first@mail.com");
        TEST_USER.setRole("ROLE_USER");

        EXAMPLE_OWNED_URL.setFullUrl(LONG_URL);
        EXAMPLE_OWNED_URL.setKey(SHORT_KEY);
        EXAMPLE_OWNED_URL.setOwnerId(TEST_USER.getId());

        EXAMPLE_ANONYMOUS_URL.setFullUrl(LONG_URL);
        EXAMPLE_ANONYMOUS_URL.setKey(SHORT_KEY);
    }

    @BeforeEach
    void init() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Test
    void getAllUrls_NoAuth() {
        List<Url> urlsList = urlService.getAllUrls();
        assertNull(urlsList);
    }

    @Test
    void getAllUrls_WithAuth() {
        when(userRepository.findUserByEmail(any())).thenReturn(Optional.of(TEST_USER));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<Url> urlsList = urlService.getAllUrls();
        assertNotNull(urlsList);
    }

    @Test
    void getUrlById_NotExisting() {
        when(urlRepository.findById(any())).thenReturn(Optional.empty());
        Url url = urlService.getUrlById(any());
        assertNull(url);
    }

    @Test
    void getUrlById_Existing() {
        when(urlRepository.findById(any())).thenReturn(Optional.of(new Url()));
        Url url = urlService.getUrlById(any());
        assertNotNull(url);
    }

    @Test
    void getUrlByKey_NotExisting() {
        when(urlRepository.findUrlByKey(any())).thenReturn(Optional.empty());
        Url url = urlService.getUrlByKey(any());
        assertNull(url);
    }

    @Test
    void getUrlByKey_Existing() {
        when(urlRepository.findUrlByKey(any())).thenReturn(Optional.of(new Url()));
        Url url = urlService.getUrlByKey(any());
        assertNotNull(url);
    }

    @Test
    void addNewUrl_NoAuth() {
        when(urlShortener.shorten(any())).thenReturn(SHORT_KEY);
        Url url = urlService.addNewUrl(LONG_URL);

        verify(urlRepository).save(argThat(u ->
                u.getFullUrl().equals(EXAMPLE_ANONYMOUS_URL.getFullUrl()) &&
                        u.getOwnerId() == null &&
                        u.getKey().equals(EXAMPLE_ANONYMOUS_URL.getKey())));
    }

    @Test
    void addNewUrl_WithAuth() {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userRepository.findUserByEmail(any())).thenReturn(Optional.of(TEST_USER));
        when(urlShortener.shorten(any())).thenReturn(SHORT_KEY);

        urlService.addNewUrl(LONG_URL);

        verify(urlRepository).save(argThat(u ->
                u.getFullUrl().equals(EXAMPLE_OWNED_URL.getFullUrl()) &&
                        u.getOwnerId().equals(EXAMPLE_OWNED_URL.getOwnerId()) &&
                        u.getKey().equals(EXAMPLE_OWNED_URL.getKey())));
    }

    @Test
    void update_IsMethodCalled() {
        urlService.updateUrl(any());
        verify(urlRepository).save(any());
    }

    @Test
    void delete_IsMethodCalled() {
        urlService.deleteUrl(any());
        verify(urlRepository).deleteById(any());
    }
}
