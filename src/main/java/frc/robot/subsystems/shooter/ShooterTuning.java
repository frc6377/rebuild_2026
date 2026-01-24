package frc.robot.subsystems.shooter;

import static edu.wpi.first.units.Units.RadiansPerSecond;

import com.ctre.phoenix6.configs.Slot0Configs;
import edu.wpi.first.math.interpolation.InterpolatingDoubleTreeMap;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterTuning {
    // Map from distance (meters) to angular velocity (rad/s)
    private final InterpolatingDoubleTreeMap distanceToAngularVelocity = new InterpolatingDoubleTreeMap();

    // Keys for tuning - distance points
    private final double[] distances = {2.0, 3.0, 4.0, 5.0, 6.0};
    // Default angular velocities in rad/s for each distance
    private final double[] defaultAngularVelocities = {50.0, 55.0, 60.0, 65.0, 70.0};

    // Default PID/FF gains for real robot (TalonFX Slot0)
    private static final double DEFAULT_KP = 0.1;
    private static final double DEFAULT_KI = 0.0;
    private static final double DEFAULT_KD = 0.0;
    private static final double DEFAULT_KS = 0.0;
    private static final double DEFAULT_KV = 0.12;

    // Default simulation PID gains
    private static final double DEFAULT_SIM_KP = 0.1;
    private static final double DEFAULT_SIM_KI = 0.0;
    private static final double DEFAULT_SIM_KD = 0.0;

    // Default current limit
    private static final double DEFAULT_STATOR_CURRENT_LIMIT = 40.0;

    public ShooterTuning() {
        // Initialize distance-to-velocity tuning
        for (int i = 0; i < distances.length; i++) {
            distanceToAngularVelocity.put(distances[i], defaultAngularVelocities[i]);
            SmartDashboard.setDefaultNumber(
                    "ShooterTuning/AngularVelocity " + distances[i] + "m (rad_s)", defaultAngularVelocities[i]);
        }

        // Initialize PID/FF gains for real robot
        SmartDashboard.setDefaultNumber("ShooterTuning/kP", DEFAULT_KP);
        SmartDashboard.setDefaultNumber("ShooterTuning/kI", DEFAULT_KI);
        SmartDashboard.setDefaultNumber("ShooterTuning/kD", DEFAULT_KD);
        SmartDashboard.setDefaultNumber("ShooterTuning/kS", DEFAULT_KS);
        SmartDashboard.setDefaultNumber("ShooterTuning/kV", DEFAULT_KV);

        // Initialize simulation PID gains
        SmartDashboard.setDefaultNumber("ShooterTuning/Sim_kP", DEFAULT_SIM_KP);
        SmartDashboard.setDefaultNumber("ShooterTuning/Sim_kI", DEFAULT_SIM_KI);
        SmartDashboard.setDefaultNumber("ShooterTuning/Sim_kD", DEFAULT_SIM_KD);

        // Initialize current limit
        SmartDashboard.setDefaultNumber("ShooterTuning/StatorCurrentLimit (A)", DEFAULT_STATOR_CURRENT_LIMIT);
    }

    /** Updates the distance-to-velocity map with the latest tunable values from the dashboard. */
    private void updateMap() {
        for (int i = 0; i < distances.length; i++) {
            double val = SmartDashboard.getNumber(
                    "ShooterTuning/AngularVelocity " + distances[i] + "m (rad_s)", defaultAngularVelocities[i]);
            distanceToAngularVelocity.put(distances[i], val);
        }
    }

    /**
     * Calculates the shooter angular velocity for a given distance.
     *
     * @param distanceMeters The distance to the target in meters.
     * @return The target angular velocity.
     */
    public AngularVelocity getAngularVelocity(double distanceMeters) {
        updateMap();
        return RadiansPerSecond.of(distanceToAngularVelocity.get(distanceMeters));
    }

    /**
     * Gets the current Slot0Configs with tunable PID/FF values from SmartDashboard.
     *
     * @return Slot0Configs with current tuned values.
     */
    public Slot0Configs getSlot0Configs() {
        return new Slot0Configs()
                .withKP(SmartDashboard.getNumber("ShooterTuning/kP", DEFAULT_KP))
                .withKI(SmartDashboard.getNumber("ShooterTuning/kI", DEFAULT_KI))
                .withKD(SmartDashboard.getNumber("ShooterTuning/kD", DEFAULT_KD))
                .withKS(SmartDashboard.getNumber("ShooterTuning/kS", DEFAULT_KS))
                .withKV(SmartDashboard.getNumber("ShooterTuning/kV", DEFAULT_KV));
    }

    /** Gets the tunable kP value for simulation PID controller. */
    public double getSimKP() {
        return SmartDashboard.getNumber("ShooterTuning/Sim_kP", DEFAULT_SIM_KP);
    }

    /** Gets the tunable kI value for simulation PID controller. */
    public double getSimKI() {
        return SmartDashboard.getNumber("ShooterTuning/Sim_kI", DEFAULT_SIM_KI);
    }

    /** Gets the tunable kD value for simulation PID controller. */
    public double getSimKD() {
        return SmartDashboard.getNumber("ShooterTuning/Sim_kD", DEFAULT_SIM_KD);
    }

    /** Gets the tunable stator current limit in Amps. */
    public double getStatorCurrentLimit() {
        return SmartDashboard.getNumber("ShooterTuning/StatorCurrentLimit (A)", DEFAULT_STATOR_CURRENT_LIMIT);
    }
}
