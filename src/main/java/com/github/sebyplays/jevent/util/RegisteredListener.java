package com.github.sebyplays.jevent.util;

import com.github.sebyplays.jevent.JEvent;
import com.github.sebyplays.jevent.api.Event;
import com.github.sebyplays.jevent.api.Listener;
import lombok.Getter;
import lombok.Setter;

/**
 * It's a class that holds a listener and an event
 */
public class RegisteredListener {

    @Getter
    @Setter
    private Listener listener;
    @Getter
    @Setter
    private Event event;

    // The constructor of the class.
    public RegisteredListener(Listener listener, Event event) {
        this.setListener(listener);
        this.setEvent(event);

    }

    /**
     * It calls the event
     */
    public void callEvent() {
        JEvent.call(this.getListener(), this.getEvent());
    }

}
