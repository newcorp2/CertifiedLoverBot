package com.team2073.robot;

import com.team2073.robot.Commands.CruiseControlCommand;
import com.team2073.robot.Commands.HalfPowerCommand;
import com.team2073.robot.Commands.Pulse;
import com.team2073.robot.Commands.Revolution;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OperatorInterface {
    private final ApplicationContext appCTX = ApplicationContext.getInstance();
    public final Joystick controller = appCTX.getController();
    // create button A
    private final JoystickButton a = new JoystickButton(controller, 1);
    private final JoystickButton leftBumper = new JoystickButton(controller, 5);
    private final JoystickButton y = new JoystickButton(controller, 4);
    private final JoystickButton b = new JoystickButton(controller, 2);

    public void init() {
        a.whileHeld(new HalfPowerCommand());
        leftBumper.whenPressed(new Pulse());
        y.toggleWhenPressed(new CruiseControlCommand());
        b.whenPressed(new Revolution());

    }


}
