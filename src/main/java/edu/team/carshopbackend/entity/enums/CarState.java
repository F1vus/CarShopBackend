package edu.team.carshopbackend.entity.enums;

import lombok.Getter;

@Getter
public enum CarState {
    POOR("POOR"),
    USED("USED"),
    NEW("NEW");

    private final String displayState;

    CarState(String displayState) {
        this.displayState = displayState;
    }

    public static CarState fromString(String value) throws IllegalArgumentException {
        for (CarState state : CarState.values()) {
            if (state.name().equalsIgnoreCase(value) || state.displayState.equalsIgnoreCase(value)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unknown car state: " + value);
    }

}
