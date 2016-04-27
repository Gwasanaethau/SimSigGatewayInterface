// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

package simsigGatewayInterface;
import strampáil.ClientInterface;
import strampáil.Constants;
import strampáil.Notifier;
import strampáil.Printer;
import java.util.HashMap;
import java.util.Scanner;

// +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

/**
 * A STOMP client designed to interface with the a
 * <a href="http://www.simsig.co.uk/">SimSig</a>
 * <a href="http://www.simsig.co.uk/dokuwiki/doku.php?id=usertrack:interface_gateway">
 * Interface Gateway</a>.
 *
 * @author Mark David Pokorny
 * @version Dé Céadaoin, 27ú Aibreán 2016
 * @since Déardaoin, 21ú Aibreán 2016
 */
public class SimSigClient implements Notifier
{

// ------------------------------------------ SimSigClient Class ---------------

  private ClientInterface client;

// ------------------------------------------ SimSigClient Class ---------------

  public SimSigClient(String hostName, int port, int debug)
  {
    client = new ClientInterface(hostName, port, debug, this);
  } // End ‘SimSigClient(String, int, int)’ Constructor

// ------------------------------------------ SimSigClient Class ---------------

  /**
   * Prints SimSig messages verbatim when received
   * by the {@link ClientInterface}.
   */
  public void alert()
  {
    String message = client.retrieveMessage();
    if (message != null)
      parseMessage(message);
  } // End ‘alert()’ method

// ------------------------------------------ SimSigClient Class ---------------

  private static void parseMessage(String message)
  {

    MessageType type;
    HashMap<String, String> parameters;

    // Finite automata state 1:
    if (message.startsWith("{\"") && message.endsWith("\"}}"))
    {

      // Finite autotmata state 3 (NB: state 2 removed!):
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
        return;
      }

      Printer.printInfo(type.name() + " message received.");

      if (message.startsWith("\":{\"", headerFinalIndex))
        // Finite autotmata state 4:
        parameters = parseParameters(message.substring(headerFinalIndex + 4));
      else
      {
        Printer.printError("Invalid SimSig message syntax.");
        return;
      }

    } // End if
    else
    {
      Printer.printError("‘" + message + "’ is not valid JSON syntax.");
      return;
    } // End else

  } // End ‘parseMessage(String)’ Method

// ------------------------------------------ SimSigClient Class ---------------

  private static HashMap<String, String> parseParameters(String message)
  {

    // Finite automata state 4:
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
        // Finite automata state 5:
        keyEnd += 3;
        valueEnd = message.indexOf("\"", keyEnd);
        value = message.substring(keyEnd, valueEnd);

        parameters.put(key, value);
        Printer.printDebug(key + ":" + value);

        if (message.startsWith("\",\"", valueEnd))
        {
          // Finite automata state 4:
          valueEnd += 3;
          continue;
        } // End if
        else if (message.startsWith("\"}}", valueEnd))
          // Finite automata state END:
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
