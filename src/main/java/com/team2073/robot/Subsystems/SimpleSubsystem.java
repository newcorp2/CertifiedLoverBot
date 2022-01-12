package com.team2073.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import com.team2073.robot.ApplicationContext;
import edu.wpi.first.wpilibj.Joystick;

public class SimpleSubsystem implements AsyncPeriodicRunnable {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    private final Joystick controller = appCTX.getController();
    private final CANSparkMax motor = appCTX.getMotor();

    private SimpleSubsystemState currentState  = SimpleSubsystemState.AXIS;

    private double output = 0;

    public SimpleSubsystem () {
        autoRegisterWithPeriodicRunner();
    }

    @Override
    public void onPeriodicAsync() {
        output = getAxisOutput();
        switch (currentState) {
            case AXIS:
                output = getAxisOutput();
                break;
            case HALF_POWER:
                output = 0.5;
                break;
            case STOP:
                output = 0;
                break;
        }

        if (output > 0.8) {
            output = 0.8;
        } else if (output < 0.2) {
            output = 0;
        }

        motor.set(output);
    }

    public void setCurrentState(SimpleSubsystemState currentState) {
        this.currentState = currentState;
    }

    private double getAxisOutput () {
        return -controller.getRawAxis(1);
    }

    public double leftTrigger () {

    }

    public enum SimpleSubsystemState {
        AXIS,
        HALF_POWER,
        STOP;
    }
}
