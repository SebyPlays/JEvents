package com.github.sebyplays.jevent.api;

import com.github.sebyplays.jevent.util.Cancellable;
import lombok.Getter;

public class Event implements Cancellable {

    @Getter
    private boolean cancelled;
    @Getter
    private String eventName = getClass().getSimpleName();

    @Override
    public void setCancelled(boolean isCancelled) {
        this.cancelled = isCancelled;
    }
}
