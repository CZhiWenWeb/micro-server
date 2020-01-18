package com.czw.设计模式.命令模式;

import com.czw.设计模式.命令模式.command.GarageDoorOpenCommand;
import com.czw.设计模式.命令模式.command.LightOnCommand;
import com.czw.设计模式.命令模式.model.Garage;
import com.czw.设计模式.命令模式.model.Ligth;

/**
 * @Author: czw
 * @CreateTime: 2020-01-16 14:22
 * @UpdeteTime: 2020-01-16 14:22
 * @Description:
 */
public class RemoteControlTest {
	public static void main(String[] args) {
		SimpleRemoteControl remote=new SimpleRemoteControl();
		LightOnCommand lightOn=new LightOnCommand(new Ligth());
		remote.setCommand(lightOn);
		remote.buttonWasPressed();
		GarageDoorOpenCommand garageUp=new GarageDoorOpenCommand(new Garage());
		remote.setCommand(garageUp);
		remote.buttonWasPressed();
	}
}
