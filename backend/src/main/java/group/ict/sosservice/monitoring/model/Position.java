package group.ict.sosservice.monitoring.model;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Position {

    private double latitude;

    private double longitude;

    @Builder
    public Position(final double latitude, final double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
