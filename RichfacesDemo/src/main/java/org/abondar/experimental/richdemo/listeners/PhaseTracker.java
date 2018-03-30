package org.abondar.experimental.richdemo.listeners;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class PhaseTracker implements PhaseListener {
    @Override
    public void afterPhase(PhaseEvent event) {
        event.getFacesContext().getExternalContext().log("AFTER"+event.getPhaseId());
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        event.getFacesContext().getExternalContext().log("Before"+event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}
