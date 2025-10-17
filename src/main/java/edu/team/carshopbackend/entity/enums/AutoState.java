package edu.team.carshopbackend.entity.enums;

import lombok.Getter;

@Getter
public enum AutoState {
    POOR("POOR"),
    USED("USED"),
    NEW("NEW");

    private final String displayState;

    AutoState(String displayState) {
        this.displayState = displayState;
    }

    public static AutoState fromString(String value) throws IllegalArgumentException {
        for (AutoState state : AutoState.values()) {
            if (state.name().equalsIgnoreCase(value) || state.displayState.equalsIgnoreCase(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unknown auto state: " + value);
    }

}
