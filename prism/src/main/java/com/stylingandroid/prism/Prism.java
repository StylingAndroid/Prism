package com.stylingandroid.prism;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.stylingandroid.prism.filter.Filter;
import com.stylingandroid.prism.filter.SystemChromeFilter;
import com.stylingandroid.prism.setter.ColourSetterFactory;

import java.util.ArrayList;
import java.util.List;

public class Prism implements Setter, ColorSetter {
    private final List<Setter> setters;
    private final List<Trigger> triggers;

    Prism(List<Trigger> triggers, List<Setter> setters) {
        this.triggers = triggers;
        this.setters = setters;
    }

    @Override
    public void setColour(@ColorInt int colour) {
        for (Setter setter : setters) {
            setter.setColour(colour);
        }
    }

    @Override
    public void setTransientColour(@ColorInt int colour) {
        for (Setter setter : setters) {
            setter.setTransientColour(colour);
        }
    }

    @Override
    public void setColor(@ColorInt int color) {
        setColour(color);
    }

    @Override
    public void setTransientColor(@ColorInt int color) {
        setTransientColour(color);
    }

    public void add(Trigger trigger) {
        triggers.add(trigger);
        trigger.addSetter(this);
    }

    public void remove(Trigger trigger) {
        trigger.removeSetter(this);
        triggers.remove(trigger);
    }

    public void destroy() {
        for (Trigger trigger : triggers) {
            remove(trigger);
        }
        setters.clear();
    }

    public static final class Builder {
        private static final Filter STATUS_BAR_COLOUR_FILTER = new SystemChromeFilter();
        private final List<Setter> setters;
        private final List<Trigger> triggers;

        public static Builder newInstance() {
            List<Setter> setters = new ArrayList<>();
            List<Trigger> triggers = new ArrayList<>();
            return new Builder(triggers, setters);
        }

        private Builder(@NonNull List<Trigger> triggers, @NonNull List<Setter> setters) {
            this.triggers = triggers;
            this.setters = setters;
        }

        public Builder background(@NonNull View view) {
            return background(view, ColourSetterFactory.IDENTITY_COLOUR_FILTER);
        }

        public Builder background(@NonNull View view, @NonNull Filter filter) {
            return add(ColourSetterFactory.getBackgroundSetter(view, filter));
        }

        public Builder background(@NonNull Window window) {
            return background(window, STATUS_BAR_COLOUR_FILTER);
        }

        public Builder background(@NonNull Window window, @NonNull Filter filter) {
            return add(ColourSetterFactory.getBackgroundSetter(window, filter));
        }

        public Builder add(@NonNull Setter setter) {
            if (setter != null) {
                setters.add(setter);
            }
            return this;
        }

        public Builder add(@NonNull ColorSetter setter) {
            if (setter != null) {
                setters.add(new SetterAdapter(setter));
            }
            return this;
        }

        public Builder add(@NonNull Trigger trigger) {
            if (trigger != null) {
                triggers.add(trigger);
            }
            return this;
        }

        public Builder text(@NonNull TextView view) {
            return text(view, ColourSetterFactory.IDENTITY_COLOUR_FILTER);
        }

        public Builder text(@NonNull TextView view, @NonNull Filter filter) {
            return add(ColourSetterFactory.getTextSetter(view, filter));
        }

        public Builder colour(@NonNull View view) {
            return colour(view, ColourSetterFactory.IDENTITY_COLOUR_FILTER);
        }

        public Builder colour(@NonNull View view, @NonNull Filter filter) {
            return add(ColourSetterFactory.getColourSetter(view, filter));
        }

        public Builder color(@NonNull View view) {
            return colour(view, ColourSetterFactory.IDENTITY_COLOUR_FILTER);
        }

        public Builder color(@NonNull View view, @NonNull Filter filter) {
            return colour(view, filter);
        }

        public Prism build() {
            Prism prism = new Prism(triggers, setters);
            for (Trigger trigger : triggers) {
                trigger.addSetter(prism);
            }
            return prism;
        }
    }
}
