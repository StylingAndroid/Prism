package com.stylingandroid.prism.palette;

import android.graphics.Color;

import com.stylingandroid.prism.ColourUtils;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.anyInt;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

public final class MockUtils {
    public static void mockColor() {
        mockStatic(Color.class);
        when(Color.argb(anyInt(), anyInt(), anyInt(), anyInt())).thenAnswer(
                new Answer<Integer>() {
                    @Override
                    public Integer answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return ColourUtils.argb((int) args[0], (int) args[1], (int) args[2], (int) args[3]);
                    }
                });
        when(Color.rgb(anyInt(), anyInt(), anyInt())).thenAnswer(
                new Answer<Integer>() {
                    @Override
                    public Integer answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return ColourUtils.rgb((int) args[0], (int) args[1], (int) args[2]);
                    }
                });
        when(Color.alpha(anyInt())).thenAnswer(
                new Answer<Integer>() {
                    @Override
                    public Integer answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return (int) args[0] >>> ColourUtils.TWENTY_FOUR_BITS;
                    }
                });
        when(Color.red(anyInt())).thenAnswer(
                new Answer<Integer>() {
                    @Override
                    public Integer answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return ((int)args[0] >> ColourUtils.SIXTEEN_BITS) & 0xFF;
                    }
                });
        when(Color.green(anyInt())).thenAnswer(
                new Answer<Integer>() {
                    @Override
                    public Integer answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return ((int) args[0] >> ColourUtils.EIGHT_BITS) & 0xFF;
                    }
                });
        when(Color.blue(anyInt())).thenAnswer(
                new Answer<Integer>() {
                    @Override
                    public Integer answer(InvocationOnMock invocation) throws Throwable {
                        Object[] args = invocation.getArguments();
                        return (int) args[0] & 0xFF;
                    }
                });

    }
}
