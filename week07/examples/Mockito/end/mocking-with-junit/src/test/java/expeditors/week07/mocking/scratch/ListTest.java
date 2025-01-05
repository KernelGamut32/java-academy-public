package expeditors.week07.mocking.scratch;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {

    @Test
    public void letsMockListSize() {
        List list = mock(List.class);
        when(list.size()).thenReturn(10);
        assertEquals(10, list.size());
    }

    @Test
    public void letsMockListSizeWithMultipleReturnValues() {
        List list = mock(List.class);
        when(list.size()).thenReturn(10).thenReturn(20);
        assertEquals(10, list.size()); // First Call
        assertEquals(20, list.size()); // Second Call
    }

    @Test
    public void letsMockListGet() {
        List<String> list = mock(List.class);
        when(list.get(0)).thenReturn("Expeditors");
        assertEquals("Expeditors", list.get(0));
        assertNull(list.get(1));
    }

    @Test
    public void letsMockListGetWithAny() {
        List<String> list = mock(List.class);
        when(list.get(anyInt())).thenReturn("Expeditors");
        // If you are using argument matchers, all arguments
        // have to be provided by matchers.
        assertEquals("Expeditors", list.get(0));
        assertEquals("Expeditors", list.get(1));
        assertEquals("Expeditors", list.get(1000));
    }

    @Test
    public void letsMockListWithException() {
        List<String> list = mock(List.class);
        when(list.get(anyInt())).thenThrow(new IndexOutOfBoundsException("Invalid list item index provided"));
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(1);
        });
        assertEquals("Invalid list item index provided", exception.getMessage());
    }

}
