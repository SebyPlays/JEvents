package com.github.sebyplays.jevent;

import com.github.sebyplays.jevent.api.Event;
import com.github.sebyplays.jevent.api.EventHandler;
import com.github.sebyplays.jevent.api.Listener;
import com.github.sebyplays.jevent.util.RegisteredListener;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * It's a class that allows you to call events and register listeners
 */
public class JEvent {

    // It's a list of all the listeners that are registered.
    public static ArrayList<RegisteredListener> handlers = new ArrayList();

    @Getter
    @Setter
    private Event event;

    public JEvent(Event event) {
        this.setEvent(event);
    }

    /**
     * It loops through all the methods in the listener class, and if the method has the EventHandler annotation, and the
     * method has one parameter, and the parameter is the same as the event, then it creates a new instance of the listener
     * class and invokes the method with the event
     *
     * @param listener The listener class that you want to call the event on.
     * @param event    The event that is being called.
     */
    @SneakyThrows
    public static void call(Listener listener, Event event) {
        Class c = listener.getClass();
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(EventHandler.class)) {
                if (method.getParameterCount() == 1) {
                    if (method.getParameterTypes()[0].getSimpleName().equalsIgnoreCase(event.getEventName())) {
                        Object object = c.newInstance();
                        method.invoke(object, event);
                    }
                }
            }
        }
    }

    /**
     * It calls the event
     *
     * @return The event itself.
     */
    public JEvent callEvent() {
        for (RegisteredListener registeredListener : handlers) {
            if (registeredListener.getEvent().getClass() == this.getEvent().getClass()) {
                registeredListener.setEvent(this.getEvent());
                registeredListener.callEvent();
            }
        }
        return this;
    }

    /**
     * It adds a new Listener to the HandlerList.handlers ArrayList
     *
     * @param listener The listener to register
     */
    public void registerListener(Listener listener) {
        handlers.add(new RegisteredListener(listener, this.getEvent()));
    }

    /**
     * It removes the listener from the list of listeners
     *
     * @param listener The listener to unregister
     */
    public void unregisterListener(Listener listener) {
        handlers.remove(new RegisteredListener(listener, this.getEvent()));
    }


}
