package za.co.knonchalant.space.agent;

import za.co.knonchalant.space.Spacer;
import za.co.knonchalant.space.domain.Ship;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface IRole {
    void perform(Spacer spacer, Ship ship) throws IOException;

    Date getResumeAfter();

    default void log(Ship ship, String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        System.out.println(sdf.format(new Date()) + " " + ship.getSymbol() + ": " + message);
        ship.setDisplayMessage(message);
    }

    default Date cooldown(long seconds) {
        return new Date(new Date().getTime() + 1000 * seconds);
    }
}
