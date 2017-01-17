package com.agmednet.judi.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by pshynin on 1/12/17.
 */
public class Events extends ForwardingSet<EventData> {

    private Set<EventData> delegate;

    public Events(Events events) {
        this.delegate = new HashSet<EventData>(events.delegate);
    }

    public Events(List<EventData> result) {

    }

    @Override
    protected Set<EventData> delegate() {
        return delegate;
    }

    public Events withCreated(EventData event) {
        Events events = new Events(this);
        events.add(event);
        return events;
    }

    public Events withDeleted(EventData event) {
        Events events = new Events(this);
        events.add(event);
        return events;
    }
}
