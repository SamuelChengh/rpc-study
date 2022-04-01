package framework.protocol;

import framework.protocol.Protocol;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author: CH
 * @date: 2021-12-08
 */
public class ProtocolFactory {

    public static Protocol getProtocol() {

        // Java SPI
        ServiceLoader<Protocol> protocols = ServiceLoader.load(Protocol.class);
        Iterator<Protocol> iterator = protocols.iterator();
        return iterator.next();

    }

}
