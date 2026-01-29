package frc.robot.subsystems.climb;

import edu.wpi.first.units.measure.Distance;
import org.littletonrobotics.junction.AutoLog;

public interface ClimberIO {
    @AutoLog
    public static class ClimberIOInputs {}

    default void goToHeight(Distance height) {}

    default void stop() {}

    default void set(double percent) {}
}
