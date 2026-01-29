package frc.robot.subsystems.climb;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;

public interface ClimberIO {
    @AutoLog
    public static class ClimberIOInputs {}

    default void goToHeight(Distance height) {}

    default void stop() {}

    default void set(double percent) {}
}
