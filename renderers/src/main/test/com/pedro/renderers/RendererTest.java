package com.pedro.renderers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.pedro.renderers.exception.NotInflateViewException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test created to check the correctness of Renderer<T>
 *
 * @author Pedro Vicente Gómez Sánchez.
 */

public class RendererTest {

    /*
     * Text data
     */

    @Spy
    private ObjectRenderer renderer;

    /*
     * Mocks
     */

    @Mock
    private Object mockedContent;
    @Mock
    private LayoutInflater mockedLayoutInflater;
    @Mock
    private ViewGroup mockedParent;
    @Mock
    private View mockedView;

    /*
     * Before and after methods
     */

    @Before
    public void setUp() {
        initializeRenderer();
        initializeMocks();
    }


    /*
     * Test methods
     */

    @Test
    public void shouldKeepTheContentAfterOnCreateCall() {
        givenARendererInflatingView(mockedView);

        onCreateRenderer();

        assertEquals(mockedContent, renderer.getContent());
    }

    @Test
    public void shouldInflateViewUsingLayoutInflaterAndParentAfterOnCreateCall() {
        givenARendererInflatingView(mockedView);

        onCreateRenderer();

        verify(renderer).inflate(mockedLayoutInflater, mockedParent);
    }


    @Test(expected = NotInflateViewException.class)
    public void shouldThrowExceptionIfInflateReturnsAnEmptyViewAfterOnCreateCall() {
        givenArendererInflatingANullView();

        onCreateRenderer();
    }

    @Test
    public void shouldAssociateTheRendererToTheRootViewTagAfterOnCreateCall() {
        givenARendererInflatingView(mockedView);

        onCreateRenderer();

        verify(mockedView).setTag(renderer);
    }

    @Test
    public void shouldSetUpViewWithTheInflatedViewAfterOnCreateCall() {
        givenARendererInflatingView(mockedView);

        onCreateRenderer();

        verify(renderer).setupView(mockedView);
    }

    @Test
    public void shouldHookListenersViewWithTheInflatedViewAfterOnCreateCall() {
        givenARendererInflatingView(mockedView);

        onCreateRenderer();

        verify(renderer).hookListeners(mockedView);
    }

    @Test
    public void shouldKeepTheContentAfterOnRecycleCall() {
        givenARendererInflatingView(mockedView);

        onRecycleRenderer();

        assertEquals(mockedContent, renderer.getContent());
    }



    /*
     * Auxiliary methods
     */

    private void initializeRenderer() {
        renderer = new ObjectRenderer();
    }

    private void initializeMocks() {
        MockitoAnnotations.initMocks(this);
    }


    private void onCreateRenderer() {
        renderer.onCreate(mockedContent, mockedLayoutInflater, mockedParent);
    }

    private void onRecycleRenderer() {
        renderer.onRecycle(mockedContent);
    }

    private void givenArendererInflatingANullView() {
        givenARendererInflatingView(null);
    }

    private void givenARendererInflatingView(View view) {
        when(renderer.inflate(mockedLayoutInflater, mockedParent)).thenReturn(view);
    }
}
