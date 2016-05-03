// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigGatewayInterface;
import strampáil.ClientInterface;
import strampáil.Notifier;
import strampáil.Printer;
import java.util.HashMap;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * A STOMP client designed to interface with the a
 * <a href="http://www.simsig.co.uk/">SimSig</a>
 * <a href="http://www.simsig.co.uk/dokuwiki/doku.php?id=usertrack:interface_gateway">
 * Interface Gateway</a>.
 *
 * @author Mark David Pokorny
 * @version Dé Máirt, 3ú Bealtaine 2016
 * @since Déardaoin, 21ú Aibreán 2016
 */
public class SimSigClient implements Notifier
{

// ------------------------------------------ SimSigClient Class ---------------

  private ClientInterface clientInterface;
  private Harness harness;

// ------------------------------------------ SimSigClient Class ---------------

  /**
   * Creates a SimSig client that listens for and parses SimSig messages from
   * the SimSig server.
   */
  public SimSigClient(
    String address, int port, int debug, String stompHostName, Harness harness)
  {

    this.harness = harness;
    clientInterface = new ClientInterface(address, port, debug, this);

    if (clientInterface.handshake())
    {
      if (clientInterface.connect(stompHostName))
      {
        clientInterface.subscribe("/topic/TD_ALL_SIG_AREA", true);
        harness.connected(true);
      } // End if
      else
        harness.connected(false);
    } // End if
    else
      harness.connected(false);

  } // End ‘SimSigClient(String, int, int, String, Harness)’ Constructor

// ------------------------------------------ SimSigClient Class ---------------

  /**
   * Shuts down this <code>SimSigClient</code> and the underlying interface.
   */
  public void close()
  {
    // SimSig loader 4.5.9 doesn’t send a RECEIPT for UNSUBSCRIBE (it should!)
    clientInterface.unsubscribe(false);
    // SimSig loader 4.5.9 complains about an access violation when
    // receiving a DISCONNECT frame. Not much can be done about it at the mo.
    clientInterface.disconnect();
    clientInterface.close();
  } // End ‘close()’ Method

// ------------------------------------------ SimSigClient Class ---------------

  /**
   * Parses SimSig messages when received by the {@link ClientInterface}.
   */
  public void alert()
  {
    String message = clientInterface.retrieveMessage();
    if (message != null)
      harness.transferMessage(parseMessage(message));
  } // End ‘alert()’ method

// ------------------------------------------ SimSigClient Class ---------------

  private static SimSigMessage parseMessage(String message)
  {

    MessageType type;
    HashMap<String, String> parameters;

    if (message.startsWith("{\"") && message.endsWith("\"}}"))
    {

      int headerFinalIndex = message.indexOf("\"", 2);
      String messageType = message.substring(2, headerFinalIndex);

      if (messageType.equals("CA_MSG") ||
          messageType.equals("CB_MSG") ||
          messageType.equals("CC_MSG"))
        type = MessageType.TD;
      else if (messageType.equals("SG_MSG"))
        type = MessageType.SIGNAL;
      else
      {
        Printer.printError(
          "Unable to understand SimSig message type: " + messageType);
        return null;
      }

      Printer.printInfo(type.name() + " message received.");

      if (message.startsWith("\":{\"", headerFinalIndex))
        parameters = parseParameters(message.substring(headerFinalIndex + 4));
      else
      {
        Printer.printError("Invalid SimSig message syntax.");
        return null;
      }

    } // End if
    else
    {
      Printer.printError("‘" + message + "’ is not valid JSON syntax.");
      return null;
    } // End else

    return new SimSigMessage(type, parameters);

  } // End ‘parseMessage(String)’ Method

// ------------------------------------------ SimSigClient Class ---------------

  private static HashMap<String, String> parseParameters(String message)
  {

    HashMap<String, String> parameters = new HashMap<String, String>();
    String key, value;
    int keyEnd = 0;
    int valueEnd = 0;
    while (true)
    {
      keyEnd = message.indexOf("\"", valueEnd);
      key = message.substring(valueEnd, keyEnd);

      if (message.startsWith("\":\"", keyEnd))
      {
        keyEnd += 3;
        valueEnd = message.indexOf("\"", keyEnd);
        value = message.substring(keyEnd, valueEnd);

        parameters.put(key, value);
        Printer.printDebug(key + ":" + value);

        if (message.startsWith("\",\"", valueEnd))
        {
          valueEnd += 3;
          continue;
        } // End if
        else if (message.startsWith("\"}}", valueEnd))
          return parameters;
        else
        {
          Printer.printError("1: Invalid JSON syntax detected.");
          return null;
        } // End else

      }
      else
      {
        Printer.printError("2: Invalid JSON syntax detected.");
        return null;
      } // End else

    } // End while

  } // End ‘parseHeaders(String)’ Method

// ------------------------------------------ SimSigClient Class ---------------

} // End ‘SimSigClient’ Class

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
