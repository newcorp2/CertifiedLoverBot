package com.team2073.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.team2073.common.periodic.AsyncPeriodicRunnable;
import com.team2073.robot.ApplicationContext;
import edu.wpi.first.wpilibj.Joystick;
import java.lang.Math;
import com.team2073.common.util.*;

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
                if (controller.getRawAxis(2) > 0 && controller.getRawAxis(3) == 0 && -controller.getRawAxis(1) > 0) {
                    output -= controller.getRawAxis(2);
                } else if (controller.getRawAxis(3) > 0 && controller.getRawAxis(2) == 0 && -controller.getRawAxis(1) > 0) {
                    output += controller.getRawAxis(3);
                }
                break;
            case PULSE:
                Timer boyuan = new Timer();


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
        } else if (Math.abs(output) < 0.2) {
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

    public enum SimpleSubsystemState {
        AXIS,
        HALF_POWER,
        PULSE,
        STOP;
    }
}
