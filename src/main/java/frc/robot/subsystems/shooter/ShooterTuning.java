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
    private final double[] defaultAngularVelocities = {240, 55.0, 60.0, 65.0, 70.0};

    public ShooterTuning() {
        // Initialize distance-to-velocity tuning
        for (int i = 0; i < distances.length; i++) {
            distanceToAngularVelocity.put(distances[i], defaultAngularVelocities[i]);
            SmartDashboard.setDefaultNumber(
                    "ShooterTuning/AngularVelocity " + distances[i] + "m (rad_s)", defaultAngularVelocities[i]);
        }

        // Initialize PID/FF gains for real robot (defaults from ShooterConstants)
        SmartDashboard.setDefaultNumber("ShooterTuning/kP", ShooterConstants.kP);
        SmartDashboard.setDefaultNumber("ShooterTuning/kI", ShooterConstants.kI);
        SmartDashboard.setDefaultNumber("ShooterTuning/kD", ShooterConstants.kD);
        SmartDashboard.setDefaultNumber("ShooterTuning/kS", ShooterConstants.kS);
        SmartDashboard.setDefaultNumber("ShooterTuning/kV", ShooterConstants.kV);

        // Initialize simulation PID gains (defaults from ShooterConstants)
        SmartDashboard.setDefaultNumber("ShooterTuning/Sim_kP", ShooterConstants.kSimP);
        SmartDashboard.setDefaultNumber("ShooterTuning/Sim_kI", ShooterConstants.kSimI);
        SmartDashboard.setDefaultNumber("ShooterTuning/Sim_kD", ShooterConstants.kSimD);

        // Initialize current limit (default from ShooterConstants)
        SmartDashboard.setDefaultNumber("ShooterTuning/StatorCurrentLimit (A)", ShooterConstants.kStatorCurrentLimit);
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
                .withKP(SmartDashboard.getNumber("ShooterTuning/kP", ShooterConstants.kP))
                .withKI(SmartDashboard.getNumber("ShooterTuning/kI", ShooterConstants.kI))
                .withKD(SmartDashboard.getNumber("ShooterTuning/kD", ShooterConstants.kD))
                .withKS(SmartDashboard.getNumber("ShooterTuning/kS", ShooterConstants.kS))
                .withKV(SmartDashboard.getNumber("ShooterTuning/kV", ShooterConstants.kV));
    }

    /** Gets the tunable kP value for simulation PID controller. */
    public double getSimKP() {
        return SmartDashboard.getNumber("ShooterTuning/Sim_kP", ShooterConstants.kSimP);
    }

    /** Gets the tunable kI value for simulation PID controller. */
    public double getSimKI() {
        return SmartDashboard.getNumber("ShooterTuning/Sim_kI", ShooterConstants.kSimI);
    }

    /** Gets the tunable kD value for simulation PID controller. */
    public double getSimKD() {
        return SmartDashboard.getNumber("ShooterTuning/Sim_kD", ShooterConstants.kSimD);
    }

    /** Gets the tunable stator current limit in Amps. */
    public double getStatorCurrentLimit() {
        return SmartDashboard.getNumber("ShooterTuning/StatorCurrentLimit (A)", ShooterConstants.kStatorCurrentLimit);
    }

    /**
     * Gets the current Slot0Configs with tunable Simulation PID values from SmartDashboard. We pack Sim PIDs into
     * Slot0Configs for interface consistency.
     *
     * @return Slot0Configs with current tuned simulation values.
     */
    public Slot0Configs getSimSlot0Configs() {
        return new Slot0Configs()
                .withKP(getSimKP())
                .withKI(getSimKI())
                .withKD(getSimKD())
                .withKS(0.0)
                .withKV(0.0);
    }
}
